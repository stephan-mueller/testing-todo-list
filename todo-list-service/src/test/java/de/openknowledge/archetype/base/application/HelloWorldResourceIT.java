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
package de.openknowledge.archetype.base.application;

import de.openknowledge.archetype.base.IntegrationTestUtil;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import io.restassured.RestAssured;

/**
 * Integration test for the resource {@link HelloWorldResource}.
 */
@Testcontainers
public class HelloWorldResourceIT {

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
  public void sayHello() {
    RestAssured.given()
        .accept(MediaType.TEXT_PLAIN)
        .when()
        .get(UriBuilder.fromUri(uri).path("api").path("hello").build())
        .then()
        .statusCode(Response.Status.OK.getStatusCode())
        .contentType(MediaType.TEXT_PLAIN)
        .body(Matchers.equalTo("Hello World!"));
  }

  @Test
  public void sayHelloWorld() {
    RestAssured.given()
        .accept(MediaType.TEXT_PLAIN)
        .when()
        .get(UriBuilder.fromUri(uri).path("api").path("hello").path("Stephan").build())
        .then()
        .statusCode(Response.Status.OK.getStatusCode())
        .contentType(MediaType.TEXT_PLAIN)
        .body(Matchers.equalTo("Hello Stephan!"));
  }
}
