package com.sfl.coolmonkey.emailtemplate.service.common.model;

import org.joda.time.MutableDateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 12/21/15
 * Time: 1:55 PM
 */
public abstract class AbstractBaseEntityModel implements Serializable {
    private static final long serialVersionUID = -8466097458895357978L;

    //region Static utility methods
    public static Date cloneDateIfNotNullAndStripOffMillisOfSecond(final Date date) {
        if (date == null) {
            return null;
        }
        final MutableDateTime mutableDateTime = new MutableDateTime(date);
        mutableDateTime.setMillisOfSecond(0);
        return mutableDateTime.toDate();
    }

    public static Double getDoubleValueOrNull(final BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }
    //endregion
}
