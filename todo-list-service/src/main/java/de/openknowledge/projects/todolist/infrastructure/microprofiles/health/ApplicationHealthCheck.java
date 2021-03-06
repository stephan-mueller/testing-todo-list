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

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Health check for the application.
 */
@Liveness
@ApplicationScoped
public class ApplicationHealthCheck implements HealthCheck {

  @Inject
  @ConfigProperty(name = "build.version")
  private String buildVersion;

  @Inject
  @ConfigProperty(name = "build.timestamp")
  private String buildTimestamp;

  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.named("application")
        .withData("name", "todo-list-service")
        .withData("buildVersion", buildVersion)
        .withData("buildTimestamp", buildTimestamp)
        .up()
        .build();
  }
}
