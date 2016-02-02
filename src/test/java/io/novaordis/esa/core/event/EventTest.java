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

package io.novaordis.esa.core.event;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Ovidiu Feodorov <ovidiu@novaordis.com>
 * @since 1/24/16
 */
public abstract class EventTest {

    // Constants -------------------------------------------------------------------------------------------------------

    private static final Logger log = LoggerFactory.getLogger(EventTest.class);

    // Static ----------------------------------------------------------------------------------------------------------

    // Attributes ------------------------------------------------------------------------------------------------------

    // Constructors ----------------------------------------------------------------------------------------------------

    // Public ----------------------------------------------------------------------------------------------------------

    @Test
    public void properties() throws Exception {

        Event event = getEventToTest();

        assertTrue(event.getProperties().isEmpty());

        Property old = event.setProperty(new StringProperty("test", "test-value"));
        assertNull(old);

        Set<Property> properties = event.getProperties();
        assertEquals(1, properties.size());
        StringProperty p = (StringProperty)properties.iterator().next();
        assertEquals("test", p.getName());
        assertEquals("test-value", p.getString());

        old = event.setProperty(new StringProperty("test", "test-value-2"));
        assertNotNull(old);
        assertEquals("test", old.getName());
        assertEquals("test-value", old.getValue());

        properties = event.getProperties();
        assertEquals(1, properties.size());
        StringProperty p2 = (StringProperty)properties.iterator().next();
        assertEquals("test", p2.getName());
        assertEquals("test-value-2", p2.getString());

        StringProperty sp = (StringProperty)event.getProperty("test");
        assertEquals("test", sp.getName());
        assertEquals("test-value-2", sp.getString());
    }

    @Test
    public void nullProperty() throws Exception {

        Event event = getEventToTest();

        try {
            event.setProperty(null);
            fail("should throw exception");
        }
        catch(IllegalArgumentException e) {
            log.info(e.getMessage());
        }
    }

    // Package protected -----------------------------------------------------------------------------------------------

    // Protected -------------------------------------------------------------------------------------------------------

    protected abstract Event getEventToTest() throws Exception;

    // Private ---------------------------------------------------------------------------------------------------------

    // Inner classes ---------------------------------------------------------------------------------------------------

}
