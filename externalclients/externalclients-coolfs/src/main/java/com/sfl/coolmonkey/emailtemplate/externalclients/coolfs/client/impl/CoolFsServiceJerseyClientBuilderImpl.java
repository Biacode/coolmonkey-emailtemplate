package com.sfl.coolmonkey.emailtemplate.externalclients.coolfs.client.impl;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sfl.coolmonkey.emailtemplate.externalclients.coolfs.client.CoolFsServiceJerseyClientBuilder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/22/15
 * Time: 5:34 PM
 */
public class CoolFsServiceJerseyClientBuilderImpl implements CoolFsServiceJerseyClientBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoolFsServiceJerseyClientBuilderImpl.class);

    //region Constructors
    public CoolFsServiceJerseyClientBuilderImpl() {
        LOGGER.debug("Initializing");
    }
    //endregion

    //region Public methods
    @Override
    public Client buildCoolFsClient() {
        LOGGER.debug("Building coolfs Jersey client");
        final Client client = ClientBuilder.newBuilder().register(JacksonJsonProvider.class).register(MultiPartFeature.class).build();
        LOGGER.debug("Successfully created coolfs Jersey client - {}", client);
        return client;
    }
    //endregion
}