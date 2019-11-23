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

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

import de.openknowledge.projects.testing.archunit.baserules.ClassesInLayer;

/**
 * ArchUnit rules for the layered architecture.
 */
public class ArchUnitLayeredArchitectureRules {

  @ArchTest
  public static final ArchRule applicationLayerShouldFollowTheAccessRules =
      ClassesInLayer.shouldBeAccessByClassesFromSpecifiedPackagesOnly(ArchUnitTest.PACKAGE_ROOT + ".application..",
                                                                      ArchUnitTest.PACKAGE_ROOT + ".application..");

  @ArchTest
  public static final ArchRule domainLayerShouldFollowTheAccessRules =
      ClassesInLayer.shouldBeAccessByClassesFromSpecifiedPackagesOnly(ArchUnitTest.PACKAGE_ROOT + ".domain..",
                                                                      ArchUnitTest.PACKAGE_ROOT + ".application..",
                                                                      ArchUnitTest.PACKAGE_ROOT + ".domain..");

  @ArchTest
  public static final ArchRule domainLayerShouldFollowTheDependencyRules =
      ClassesInLayer.shouldNotDependOnClassesInSpecifiedPackages(ArchUnitTest.PACKAGE_ROOT + ".domain..",
                                                                 ArchUnitTest.PACKAGE_ROOT + ".application..");

  @ArchTest
  public static final ArchRule infrastructureLayerShouldFollowTheAccessRules =
      ClassesInLayer.shouldBeAccessByClassesFromSpecifiedPackagesOnly(ArchUnitTest.PACKAGE_ROOT + ".infrastructure..",
                                                                      ArchUnitTest.PACKAGE_ROOT + ".application..",
                                                                      ArchUnitTest.PACKAGE_ROOT + ".domain..",
                                                                      ArchUnitTest.PACKAGE_ROOT + ".infrastructure..");

  @ArchTest
  public static final ArchRule infrastructureLayerShouldFollowTheDependencyRules =
      ClassesInLayer.shouldNotDependOnClassesInSpecifiedPackages(ArchUnitTest.PACKAGE_ROOT + ".infrastructure..",
                                                                 ArchUnitTest.PACKAGE_ROOT + ".application..",
                                                                 ArchUnitTest.PACKAGE_ROOT + ".domain..");

  @ArchTest
  public static final ArchRule layeredArchitecture = Architectures.layeredArchitecture()
      .layer("Application").definedBy(ArchUnitTest.PACKAGE_ROOT + ".application..")
      .layer("Domain").definedBy(ArchUnitTest.PACKAGE_ROOT + ".domain..")
      .layer("Infrastructure").definedBy(ArchUnitTest.PACKAGE_ROOT + ".infrastructure..")
      .layer("Base").definedBy(ArchUnitTest.PACKAGE_ROOT + ".infrastructure.domain..")
      .whereLayer("Application").mayNotBeAccessedByAnyLayer()
      .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application")
      .whereLayer("Infrastructure").mayOnlyBeAccessedByLayers("Application", "Domain")
      .whereLayer("Base").mayOnlyBeAccessedByLayers("Application", "Domain", "Infrastructure");

  @ArchTest
  public static final ArchRule layersShouldBeFreeOfCycles = ClassesInLayer.layersShouldBeFreeOfCycles();
}
