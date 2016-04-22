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

package io.novaordis.esa.extensions.bscenarios;

import io.novaordis.esa.core.event.Event;
import io.novaordis.esa.httpd.HttpEvent;

/**
 * Collects data associated with a HTTP session, as identified in the incoming event stream by its JSESSIONID
 *
 * @author Ovidiu Feodorov <ovidiu@novaordis.com>
 * @since 4/21/16
 */
class HttpSession {

    // Constants -------------------------------------------------------------------------------------------------------

    public static final String JSESSIONID_COOKIE_KEY = "JSESSIONID";

    // Static ----------------------------------------------------------------------------------------------------------

    // Attributes ------------------------------------------------------------------------------------------------------

    private String jSessionId;

    // Constructors ----------------------------------------------------------------------------------------------------

    public HttpSession() {
        this(null);
    }

    public HttpSession(String jSessionId) {
        this.jSessionId = jSessionId;
    }

    // Public ----------------------------------------------------------------------------------------------------------

    public String getJSessionId() {
        return jSessionId;
    }

    public void setJSessionId(String jSessionId) {
        this.jSessionId = jSessionId;
    }

    /**
     * NOT thread safe.
     *
     * @return a BusinessScenarioEvent, a FaultEvent or null if it gets a HTTP event that belongs to the business
     * scenario being processes.
     */
    public Event processBusinessScenario(HttpEvent event) {
        throw new RuntimeException("NOT YET IMPLEMENTED");
    }

//        if (cookies != null) {
//            String jSessionIdValue = (String) cookies.getMap().get(HttpSession.JSESSIONID_COOKIE_KEY);
//        }
//
//        UserContext uc = null;
//
//        if (cookies != null) {
//
//            if (jSessionIdValue != null) {
//
//                uc = userContexts.get(jSessionIdValue);
//                if (uc == null) {
//                    uc = new UserContext();
//                    userContexts.put(jSessionIdValue, uc);
//                }
//            }
//        }
//
//        if (uc == null) {
//
//            //
//            // this request does not belong to any user
//            //
//
//            return;
//        }
//
//
//        HttpSession session = getSession(event);
//
//        if (session == null) {
//            terminatorQueue.put(FaultEvent("HTTP event " + event + " does not have "));
//
//        }
//        else {
//
//            throw new RuntimeException("NOT YET IMPLEMENTED");
//        }

//
//        BusinessScenario bs = uc.getCurrentBusinessScenario();
//        String marker = getMarker(httpEvent);
//
//        if (bs != null) {
//
//            if (START_BUSINESS_SCENARIO_MARKER.equals(marker)) {
//                throw new IllegalStateException(
//                        "got the start business scenario marker while a business scenario is active");
//            }
//
//            // the current request belongs to a business scenario
//            bs.update(httpEvent);
//
//            if (STOP_BUSINESS_SCENARIO_MARKER.equals(marker)) {
//
//                //
//                // this is the last request of this scenario, wrap it up and send statistics downstream
//                //
//
//                BusinessScenarioEvent bsEvent = bs.toEvent();
//                uc.clear();
//                getOutputQueue().put(bsEvent);
//            }
//        }
//        else {
//
//            // no current business scenario
//
//            if (START_BUSINESS_SCENARIO_MARKER.equals(marker)) {
//
//                uc.startNewBusinessScenario(httpEvent);
//                return;
//            }
//            else if (STOP_BUSINESS_SCENARIO_MARKER.equals(marker)) {
//                throw new IllegalStateException(
//                        "got the stop business scenario marker while there is no active business scenario");
//            }
//
//
//            log.debug("ignored request " + httpEvent);
//
//            //
//            // ignored request
//            //
//        }


    //
//    public static final String MARKER_REQUEST_HEADER_NAME = "NovaOrdis-Request-Group-Marker";
//    public static final String START_BUSINESS_SCENARIO_MARKER = "start";
//    public static final String STOP_BUSINESS_SCENARIO_MARKER = "stop";
//    /**
//     * @return the business scenario marker if it finds one or null otherwise
//     */
//    static String getMarker(HttpEvent event) {
//
//        if (event == null) {
//            return null;
//        }
//
//        MapProperty mp = event.getMapProperty(HttpEvent.REQUEST_HEADERS);
//
//        if (mp == null) {
//            return null;
//        }
//
//        Map<String, Object> map = mp.getMap();
//        return (String)map.get(PerfCommand.MARKER_REQUEST_HEADER_NAME);
//    }




    // Package protected -----------------------------------------------------------------------------------------------

    // Protected -------------------------------------------------------------------------------------------------------

    // Private ---------------------------------------------------------------------------------------------------------

    // Inner classes ---------------------------------------------------------------------------------------------------

//    private BusinessScenario currentBusinessScenario;
//    /**
//     * May return null
//     */
//    public BusinessScenario getCurrentBusinessScenario() {
//
//        return currentBusinessScenario;
//    }
//
//    public void clear() {
//        currentBusinessScenario = null;
//    }
//
//    public void startNewBusinessScenario(HttpEvent event) {
//
//        if (currentBusinessScenario != null) {
//            throw new IllegalStateException(
//                    "cannot start a new business scenario while there is one active: " + currentBusinessScenario);
//        }
//
//        currentBusinessScenario = new BusinessScenario(event);
//    }


}
