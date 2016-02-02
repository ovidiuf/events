/*
 * Copyright (c) 2016 Nova Ordis LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.novaordis.esa.logs.httpd;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A httpd log line.
 *
 * @author Ovidiu Feodorov <ovidiu@novaordis.com>
 * @since 1/21/16
 */
public class HttpdLogLine {

    // Constants -------------------------------------------------------------------------------------------------------

    // Static ----------------------------------------------------------------------------------------------------------

    // Attributes ------------------------------------------------------------------------------------------------------

    private Map<FormatString, Object> values;

    // Constructors ----------------------------------------------------------------------------------------------------

    public HttpdLogLine() {

        this.values = new HashMap<>();
    }

    // Methods related to the fact that these events come from a HTTP log - this is where the httpd log format details
    // are important ---------------------------------------------------------------------------------------------------

    /**
     * @return the value corresponding to the specified format element or null if there is no corresponding value
     * in the log event ("-" in the httpd logs, or whether the log format does contain the given format element.
     */
    public Object getLogValue(FormatString e) {

        return values.get(e);
    }

    /**
     * @param value the value associated with the given FormatStrings in the log entry corresponding to this event. Can
     *              be null, and this has the semantics of "erasing" the old value, if any.
     *
     * @return the previous value, if any, or null
     *
     * @exception IllegalArgumentException if the value's type does not match the format element.
     */
    public Object setLogValue(FormatString e, Object value) {

        if (value == null) {
            return values.remove(e);
        }

        if (!e.getType().equals(value.getClass())) {
            throw new IllegalArgumentException(
                    "type mismatch, " + value.getClass() + " \"" + value + "\" is not a valid type for " + e);
        }

        if (FormatStrings.TIMESTAMP.equals(e)) {

            // we convert timestamp from Date to Long
            Date timestamp = (Date)value;
            value = timestamp.getTime();
        }

        return values.put(e, value);
    }

    // Public ----------------------------------------------------------------------------------------------------------

    public Long getTimestamp() {
        return (Long)getLogValue(FormatStrings.TIMESTAMP);
    }

    /**
     * @see FormatStrings#REMOTE_HOST
     *
     * @return may return null
     */
    public String getRemoteHost() {
        return (String) getLogValue(FormatStrings.REMOTE_HOST);
    }

    /**
     * @see FormatStrings#REMOTE_LOGNAME
     *
     * @return may return null
     */
    public String getRemoteLogname() {
        return (String) getLogValue(FormatStrings.REMOTE_LOGNAME);
    }

    public String getRemoteUser() {
        return (String) getLogValue(FormatStrings.REMOTE_USER);
    }

    public String getFirstRequestLine() {
        return (String) getLogValue(FormatStrings.FIRST_REQUEST_LINE);
    }

    public String getQueryString() {
        return (String) getLogValue(FormatStrings.QUERY_STRING);
    }

    /**
     * @return may return null.
     */
    public Integer getStatusCode() {
        return (Integer) getLogValue(FormatStrings.STATUS_CODE);
    }

    /**
     * @return may return null.
     */
    public Integer getOriginalRequestStatusCode() {
        return (Integer) getLogValue(FormatStrings.ORIGINAL_REQUEST_STATUS_CODE);
    }

    /**
     * @return may return null.
     */
    public Long getResponseEntityBodySize() {
        return (Long) getLogValue(FormatStrings.RESPONSE_ENTITY_BODY_SIZE);
    }

    /**
     * @return may return null.
     */
    public String getThreadName() {
        return (String) getLogValue(FormatStrings.THREAD_NAME);
    }

    /**
     * @return may return null.
     */
    public Long getRequestProcessingTimeMs() {
        return (Long) getLogValue(FormatStrings.REQUEST_PROCESSING_TIME_MS);
    }


    public HttpEvent toEvent() {

        Long timestamp = getTimestamp();
        return new HttpEvent(timestamp);

    }

    @Override
    public String toString() {

        Long timestamp = getTimestamp();
        String ts = timestamp == null ? "-" : FormatString.TIMESTAMP_FORMAT.format(timestamp);
        String rls = getFirstRequestLine();
        rls = rls == null ? "-" : rls;
        Integer rsc = getOriginalRequestStatusCode();
        String rscs = rsc == null ? "-" : Integer.toString(rsc);
        return ts + ", " + rls + ", " + rscs;
    }

    // Package protected -----------------------------------------------------------------------------------------------

    // Protected -------------------------------------------------------------------------------------------------------

    // Private ---------------------------------------------------------------------------------------------------------

    // Inner classes ---------------------------------------------------------------------------------------------------

}
