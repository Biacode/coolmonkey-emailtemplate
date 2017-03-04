package com.sfl.coolmonkey.emailtemplate.externalclients.coolfs.client;

import javax.ws.rs.client.Client;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/22/15
 * Time: 5:31 PM
 */
public interface CoolFsServiceJerseyClientBuilder {

    Client buildCoolFsClient();
}