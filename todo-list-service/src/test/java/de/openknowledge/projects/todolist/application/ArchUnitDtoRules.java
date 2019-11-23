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

import de.openknowledge.projects.testing.archunit.baserules.ClassesWithSuffix;

import de.openknowledge.projects.todolist.ArchUnitTest;
import de.openknowledge.projects.todolist.infrastructure.domain.value.AbstractValueObject;

/**
 * ArchUnit rules for DTO classes.
 */
public class ArchUnitDtoRules {

  @ArchTest
  public static final ArchRule dtoClassesShouldExtendToAbstractValueObject =
      ClassesWithSuffix.shouldBeAssignableToClassOrInterface("DTO", AbstractValueObject.class);

  @ArchTest
  public static final ArchRule valueObjectClassesShouldResideInApplicationOrBaseOrDomainLayer =
      ClassesWithSuffix.shouldResideInAnySpecifiedPackage("DTO", ArchUnitTest.PACKAGE_ROOT + ".application..",
                                                          ArchUnitTest.PACKAGE_ROOT + ".infrastructure.domain.error",
                                                          ArchUnitTest.PACKAGE_ROOT + ".infrastructure.validation");
}
