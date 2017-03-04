package com.sfl.coolmonkey.emailtemplate.api.client.rest.emailtemplate;

import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request.*;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response.*;

import javax.annotation.Nonnull;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/23/15
 * Time: 11:11 AM
 */
public interface EmailTemplateResourceClient {
    @Nonnull
    ResultResponseModel<CreateEmailTemplateResponse> create(@Nonnull final CreateEmailTemplateRequest request);

    @Nonnull
    ResultResponseModel<GetEmailGridResponse> getEmailGrid(@Nonnull final GetEmailGridRequest request);

    @Nonnull
    ResultResponseModel<UpdateEmailTemplateResponse> update(@Nonnull final UpdateEmailTemplateRequest request);

    @Nonnull
    ResultResponseModel<GetEmailTemplatesByTypeResponse> getByType(@Nonnull final GetEmailTemplatesByTypeRequest request);

    @Nonnull
    ResultResponseModel<GetEmailTemplateResponse> get(@Nonnull final GetEmailTemplateRequest request);

    @Nonnull
    ResultResponseModel<ActivateEmailTemplateResponse> activate(@Nonnull final ActivateEmailTemplateRequest request);

    @Nonnull
    ResultResponseModel<DeactivateEmailTemplateResponse> deactivate(@Nonnull final DeactivateEmailTemplateRequest request);

    @Nonnull
    ResultResponseModel<RemoveEmailTemplateResponse> remove(@Nonnull final RemoveEmailTemplateRequest request);

    @Nonnull
    ResultResponseModel<GetActiveEmailTemplatesResponse> getAllActive(@Nonnull final GetActiveEmailTemplatesRequest request);

    @Nonnull
    ResultResponseModel<GetInactiveEmailTemplatesResponse> getAllInactive(@Nonnull final GetInactiveEmailTemplatesRequest request);

    @Nonnull
    ResultResponseModel<GetEmailTemplateBySystemTypeResponse> getBySystemType(@Nonnull final GetEmailTemplateBySystemTypeRequest request);

    @Nonnull
    ResultResponseModel<GetEmailTemplatesForIndexationResponse> getForIndexation(@Nonnull final GetEmailTemplatesForIndexationRequest request);

    @Nonnull
    ResultResponseModel<CopyEmailTemplatesResponse> copy(@Nonnull final CopyEmailTemplatesRequest request);

    @Nonnull
    ResultResponseModel<CreateCompanyDefaultsEmailTemplateResponse> createCompanyDefaults(@Nonnull final CreateCompanyDefaultsEmailTemplateRequest request);
}
