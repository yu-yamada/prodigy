/*
 * Copyright (C) 2019-present Eiichiro Uchiumi and the Prodigy Authors. 
 * All Rights Reserved.
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

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.logging.Log;

import io.eiichiro.prodigy.Scheduler.Entry;

public class StatusHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static Log log = LambdaLogFactory.getLog(StatusHandler.class);

    static {
        ProvisionedConcurrency.warmup();
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        APIGatewayProxyResponseEvent output = new APIGatewayProxyResponseEvent();

        try {
            Map<String, String> queryStringParameters = input.getQueryStringParameters();
            String id = null;

            if (queryStringParameters != null) {
                id = queryStringParameters.get("id");
            }
            
            Object response;

            if (id == null) {
                response = Prodigy.container().scheduler().list();
            } else {
                Entry entry = Prodigy.container().scheduler().get(id);

                if (entry == null) {
                    return output.withStatusCode(400).withBody("Fault id [" + id + "] not found");
                }

                response = entry;
            }

            ObjectMapper mapper = new ObjectMapper();
            return output.withStatusCode(200).withBody(mapper.writeValueAsString(response));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return output.withStatusCode(500).withBody(e.getMessage());
        }
    }
    
}
