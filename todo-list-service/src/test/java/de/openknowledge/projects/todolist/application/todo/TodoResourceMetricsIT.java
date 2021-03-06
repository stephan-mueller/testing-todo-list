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
package de.openknowledge.projects.todolist.application.todo;

import de.openknowledge.projects.todolist.IntegrationTestUtil;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

/**
 * Integration test for the metrics of the resource {@link TodoResource}.
 */
@Testcontainers
@Tag("testcontainer")
public class TodoResourceMetricsIT {

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

    RestAssured.given()
        .accept(MediaType.APPLICATION_JSON)
        .when()
        .get(UriBuilder.fromUri(baseUri).path("api").path("todos").build())
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
        .statusCode(Response.Status.OK.getStatusCode());

    response
        .rootPath("'todos.TodoResource'")
        .body("size()", Matchers.is(15));

    response
        .rootPath("'todos.createTodo'")
        .body("size()", Matchers.is(15));

    response
        .rootPath("'todos.deleteTodo'")
        .body("size()", Matchers.is(15));

    response
        .rootPath("'todos.getTodo'")
        .body("size()", Matchers.is(15));

    response
        .rootPath("'todos.getTodos'")
        .body("size()", Matchers.is(15));

    response
        .rootPath("'todos.updateTodo'")
        .body("size()", Matchers.is(15));
  }
}
