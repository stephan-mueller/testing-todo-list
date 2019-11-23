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
package de.openknowledge.projects.todolist.infrastructure.rest.exception;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import de.openknowledge.projects.testing.archunit.baserules.ClassesWithSuffix;
import de.openknowledge.projects.testing.archunit.baserules.InheritedClasses;

import de.openknowledge.projects.todolist.ArchUnitTest;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * ArchUnit rules for exception mapper classes.
 */
public class ArchUnitJaxrsExceptionMapperRules {

  @ArchTest
  public static final ArchRule exceptionMapperClassesShouldBeAnnotatedWithProvider =
      InheritedClasses.shouldBeAnnotatedWithAnnotationType(ExceptionMapper.class, Provider.class);

  @ArchTest
  public static final ArchRule exceptionMapperClassesShouldHaveSuffixExceptionMapper =
      InheritedClasses.shouldHaveNameWithSuffix(ExceptionMapper.class, "ExceptionMapper");

  @ArchTest
  public static final ArchRule exceptionMapperClassesShouldImplementExceptionMapperInterface =
      ClassesWithSuffix.shouldBeAssignableToClassOrInterface("ExceptionMapper", ExceptionMapper.class);

  @ArchTest
  public static final ArchRule exceptionMapperClassesShouldResideInInfrastructureLayer =
      InheritedClasses.shouldResideInSpecifiedPackages(ExceptionMapper.class, ArchUnitTest.PACKAGE_ROOT + ".infrastructure.rest.exception..");
}
