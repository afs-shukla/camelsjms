/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.examples.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author <a href="http://www.christianposta.com/blog">Christian Posta</a>
 */
public class JmsRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:jmsTimer?period=5000")
                .routeId("JmsRouteBuilder.producer")
                .setBody(constant("camel rocks jms!"))
                .to("jms:topic:beer.lager").to("jms:queue:beer.ipa");


        from("jms:topic:beer.lager")
                .routeId("JmsRouteBuilder.lagerConsumer")
                .log("we just received a lager on the JMS endpoint: ${body}");
    }
}
