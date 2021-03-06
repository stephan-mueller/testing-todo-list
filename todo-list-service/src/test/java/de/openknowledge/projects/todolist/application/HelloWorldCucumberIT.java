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

import de.openknowledge.projects.testing.junit.Testcontainers;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Cucumber-Test for the resource {@link HelloWorldResource}.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "de.openknowledge.projects.todolist.application.HelloWorldCucumberTestContainerBaseClass"}, features = "src/test/resources/it/feature")
@Category(Testcontainers.class) // TODO replace by JUnit5 @Tag. Annotation is ignored by maven-failsafe-plugin at the moment
public class HelloWorldCucumberIT {
}
