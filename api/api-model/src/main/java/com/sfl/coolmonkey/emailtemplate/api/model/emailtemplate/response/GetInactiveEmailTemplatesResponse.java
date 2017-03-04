package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response;

import com.sfl.coolmonkey.commons.api.model.response.AbstractGridAwareResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateModel;

import java.util.List;

/**
 * User: Syuzanna Eprikyan
 * Company: SFL LLC
 * Date: 10/6/16
 * Time: 2:19 PM
 */
public class GetInactiveEmailTemplatesResponse extends AbstractGridAwareResponseModel<EmailTemplateModel> {
    private static final long serialVersionUID = 8240188092367972236L;

    //region Properties
    //endregion

    //region Constructors
    public GetInactiveEmailTemplatesResponse() {
    }

    public GetInactiveEmailTemplatesResponse(final List<EmailTemplateModel> grid) {
        super(grid);
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion

}
