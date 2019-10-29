/*
 * Copyright (C) 2019 Eiichiro Uchiumi and The Prodigy Authors. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.eiichiro.prodigy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class Prodigy {

    private static Log log = LogFactory.getLog(Prodigy.class);

    private static Configuration configuration = new Configuration();

    private static Container container = new Container();

    private Prodigy() {}

    public static Configuration configuration() {
        return configuration;
    }

    public static void configuration(Configuration configuration) {
        Prodigy.configuration = configuration;
    }
    
    public static Container container() {
        return container;
    }

    public static void container(Container container) {
        Prodigy.container = container;
    }

}
