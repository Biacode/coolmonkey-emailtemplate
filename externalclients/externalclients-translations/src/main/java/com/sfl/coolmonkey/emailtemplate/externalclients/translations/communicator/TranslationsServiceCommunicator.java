package com.sfl.coolmonkey.emailtemplate.externalclients.translations.communicator;


import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.translations.api.model.translation.request.*;
import com.sfl.coolmonkey.translations.api.model.translation.response.*;

import javax.annotation.Nonnull;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/26/16
 * Time: 12:31 PM
 */
public interface TranslationsServiceCommunicator {

    @Nonnull
    ResultResponseModel<GetAllTranslationsByUiLocationResponse> getAllTranslationsByUiLocation(@Nonnull final GetAllTranslationsByUiLocationRequest request);

}
