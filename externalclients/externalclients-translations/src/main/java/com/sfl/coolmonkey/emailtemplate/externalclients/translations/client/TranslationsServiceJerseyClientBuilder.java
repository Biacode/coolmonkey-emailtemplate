package com.sfl.coolmonkey.emailtemplate.externalclients.translations.client;

import javax.ws.rs.client.Client;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 1/26/16
 * Time: 12:23 PM
 */
public interface TranslationsServiceJerseyClientBuilder {
    Client buildTranslationsClient();
}
