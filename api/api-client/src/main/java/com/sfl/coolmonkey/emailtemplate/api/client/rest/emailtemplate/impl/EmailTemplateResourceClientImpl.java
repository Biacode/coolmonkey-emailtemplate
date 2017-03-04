package com.sfl.coolmonkey.emailtemplate.api.client.rest.emailtemplate.impl;

import com.sfl.coolmonkey.commons.api.model.request.AbstractRequestModel;
import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.client.rest.common.AbstractResourceClient;
import com.sfl.coolmonkey.emailtemplate.api.client.rest.emailtemplate.EmailTemplateResourceClient;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request.*;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/23/15
 * Time: 11:12 AM
 */
public class EmailTemplateResourceClientImpl extends AbstractResourceClient implements EmailTemplateResourceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateResourceClientImpl.class);

    //region Constants
    private static final String RESOURCE_BASE_PATH = "email-template";
    private static final String COMPANY_UUID = "companyUuid";
    //endregion

    //region Constructors
    public EmailTemplateResourceClientImpl(final Client client, final String apiPath) {
        super(client, apiPath);
        LOGGER.debug("Initializing email template resource client");
    }
    //endregion

    //region Public methods
    @Nonnull
    @Override
    public ResultResponseModel<CreateEmailTemplateResponse> create(@Nonnull final CreateEmailTemplateRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), new GenericType<ResultResponseModel<CreateEmailTemplateResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetEmailGridResponse> getEmailGrid(@Nonnull final GetEmailGridRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path("email-grid")
                .queryParam(COMPANY_UUID, request.getCompanyUuid())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ResultResponseModel<GetEmailGridResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<UpdateEmailTemplateResponse> update(@Nonnull final UpdateEmailTemplateRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), new GenericType<ResultResponseModel<UpdateEmailTemplateResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetEmailTemplatesByTypeResponse> getByType(@Nonnull final GetEmailTemplatesByTypeRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path("byType")
                .queryParam(COMPANY_UUID, request.getCompanyUuid())
                .queryParam("type", request.getType())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ResultResponseModel<GetEmailTemplatesByTypeResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetEmailTemplateResponse> get(@Nonnull final GetEmailTemplateRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .queryParam(COMPANY_UUID, request.getCompanyUuid())
                .queryParam("uuid", request.getUuid())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ResultResponseModel<GetEmailTemplateResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<ActivateEmailTemplateResponse> activate(@Nonnull final ActivateEmailTemplateRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path("activate")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), new GenericType<ResultResponseModel<ActivateEmailTemplateResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<DeactivateEmailTemplateResponse> deactivate(@Nonnull final DeactivateEmailTemplateRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path("deactivate")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), new GenericType<ResultResponseModel<DeactivateEmailTemplateResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<RemoveEmailTemplateResponse> remove(@Nonnull final RemoveEmailTemplateRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .queryParam(COMPANY_UUID, request.getCompanyUuid())
                .queryParam("uuid", request.getUuid())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .delete(new GenericType<ResultResponseModel<RemoveEmailTemplateResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetActiveEmailTemplatesResponse> getAllActive(@Nonnull final GetActiveEmailTemplatesRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path("all-active")
                .queryParam(COMPANY_UUID, request.getCompanyUuid())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ResultResponseModel<GetActiveEmailTemplatesResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetInactiveEmailTemplatesResponse> getAllInactive(@Nonnull final GetInactiveEmailTemplatesRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path("all-inactive")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ResultResponseModel<GetInactiveEmailTemplatesResponse>>(){
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetEmailTemplateBySystemTypeResponse> getBySystemType(@Nonnull final GetEmailTemplateBySystemTypeRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path("by-system-type")
                .queryParam("systemType", request.getSystemType())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ResultResponseModel<GetEmailTemplateBySystemTypeResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetEmailTemplatesForIndexationResponse> getForIndexation(@Nonnull final GetEmailTemplatesForIndexationRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path("for-indexation")
                .queryParam("page", request.getPage())
                .queryParam("size", request.getSize())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ResultResponseModel<GetEmailTemplatesForIndexationResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<CopyEmailTemplatesResponse> copy(@Nonnull final CopyEmailTemplatesRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path("copy")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), new GenericType<ResultResponseModel<CopyEmailTemplatesResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<CreateCompanyDefaultsEmailTemplateResponse> createCompanyDefaults(@Nonnull final CreateCompanyDefaultsEmailTemplateRequest request) {
        assertRequestNotNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path("create-company-defaults")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), new GenericType<ResultResponseModel<CreateCompanyDefaultsEmailTemplateResponse>>() {
                });
    }
    //endregion

    //region Utility methods
    private void assertRequestNotNull(final AbstractRequestModel request) {
        Assert.notNull(request, "Request should not be null");
    }
    //endregion
}
