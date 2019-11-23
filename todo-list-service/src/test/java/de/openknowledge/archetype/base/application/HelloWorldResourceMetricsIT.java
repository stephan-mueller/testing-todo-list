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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

/**
 * Integration test for the metrics of the resource {@link HelloWorldResource}.
 */
@Testcontainers
public class HelloWorldResourceMetricsIT {

  @Container
  private static GenericContainer testContainer = IntegrationTestUtil.getTestContainer();

  private static String uri;

  @BeforeAll
  public static void setUpBeforeClass() {
    uri = UriBuilder.fromUri(IntegrationTestUtil.METRICS_URI)
        .resolveTemplate("host", testContainer.getContainerIpAddress())
        .resolveTemplate("port", testContainer.getFirstMappedPort())
        .toTemplate();
  }

  @BeforeEach
  void setUp() {
    String baseUri = UriBuilder.fromUri(IntegrationTestUtil.BASE_URI)
        .resolveTemplate("host", testContainer.getContainerIpAddress())
        .resolveTemplate("port", testContainer.getFirstMappedPort())
        .resolveTemplate("context-root", IntegrationTestUtil.getContextRoot())
        .toTemplate();

    URI build = UriBuilder.fromUri(baseUri).path("api").path("hello").build();
    RestAssured.given()
        .accept(MediaType.TEXT_PLAIN)
        .when()
        .get(build)
        .then()
        .statusCode(Response.Status.OK.getStatusCode());
  }

  @Test
  public void getApplicationMetrics() {
    ValidatableResponse response = RestAssured.given()
        .accept(MediaType.APPLICATION_JSON)
        .when()
        .get(UriBuilder.fromUri(uri).path("application").build())
        .then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("size()", Matchers.is(3));

    response
        .rootPath("'HelloWorld.HelloWorldResource'")
        .body("size()", Matchers.is(15))
        .body("p50", Matchers.notNullValue())
        .body("p75", Matchers.notNullValue())
        .body("p95", Matchers.notNullValue())
        .body("p98", Matchers.notNullValue())
        .body("p99", Matchers.notNullValue())
        .body("p999", Matchers.notNullValue())
        .body("min", Matchers.notNullValue())
        .body("mean", Matchers.notNullValue())
        .body("max", Matchers.notNullValue())
        .body("stddev", Matchers.notNullValue())
        .body("count", Matchers.notNullValue())
        .body("meanRate", Matchers.notNullValue())
        .body("oneMinRate", Matchers.notNullValue())
        .body("fiveMinRate", Matchers.notNullValue())
        .body("fifteenMinRate", Matchers.notNullValue());

    response
        .rootPath("'HelloWorld.sayHello'")
        .body("size()", Matchers.is(15));

    response
        .rootPath("'HelloWorld.sayHelloWorld'")
        .body("size()", Matchers.is(15));
  }
}
