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
package de.openknowledge.projects.testing.archunit.baserules;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

/**
 * Classes in layer rules. ArchUnit rules that specific layer access and dependency rules.
 */
public class ClassesInLayer {

  public static ArchRule shouldBeAccessByClassesFromSpecifiedPackagesOnly(final String packageIdentifier, final String... packages) {
    return ArchRuleDefinition.classes()
        .that()
        .resideInAPackage(packageIdentifier)
        .should()
        .onlyBeAccessed()
        .byAnyPackage(packages)
        .because(String.format("Classes in package '%s' should be access by classes from packages '%s' only", packageIdentifier, packages));
  }

  public static ArchRule shouldNotDependOnClassesInSpecifiedPackages(final String packageIdentifier, final String... packages) {
    return ArchRuleDefinition.noClasses()
        .that()
        .resideInAPackage(packageIdentifier)
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage(packages)
        .because(String.format("No classes in package '%s' should depend on classes from packages '%s'", packageIdentifier, packages));
  }

  public static ArchRule layersShouldBeFreeOfCycles() {
    return SlicesRuleDefinition.slices()
        .matching("..(**)..")
        .should()
        .beFreeOfCycles()
        .because("All packages should be free of cycles");
  }
}
