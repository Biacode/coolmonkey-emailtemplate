package com.sfl.coolmonkey.emailtemplate.externalclients.translations.client.impl;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sfl.coolmonkey.emailtemplate.externalclients.translations.client.TranslationsServiceJerseyClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/26/16
 * Time: 12:29 PM
 */
public class TranslationsServiceJerseyClientBuilderImpl implements TranslationsServiceJerseyClientBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslationsServiceJerseyClientBuilderImpl.class);

    //region Constructors
    public TranslationsServiceJerseyClientBuilderImpl() {
        LOGGER.debug("Initializing translations service jersey client builder");
    }
    //endregion

    //region Public methods
    @Override
    public Client buildTranslationsClient() {
        return ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build();
    }
    //endregion
}
