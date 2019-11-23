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
import de.openknowledge.projects.testing.archunit.baserules.ClassesWithSuffix;

import de.openknowledge.projects.todolist.ArchUnitTest;

import javax.ws.rs.Path;

/**
 * ArchUnit rules for JAX-RS resource classes.
 */
public class ArchUnitJaxrsResourceRules {

  @ArchTest
  public static final ArchRule resourceClassesShouldBeAnnotatedWithPath =
      ClassesWithSuffix.shouldBeAnnotatedWithAnnotationType("Resource", Path.class);

  @ArchTest
  public static final ArchRule resourceClassesShouldHaveSuffixResource =
      AnnotatedClasses.shouldHaveNameWithSuffix(Path.class, "Resource");

  @ArchTest
  public static final ArchRule resourceClassesShouldResideInApplicationLayer =
      AnnotatedClasses.shouldResideInAnySpecifiedPackage(Path.class, ArchUnitTest.PACKAGE_ROOT + ".application..");
}
