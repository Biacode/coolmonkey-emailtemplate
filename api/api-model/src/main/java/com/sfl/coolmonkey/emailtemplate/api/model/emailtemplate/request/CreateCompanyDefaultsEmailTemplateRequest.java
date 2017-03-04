package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request;

import com.sfl.coolmonkey.commons.api.model.request.AbstractCompanyUuidAwareRequestModel;

/**
 * User: Suren Aznauryan
 * Company: SFL LLC
 * Date: 9/3/15
 * Time: 5:34 PM
 */
public class CreateCompanyDefaultsEmailTemplateRequest extends AbstractCompanyUuidAwareRequestModel {

    private static final long serialVersionUID = 5493132766304066416L;

    //region Properties
    //endregion

    //region Constructors
    public CreateCompanyDefaultsEmailTemplateRequest() {
    }

    public CreateCompanyDefaultsEmailTemplateRequest(final String companyUuid) {
        super(companyUuid);
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion
}
