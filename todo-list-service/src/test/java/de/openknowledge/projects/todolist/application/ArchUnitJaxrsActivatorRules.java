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

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import de.openknowledge.projects.testing.archunit.baserules.AnnotatedClasses;
import de.openknowledge.projects.testing.archunit.baserules.InheritedClasses;

import de.openknowledge.projects.todolist.ArchUnitTest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * ArchUnit rules for JAX-RS activator classes.
 */
public class ArchUnitJaxrsActivatorRules {

  @ArchTest
  public static final ArchRule jaxrsActivatorClassShouldBeAnnotatedWithApplicationPath =
      InheritedClasses.shouldBeAnnotatedWithAnnotationType(Application.class, ApplicationPath.class);

  @ArchTest
  public static final ArchRule jaxrsActivatorClassShouldExtendApplication =
      AnnotatedClasses.shouldBeAssignableToClass(ApplicationPath.class, Application.class);

  @ArchTest
  public static final ArchRule jaxrsActivatorClassShouldHaveNameJaxRsActivator =
      AnnotatedClasses.shouldHaveName(ApplicationPath.class, "JaxRsActivator");

  @ArchTest
  public static final ArchRule jaxrsActivatorClassShouldResideInApplicationLayer =
      AnnotatedClasses.shouldResideInAnySpecifiedPackage(ApplicationPath.class, ArchUnitTest.PACKAGE_ROOT + ".application");
}
