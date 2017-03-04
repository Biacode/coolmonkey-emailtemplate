package com.sfl.coolmonkey.emailtemplate.service.common.util;

import org.joda.time.MutableDateTime;

import java.util.Date;

/**
 * User: Mher Sargsyan
 * Date: 9/25/15
 * Time: 10:46 PM
 */
public final class DateUtils {

    //region Constructors
    private DateUtils() {
    }
    //endregion

    //region Static utility methods
    public static Date cloneDateIfNotNull(final Date date) {
        if (date == null) {
            return null;
        }
        final MutableDateTime mutableDateTime = new MutableDateTime(date);
        return mutableDateTime.toDate();
    }

    public static Date cloneDateIfNotNullAndStripOffTime(final Date date) {
        if (date == null) {
            return null;
        }
        final MutableDateTime mutableDateTime = new MutableDateTime(date);
        mutableDateTime.setMillisOfSecond(0);
        mutableDateTime.setSecondOfMinute(0);
        mutableDateTime.setMinuteOfHour(0);
        mutableDateTime.setHourOfDay(0);
        return mutableDateTime.toDate();
    }
    //endregion
}
