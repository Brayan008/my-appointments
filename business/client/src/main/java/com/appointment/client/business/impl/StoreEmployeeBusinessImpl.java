package com.appointment.client.business.impl;

import com.appointment.client.business.StoreEmployeeBusiness;
import com.appointment.client.dtos.ClientAppointmentResponse;
import com.appointment.client.dtos.ConfigEmployeeResponse;
import com.appointment.client.dtos.StoreEmployeeResponse;
import com.appointment.client.services.DatabaseService;
import com.appointment.commons.dtos.response.AppointmentDetailEmployeeRes;
import com.appointment.commons.dtos.response.EmployeeAppointmentsAvailabilityResponse;
import com.appointment.commons.enums.StatusAppointmentsEnum;
import com.appointment.commons.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreEmployeeBusinessImpl implements StoreEmployeeBusiness {

   private final DatabaseService databaseService;
   private final List<Integer> dayOfWeek = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

   @Override
   public Flux<EmployeeAppointmentsAvailabilityResponse> getEmployeeAvailability(Long storeEmployeeId) {
      return databaseService.getStoreEmployeeById(storeEmployeeId)
         .flatMapMany(employee ->
            Flux.fromIterable(dayOfWeek)
               .concatMap(day -> processDay(employee.getStoreEmployeeId(), day))
               .sort(Comparator.comparingInt(EmployeeAppointmentsAvailabilityResponse::getDayOfWeek))
               .switchIfEmpty(Flux.empty()));
   }

   private Mono<EmployeeAppointmentsAvailabilityResponse> processDay(Long employeeId, Integer day) {
      return Mono.defer(() -> {
         LocalDate targetDate = calculateTargetDate(day);

         if (isPastDate(targetDate)) {
            return Mono.just(buildNullResponse(day));
         }

         return fetchAndProcessConfig(employeeId, day, targetDate)
            .onErrorResume(e -> handleProcessingError(employeeId, day, e));
      });
   }

   private LocalDate calculateTargetDate(int targetDayOfWeek) {
      LocalDate today = LocalDate.now();
      LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
      return startOfWeek.plusDays(targetDayOfWeek - 1);
   }

   private boolean isPastDate(LocalDate date) {
      return date.isBefore(LocalDate.now());
   }

   private Mono<EmployeeAppointmentsAvailabilityResponse> fetchAndProcessConfig(
      Long employeeId,
      Integer day,
      LocalDate targetDate
   ) {
      return databaseService.findConfigEmployeeByStoreEmployeeIdAndDayOfWeek(employeeId, day)
         .flatMap(config -> processConfig(employeeId, day, targetDate, config))
         .switchIfEmpty(Mono.just(buildNullResponse(day)));
   }

   private Mono<EmployeeAppointmentsAvailabilityResponse> processConfig(
      Long employeeId,
      Integer day,
      LocalDate targetDate,
      ConfigEmployeeResponse config
   ) {
      if (isConfigDisabled(config)) {
         return Mono.just(buildNullResponse(day));
      }

      return fetchAndMergeAppointments(employeeId, day, targetDate, config);
   }

   private boolean isConfigDisabled(ConfigEmployeeResponse config) {
      return config.getStatus().getStatusId().equals(StatusEnum.DISABLED.getCode());
   }

   private Mono<EmployeeAppointmentsAvailabilityResponse> fetchAndMergeAppointments(
      Long employeeId,
      Integer day,
      LocalDate targetDate,
      ConfigEmployeeResponse config
   ) {
      return databaseService.getAppointmentsByStoreEmployeeIdAndDateWithoutStatus(
            employeeId,
            targetDate,
            StatusAppointmentsEnum.CANCELED.getCode()
         )
         .collectList()
         .map(appointments -> buildDayResponse(day, targetDate, config, appointments));
   }

   private EmployeeAppointmentsAvailabilityResponse buildDayResponse(
      Integer day,
      LocalDate date,
      ConfigEmployeeResponse config,
      List<ClientAppointmentResponse> appointments
   ) {
      return EmployeeAppointmentsAvailabilityResponse.builder()
         .dayOfWeek(day)
         .appointments(mergeAppointmentsWithSlots(date, config, appointments))
         .build();
   }

   private List<AppointmentDetailEmployeeRes> mergeAppointmentsWithSlots(
      LocalDate date,
      ConfigEmployeeResponse config,
      List<ClientAppointmentResponse> appointments
   ) {
      List<LocalDateTime> availableSlots = generateAvailableSlots(date, config);
      Map<LocalDateTime, Long> appointmentMap = createAppointmentMap(appointments);

      return availableSlots.stream()
         .map(slot -> buildAppointmentDetail(slot, appointmentMap))
         .sorted(Comparator.comparing(AppointmentDetailEmployeeRes::getUserAppointment))
         .collect(Collectors.toList());
   }

   private Map<LocalDateTime, Long> createAppointmentMap(List<ClientAppointmentResponse> appointments) {
      return appointments.stream()
         .collect(Collectors.toMap(
            ClientAppointmentResponse::getUserAppointment,
            ClientAppointmentResponse::getStatusAppointmentId
         ));
   }

   private AppointmentDetailEmployeeRes buildAppointmentDetail(
      LocalDateTime slot,
      Map<LocalDateTime, Long> appointmentMap
   ) {
      return AppointmentDetailEmployeeRes.builder()
         .userAppointment(slot)
         .statusAppointmentId(
            appointmentMap.getOrDefault(slot, StatusAppointmentsEnum.AVAILABLE.getCode())
         )
         .build();
   }

   private List<LocalDateTime> generateAvailableSlots(LocalDate date, ConfigEmployeeResponse config) {
      List<LocalDateTime> slots = new ArrayList<>();
      int interval = config.getIntervalInMinutes();

      addSlotsForPeriod(
         slots,
         date,
         config.getStartTime(),
         config.getStartTimeBreak(),
         interval
      );

      addSlotsForPeriod(
         slots,
         date,
         config.getEndTimeBreak(),
         config.getEndTime(),
         interval
      );

      return slots;
   }

   private void addSlotsForPeriod(
      List<LocalDateTime> slots,
      LocalDate date,
      LocalTime start,
      LocalTime end,
      int interval
   ) {
      LocalTime current = start;
      while (current.isBefore(end)) {
         slots.add(LocalDateTime.of(date, current));
         current = current.plusMinutes(interval);

         if (current.plusMinutes(interval).isAfter(end)) {
            break;
         }
      }
   }

   private EmployeeAppointmentsAvailabilityResponse buildNullResponse(Integer day) {
      return EmployeeAppointmentsAvailabilityResponse.builder()
         .dayOfWeek(day)
         .appointments(null)
         .build();
   }

   private Mono<EmployeeAppointmentsAvailabilityResponse> handleProcessingError(
      Long employeeId,
      Integer day,
      Throwable error
   ) {
      log.error("Error processing availability for employee {} day {}: {}",
         employeeId, day, error.getMessage());
      return Mono.just(buildNullResponse(day));
   }
}
