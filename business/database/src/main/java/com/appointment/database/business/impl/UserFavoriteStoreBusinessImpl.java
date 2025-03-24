package com.appointment.database.business.impl;

import com.appointment.commons.dtos.GenericResponse;
import com.appointment.database.business.UserFavoriteStoreBusiness;
import com.appointment.database.entities.StoreEntity;
import com.appointment.database.entities.UserEntity;
import com.appointment.database.entities.UserFavoriteStoresEntity;
import com.appointment.database.services.StoreService;
import com.appointment.database.services.UserFavoriteStoreService;
import com.appointment.database.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserFavoriteStoreBusinessImpl implements UserFavoriteStoreBusiness {
   private final StoreService storeService;
   private final UserFavoriteStoreService userFavoriteStoreService;
   private final UserService userService;
   private final MessageSource messageSource;

   @Override
   public GenericResponse addFavoriteStore(Long storeId, String emailUser) {
      Locale locale = LocaleContextHolder.getLocale();
      StoreEntity store = this.storeService.getStoreById(storeId);
      UserEntity user = this.userService.getUserByEmail(emailUser);
      UserFavoriteStoresEntity favoriteStore = new UserFavoriteStoresEntity();
      favoriteStore.setStore(store);
      favoriteStore.setUser(user);
      UserFavoriteStoresEntity userFavoriteStoresEntity = this.userFavoriteStoreService.createFavoriteStore(favoriteStore);
      return GenericResponse.builder().title(messageSource.getMessage("message.favorite-store.2001", new Object[]{userFavoriteStoresEntity.getStore().getName()}, locale)).build();
   }

   @Override
   public GenericResponse deleteFavoriteStore(Long favoriteStoreId) {
      Locale locale = LocaleContextHolder.getLocale();
      UserFavoriteStoresEntity userFavoriteStoresEntity = this.userFavoriteStoreService.deleteFavoriteStore(favoriteStoreId);
      return GenericResponse.builder().title(messageSource.getMessage("message.favorite-store.2002", new Object[]{userFavoriteStoresEntity.getStore().getName()}, locale)).build();
   }
}
