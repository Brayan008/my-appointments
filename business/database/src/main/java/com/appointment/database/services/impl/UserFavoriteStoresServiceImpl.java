package com.appointment.database.services.impl;

import com.appointment.commons.exceptions.BusinessException;
import com.appointment.database.entities.UserFavoriteStoresEntity;
import com.appointment.database.repositories.UserFavoriteStoreRepository;
import com.appointment.database.services.UserFavoriteStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserFavoriteStoresServiceImpl implements UserFavoriteStoreService {

    private final UserFavoriteStoreRepository userFavoriteStoreRepository;
    private final MessageSource messageSource;

    @Override
    public UserFavoriteStoresEntity createFavoriteStore(UserFavoriteStoresEntity userFavoriteStoresEntity) {
        Locale locale = LocaleContextHolder.getLocale();
        if(this.userFavoriteStoreRepository.findByUserAndStore(userFavoriteStoresEntity.getUser(), userFavoriteStoresEntity.getStore()) != null)
            throw new BusinessException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                messageSource.getMessage("error.40001.user-favorite-stores", new Object[]{userFavoriteStoresEntity.getStore().getName()}, locale), "", HttpStatus.BAD_REQUEST);


        return this.userFavoriteStoreRepository.save(userFavoriteStoresEntity);
    }

    @Override
    public UserFavoriteStoresEntity deleteFavoriteStore(Long userFavoritStoreId) {
        Locale locale = LocaleContextHolder.getLocale();
        UserFavoriteStoresEntity userFavoriteStoresEntity = userFavoriteStoreRepository.findById(userFavoritStoreId)
            .orElseThrow(() -> new BusinessException(String.valueOf(HttpStatus.NOT_FOUND.value()),
                messageSource.getMessage("error.404.user-favorite-stores", null, locale), "", HttpStatus.NOT_FOUND));
        this.userFavoriteStoreRepository.delete(userFavoriteStoresEntity);
        return userFavoriteStoresEntity;
    }
}
