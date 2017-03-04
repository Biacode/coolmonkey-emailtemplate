package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response;

import com.sfl.coolmonkey.commons.api.model.response.AbstractGridAwareResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateSearchModel;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 29/03/16
 * Time: 12:31
 */
public class GetEmailTemplatesForIndexationResponse extends AbstractGridAwareResponseModel<EmailTemplateSearchModel> {
    private static final long serialVersionUID = -1935084954217098255L;

    //region Properties
    //endregion

    //region Constructors
    public GetEmailTemplatesForIndexationResponse() {
    }

    public GetEmailTemplatesForIndexationResponse(final List<EmailTemplateSearchModel> grid) {
        super(grid);
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion
}
