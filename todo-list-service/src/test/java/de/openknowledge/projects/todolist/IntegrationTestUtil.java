/*
 * Copyright (C) open knowledge GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package de.openknowledge.projects.todolist;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;

/**
 * Util class for integration tests.
 */
public final class IntegrationTestUtil {

  public static final String BASE_URI = "http://{host}:{port}/{context-root}";
  public static final String HEALTH_URI = "http://{host}:{port}/health";
  public static final String METRICS_URI = "http://{host}:{port}/metrics";
  public static final String OPENAPI_URI = "http://{host}:{port}/openapi";

  private static final Network network = Network.newNetwork();

  private static final GenericContainer h2Container = new GenericContainer("oscarfonts/h2:1.1.119")
      .withNetwork(network)
      .withNetworkAliases("h2db")
      .waitingFor(
          Wait.forLogMessage(".*TCP server running.*\n", 1)
      );

  private static final int HTTP_PORT = 8080;

  private static final GenericContainer testContainer =
      new GenericContainer("todo-list-service:0")
          .withExposedPorts(HTTP_PORT)
          .withNetwork(network)
          .dependsOn(h2Container)
          .waitingFor(
              Wait.forHttp("/todo-list-service/api/hello")
          );

  private IntegrationTestUtil() {
    super();
  }

  public static GenericContainer getTestContainer() {
    return testContainer;
  }

  public static GenericContainer getH2Container() {
    return h2Container;
  }

  public static String getContextRoot() {
    return System.getProperty("thorntail.test.context-path", "todo-list-service");
  }
}
