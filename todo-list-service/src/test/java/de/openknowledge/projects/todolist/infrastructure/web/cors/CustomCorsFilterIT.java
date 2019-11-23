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
package de.openknowledge.projects.todolist.infrastructure.web.cors;

import de.openknowledge.projects.todolist.IntegrationTestUtil;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import io.restassured.RestAssured;

/**
 * Integration test for the custom CORS filter.
 *
 * @see CustomCorsFilter
 */
@Testcontainers
@Tag("testcontainer")
public class CustomCorsFilterIT {

  @Container
  private static GenericContainer testContainer = IntegrationTestUtil.getTestContainer();

  private static String uri;

  @BeforeAll
  public static void setUp() {
    uri = UriBuilder.fromUri(IntegrationTestUtil.BASE_URI)
        .resolveTemplate("host", testContainer.getContainerIpAddress())
        .resolveTemplate("port", testContainer.getFirstMappedPort())
        .resolveTemplate("context-root", IntegrationTestUtil.getContextRoot())
        .toTemplate();
  }

  @Test
  public void checkCorsHeader() {
    RestAssured.given()
        .header("Origin", "localhost:8080")
        .when()
        .options(UriBuilder.fromUri(uri).path("api").build())
        .then()
        .statusCode(Response.Status.OK.getStatusCode())
        .header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
        .header("Access-Control-Allow-Origin", Matchers.notNullValue())
        .header("Access-Control-Max-Age", Matchers.notNullValue());
  }
}
