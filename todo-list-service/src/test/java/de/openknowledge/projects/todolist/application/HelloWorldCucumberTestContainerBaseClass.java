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
package de.openknowledge.projects.todolist.application;

import de.openknowledge.projects.todolist.IntegrationTestUtil;

import org.testcontainers.containers.GenericContainer;

import javax.ws.rs.core.UriBuilder;

import cucumber.api.event.ConcurrentEventListener;
import cucumber.api.event.EventHandler;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestRunFinished;
import cucumber.api.event.TestRunStarted;

/**
 * Base class which starts a testcontainer for the cucumber test.
 */
public class HelloWorldCucumberTestContainerBaseClass implements ConcurrentEventListener {

  private static GenericContainer testContainer = IntegrationTestUtil.getTestContainer();

  private static String uri;

  @Override
  public void setEventPublisher(EventPublisher eventPublisher) {
    eventPublisher.registerHandlerFor(TestRunStarted.class, setup);
    eventPublisher.registerHandlerFor(TestRunFinished.class, teardown);
  }

  private EventHandler<TestRunStarted> setup = event -> {
    beforeAll();

    uri = UriBuilder.fromUri(IntegrationTestUtil.BASE_URI)
        .resolveTemplate("host", testContainer.getContainerIpAddress())
        .resolveTemplate("port", testContainer.getFirstMappedPort())
        .resolveTemplate("context-root", IntegrationTestUtil.getContextRoot())
        .toTemplate();
  };

  private void beforeAll() {
    testContainer.start();
  }

  private EventHandler<TestRunFinished> teardown = event -> {
    afterAll();
  };

  private void afterAll() {
    testContainer.stop();
  }

  public static String getUri() {
    return uri;
  }
}

