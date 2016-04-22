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

package io.novaordis.esa.httpd;

import io.novaordis.esa.core.event.MapProperty;
import io.novaordis.esa.core.event.TimedEventTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Ovidiu Feodorov <ovidiu@novaordis.com>
 * @since 2/1/16
 */
public class HttpEventTest extends TimedEventTest {

    // Constants -------------------------------------------------------------------------------------------------------

    // Static ----------------------------------------------------------------------------------------------------------

    // Attributes ------------------------------------------------------------------------------------------------------

    // Constructors ----------------------------------------------------------------------------------------------------

    // Public ----------------------------------------------------------------------------------------------------------

    // getCookie() -----------------------------------------------------------------------------------------------------

    @Test
    public void getCookie_NoCookie() throws Exception {

        HttpEvent e = getEventToTest(0L);
        assertNull(e.getCookie("no-such-cookie"));
    }

    @Test
    public void getCookie_NoCookiesInMap() throws Exception {

        HttpEvent e = getEventToTest(0L);
        MapProperty mp = new MapProperty(HttpEvent.COOKIES);
        e.setProperty(mp);
        assertNull(e.getCookie("no-such-cookie"));
    }

    @Test
    public void getCookie() throws Exception {

        HttpEvent e = getEventToTest(0L);
        MapProperty mp = new MapProperty(HttpEvent.COOKIES);
        mp.getMap().put("test-cookie-name", "test-cookie-value");
        e.setProperty(mp);

        assertEquals("test-cookie-value", e.getCookie("test-cookie-name"));
    }

    // setCookie() -----------------------------------------------------------------------------------------------------

    @Test
    public void setCookie() throws Exception {

        HttpEvent e = getEventToTest(0L);
        assertNull(e.getCookie("test-cookie-name"));
        e.setCookie("test-cookie-name", "test-cookie-value");
        assertEquals("test-cookie-value", e.getCookie("test-cookie-name"));
    }

    // Package protected -----------------------------------------------------------------------------------------------

    // Protected -------------------------------------------------------------------------------------------------------

    @Override
    protected HttpEvent getEventToTest(Long timestamp) throws Exception {
        return new HttpEvent(timestamp);
    }

    // Private ---------------------------------------------------------------------------------------------------------

    // Inner classes ---------------------------------------------------------------------------------------------------

}
