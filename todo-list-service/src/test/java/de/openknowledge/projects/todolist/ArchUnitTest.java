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
package de.openknowledge.projects.todolist;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;

import de.openknowledge.projects.todolist.application.ArchUnitDtoRules;
import de.openknowledge.projects.todolist.application.ArchUnitJaxrsActivatorRules;
import de.openknowledge.projects.todolist.application.ArchUnitJaxrsResourceRules;
import de.openknowledge.projects.todolist.infrastructure.domain.builder.ArchUnitBuilderRules;
import de.openknowledge.projects.todolist.infrastructure.domain.entity.ArchUnitEntityRules;
import de.openknowledge.projects.todolist.infrastructure.domain.repository.ArchUnitRepositoryRules;
import de.openknowledge.projects.todolist.infrastructure.microprofiles.health.ArchUnitMicroprofileHealthRules;
import de.openknowledge.projects.todolist.infrastructure.rest.exception.ArchUnitJaxrsExceptionMapperRules;

/**
 * ArchUnit test class for the architecture tests.
 */
@AnalyzeClasses(packagesOf = ArchUnitTest.class, importOptions = {ImportOption.DoNotIncludeArchives.class, ImportOption.DoNotIncludeJars.class})
public class ArchUnitTest {

  public static final String PACKAGE_ROOT = ArchUnitTest.class.getPackage().getName();


  @ArchTest
  public static final ArchRules layeredArchitectureRules = ArchRules.in(ArchUnitLayeredArchitectureRules.class);


  @ArchTest
  public static final ArchRules builderRules = ArchRules.in(ArchUnitBuilderRules.class);

  @ArchTest
  public static final ArchRules entityRules = ArchRules.in(ArchUnitEntityRules.class);

  @ArchTest
  public static final ArchRules repositoryRules = ArchRules.in(ArchUnitRepositoryRules.class);


  @ArchTest
  public static final ArchRules dtoRules = ArchRules.in(ArchUnitDtoRules.class);

  @ArchTest
  public static final ArchRules jaxrsActivatorRules = ArchRules.in(ArchUnitJaxrsActivatorRules.class);

  @ArchTest
  public static final ArchRules jaxrsResourceRules = ArchRules.in(ArchUnitJaxrsResourceRules.class);

  @ArchTest
  public static final ArchRules jaxrsExceptionMapperRules = ArchRules.in(ArchUnitJaxrsExceptionMapperRules.class);


  @ArchTest
  public static final ArchRules microprofileHealthRules = ArchRules.in(ArchUnitMicroprofileHealthRules.class);
}
