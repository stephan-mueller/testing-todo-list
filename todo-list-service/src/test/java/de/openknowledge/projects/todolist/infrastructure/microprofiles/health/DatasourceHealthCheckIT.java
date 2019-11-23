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
package de.openknowledge.projects.todolist.infrastructure.microprofiles.health;

import de.openknowledge.projects.todolist.IntegrationTestUtil;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import io.restassured.RestAssured;

/**
 * Integration test for the health check {@link DatasourceHealthCheck}.
 */
@Disabled
@Testcontainers
@Tag("testcontainer")
public class DatasourceHealthCheckIT {

  @Container
  private static GenericContainer testContainer = IntegrationTestUtil.getTestContainer();

  private static String uri;

  @BeforeAll
  public static void setUp() {
    uri = UriBuilder.fromUri(IntegrationTestUtil.HEALTH_URI)
        .resolveTemplate("host", testContainer.getContainerIpAddress())
        .resolveTemplate("port", testContainer.getFirstMappedPort())
        .toTemplate();
  }

  @Test
  public void checkHealth() {
    RestAssured.given()
        .accept(MediaType.APPLICATION_JSON)
        .when()
        .get(uri)
        .then()
        .contentType(MediaType.APPLICATION_JSON)
        .statusCode(Response.Status.OK.getStatusCode())
        .body("status", Matchers.equalTo("UP"))
        .rootPath("checks.find{ it.name == 'datasource' }")
        .body("status", Matchers.equalTo("UP"))
        .body("data.driverName", Matchers.equalTo("H2 JDBC Driver"))
        .body("data.driverVersion", Matchers.notNullValue())
        .body("data.databaseProductName", Matchers.equalTo("H2"))
        .body("data.databaseProductVersion", Matchers.notNullValue());
  }
}
