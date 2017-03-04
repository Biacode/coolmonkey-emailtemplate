package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request;

import com.sfl.coolmonkey.commons.api.model.request.AbstractCompanyUuidAwareRequestModel;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 10/27/15
 * Time: 8:23 PM
 */
public class GetActiveEmailTemplatesRequest extends AbstractCompanyUuidAwareRequestModel {
    private static final long serialVersionUID = -1769225439138538351L;

    //region Properties
    //endregion

    //region Constructors
    public GetActiveEmailTemplatesRequest() {
    }

    public GetActiveEmailTemplatesRequest(final String companyUuid) {
        super(companyUuid);
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion
}
