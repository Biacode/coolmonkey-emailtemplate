package com.sfl.coolmonkey.emailtemplate.externalclients.translations.communicator.impl;

import com.sfl.coolmonkey.commons.api.model.request.AbstractRequestModel;
import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.emailtemplate.externalclients.translations.communicator.TranslationsServiceCommunicator;
import com.sfl.coolmonkey.translations.api.client.rest.translation.TranslationResourceClient;
import com.sfl.coolmonkey.translations.api.model.translation.request.*;
import com.sfl.coolmonkey.translations.api.model.translation.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/26/16
 * Time: 12:31 PM
 */
@Component
public class TranslationsServiceCommunicatorImpl implements TranslationsServiceCommunicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TranslationsServiceCommunicatorImpl.class);

    //region Dependencies
    @Autowired
    private TranslationResourceClient translationResourceClient;
    //endregion

    //region Constructors
    public TranslationsServiceCommunicatorImpl() {
        LOGGER.debug("Initializing translations service communicator");
    }
    //endregion

    //region Public methods
    @Nonnull
    @Override
    public ResultResponseModel<GetAllTranslationsByUiLocationResponse> getAllTranslationsByUiLocation(@Nonnull final GetAllTranslationsByUiLocationRequest request) {
        assertRequestNotNull(request);
        return translationResourceClient.getAllByUiLocation(request);
    }
    //endregion

    //region Utility methods
    private void assertRequestNotNull(final AbstractRequestModel request) {
        Assert.notNull(request);
    }
    //endregion
}
