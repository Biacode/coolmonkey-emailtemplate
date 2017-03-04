package com.sfl.coolmonkey.emailtemplate.api.rest.resources.emailtemplate;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request.*;
import com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.EmailTemplateFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/23/15
 * Time: 11:09 AM
 */
@Component
@Path("email-template")
@Produces("application/json")
@Consumes("application/json")
public class EmailTemplateResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateResource.class);

    //region Dependencies
    @Autowired
    private EmailTemplateFacade emailTemplateFacade;
    //endregion

    //region Constructors
    public EmailTemplateResource() {
        LOGGER.debug("Initializing email template resource");
    }
    //endregion

    //region Public methods
    @PUT
    @JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
    public Response create(final CreateEmailTemplateRequest request) {
        return Response.ok(emailTemplateFacade.create(request)).build();
    }

    @POST
    @JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
    public Response update(final UpdateEmailTemplateRequest request) {
        return Response.ok(emailTemplateFacade.update(request)).build();
    }

    @GET
    public Response get(@BeanParam final GetEmailTemplateRequest request) {
        return Response.ok(emailTemplateFacade.get(request)).build();
    }

    @GET
    @Path("email-grid")
    public Response getEmailGrid(@BeanParam final GetEmailGridRequest request) {
        return Response.ok(emailTemplateFacade.getEmailGrid(request)).build();
    }

    @GET
    @Path("byType")
    public Response getByType(@BeanParam final GetEmailTemplatesByTypeRequest request) {
        return Response.ok(emailTemplateFacade.getByType(request)).build();
    }

    @POST
    @Path("activate")
    @JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
    public Response activate(final ActivateEmailTemplateRequest request) {
        return Response.ok(emailTemplateFacade.activate(request)).build();
    }

    @POST
    @Path("deactivate")
    @JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
    public Response activate(final DeactivateEmailTemplateRequest request) {
        return Response.ok(emailTemplateFacade.deactivate(request)).build();
    }

    @DELETE
    @JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
    public Response remove(@BeanParam final RemoveEmailTemplateRequest request) {
        return Response.ok(emailTemplateFacade.remove(request)).build();
    }

    @GET
    @Path("all-active")
    public Response getAllActive(@BeanParam final GetActiveEmailTemplatesRequest request) {
        return Response.ok(emailTemplateFacade.getAllActive(request)).build();
    }

    @GET
    @Path("all-inactive")
    public Response getAllInactive(@BeanParam final GetInactiveEmailTemplatesRequest request) {
        return Response.ok(emailTemplateFacade.getAllInactive(request)).build();
    }

    @GET
    @Path("by-system-type")
    public Response getBySystemType(@BeanParam final GetEmailTemplateBySystemTypeRequest request) {
        return Response.ok(emailTemplateFacade.getBySystemType(request)).build();
    }

    @GET
    @Path("for-indexation")
    public Response getForIndexation(@BeanParam final GetEmailTemplatesForIndexationRequest request) {
        return Response.ok(emailTemplateFacade.getForIndexation(request)).build();
    }

    @POST
    @Path("copy")
    public Response copy(final CopyEmailTemplatesRequest request) {
        return Response.ok(emailTemplateFacade.copy(request)).build();
    }

    @POST
    @Path("create-company-defaults")
    @JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})
    public Response createCompanyDefaults(final CreateCompanyDefaultsEmailTemplateRequest request) {
        return Response.ok(emailTemplateFacade.createCompanyDefaults(request)).build();
    }

    @GET
    @Path("heartbeat")
    public Response getHeartBeat() {
        return Response.ok("OK").build();
    }
    //endregion
}
