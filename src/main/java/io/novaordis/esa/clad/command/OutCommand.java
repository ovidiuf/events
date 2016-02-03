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

package io.novaordis.esa.clad.command;

import io.novaordis.clad.application.ApplicationRuntime;
import io.novaordis.clad.command.CommandBase;
import io.novaordis.clad.configuration.Configuration;
import io.novaordis.clad.option.Option;
import io.novaordis.clad.option.StringOption;
import io.novaordis.esa.clad.EventsApplicationRuntime;
import io.novaordis.esa.clad.OutputFormatter;
import io.novaordis.esa.core.OutputStreamTerminator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Set;

/**
 * The command simply connects the runtime's output queue to the terminator and starts the pipeline.
 *
 * @author Ovidiu Feodorov <ovidiu@novaordis.com>
 * @since 2/1/16
 */
public class OutCommand extends CommandBase {

    // Constants -------------------------------------------------------------------------------------------------------

    private static final Logger log = LoggerFactory.getLogger(OutCommand.class);

    public static final StringOption OUTPUT_FORMAT_OPTION = new StringOption('o', "output-format");

    // Static ----------------------------------------------------------------------------------------------------------

    // Attributes ------------------------------------------------------------------------------------------------------

    // Constructors ----------------------------------------------------------------------------------------------------

    // Command implementation ------------------------------------------------------------------------------------------

    @Override
    public Set<Option> requiredOptions() {
        return Collections.singleton(OUTPUT_FORMAT_OPTION);
    }

    @Override
    public void execute(Configuration configuration, ApplicationRuntime r) throws Exception {

        log.debug("executing " + this);

        EventsApplicationRuntime runtime = (EventsApplicationRuntime)r;

        //
        // mostly everything is already in place, set up by the application's runtime. We just need to connect the head
        // of the pipeline to the terminator, set up the output format, start the pipeline and wait for the end of
        // stream.
        //

        OutputStreamTerminator terminator = runtime.getTerminator();
        terminator.setInputQueue(runtime.getOutputQueue());
        ((OutputFormatter)terminator.getConversionLogic()).
                setFormat(((StringOption)getOption(OUTPUT_FORMAT_OPTION)).getString());

        runtime.start();
        runtime.waitForEndOfStream();
    }

    // Public ----------------------------------------------------------------------------------------------------------

    // Package protected -----------------------------------------------------------------------------------------------

    // Protected -------------------------------------------------------------------------------------------------------

    // Private ---------------------------------------------------------------------------------------------------------

    // Inner classes ---------------------------------------------------------------------------------------------------

}
