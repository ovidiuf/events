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

package io.novaordis.events.extensions.bscenarios;

import io.novaordis.events.core.event.GenericTimedEvent;
import io.novaordis.events.core.event.StringProperty;

/**
 * @author Ovidiu Feodorov <ovidiu@novaordis.com>
 * @since 2/4/16
 */
public class BusinessScenarioEvent extends GenericTimedEvent {

    // Constants -------------------------------------------------------------------------------------------------------

    public static final String ID = "id";
    public static final String DURATION = "duration";
    public static final String REQUEST_COUNT = "request-count";
    public static final String TYPE = "type";
    public static final String STATE = "state";
    public static final String JSESSIONID = "jsessionid";
    public static final String ITERATION_ID = "iteration-id";

    // Static ----------------------------------------------------------------------------------------------------------

    // Attributes ------------------------------------------------------------------------------------------------------

    // Constructors ----------------------------------------------------------------------------------------------------

    protected BusinessScenarioEvent(Long timestamp) {
        super(timestamp);
    }

    // Public ----------------------------------------------------------------------------------------------------------

    /**
     * @return may return null if the state is not stored in the event, or it may throw
     * IllegalStateException if the event carries an invalid state.
     *
     * @exception IllegalStateException
     */
    public BusinessScenarioState getState() {

        StringProperty sp = getStringProperty(BusinessScenarioEvent.STATE);
        String s = sp == null ? null : sp.getString();

        if (s == null) {
            return null;
        }

        try {
            return BusinessScenarioState.valueOf(s);
        }
        catch(Exception e) {
            throw new IllegalStateException(this + " carries an invalid BusinessScenarioState value \"" + s + "\"", e);
        }
    }

    public String getJSessionId() {

        StringProperty sp = getStringProperty(BusinessScenarioEvent.JSESSIONID);
        return sp == null ? null : sp.getString();
    }

    // Package protected -----------------------------------------------------------------------------------------------

    // Protected -------------------------------------------------------------------------------------------------------

    // Private ---------------------------------------------------------------------------------------------------------

    // Inner classes ---------------------------------------------------------------------------------------------------

}
