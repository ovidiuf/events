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

package io.novaordis.esa;

import java.util.Date;
import java.util.List;

/**
 * A generic timed event.
 *
 * It has a timestamp and an arbitrary number of typed properties.
 *
 * @author Ovidiu Feodorov <ovidiu@novaordis.com>
 * @since 1/21/16
 */
public interface Event {

    // Constants -------------------------------------------------------------------------------------------------------

    // Static ----------------------------------------------------------------------------------------------------------

    // Public ----------------------------------------------------------------------------------------------------------

    /**
     * @return may return null
     */
    Date getTimestamp();

    /**
     * @return the number of non-null properties for this event. Non-null time stamp counts as a value.
     */
    int getPropertyCount();

    /**
     * @return the list of property names this event carries values for, in the "preferred" order, which can be
     * used when displaying the event.
     */
    List<String> getPropertyNames();

    /**
     * @param name
     * @return a typed value - may return null.
     */
    Object getPropertyValue(String name);

    /**
     * @param value - null is acceptable, it has a "remove" semantics.
     *
     * @return the previous value or null.
     */
    Object setPropertyValue(String name, Object value);

}
