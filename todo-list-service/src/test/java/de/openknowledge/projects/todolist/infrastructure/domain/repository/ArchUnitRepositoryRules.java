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
package de.openknowledge.projects.todolist.infrastructure.domain.repository;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import de.openknowledge.projects.testing.archunit.baserules.AnnotatedClasses;
import de.openknowledge.projects.testing.archunit.baserules.ClassesWithSuffix;

import de.openknowledge.projects.todolist.ArchUnitTest;

/**
 * ArchUnit rules for repository classes.
 */
public class ArchUnitRepositoryRules {

  @ArchTest
  public static final ArchRule repositoryClassesShouldBeAnnotatedWithStereotypeRepository =
      ClassesWithSuffix.shouldBeAnnotatedWithAnnotationType("Repository", Repository.class);

  @ArchTest
  public static final ArchRule repositoryClassesShouldHaveSuffixRepository =
      AnnotatedClasses.shouldHaveNameWithSuffix(Repository.class, "Repository");

  @ArchTest
  public static final ArchRule repositoryClassesShouldResideInDomainLayer =
      AnnotatedClasses.shouldResideInAnySpecifiedPackage(Repository.class, ArchUnitTest.PACKAGE_ROOT + ".domain..");
}
