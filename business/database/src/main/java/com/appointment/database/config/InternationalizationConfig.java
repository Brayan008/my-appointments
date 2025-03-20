package com.appointment.database.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import java.util.Locale;

@Configuration
public class InternationalizationConfig {

   @Bean
   public AcceptHeaderLocaleResolver localeResolver() {
      AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
      localeResolver.setDefaultLocale(Locale.ENGLISH);
      return localeResolver;
   }

   @Bean
   public MessageSource messageSource() {
      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
      messageSource.setBasename("classpath:messages");
      messageSource.setDefaultEncoding("UTF-8");
      return messageSource;
   }
}
