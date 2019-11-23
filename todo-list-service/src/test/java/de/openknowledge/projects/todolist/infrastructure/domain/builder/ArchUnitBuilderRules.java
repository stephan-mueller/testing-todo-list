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
package de.openknowledge.projects.todolist.infrastructure.domain.builder;

import static de.openknowledge.projects.todolist.ArchUnitTest.PACKAGE_ROOT;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import de.openknowledge.projects.testing.archunit.baserules.ClassesWithSuffix;
import de.openknowledge.projects.testing.archunit.baserules.InheritedClasses;

/**
 * ArchUnit rules for builder classes.
 */
public class ArchUnitBuilderRules {

  @ArchTest
  public static final ArchRule builderClassesShouldBeAssignableToBuilderInterface =
      ClassesWithSuffix.shouldBeAssignableToClassOrInterface("Builder", Builder.class);

  @ArchTest
  public static final ArchRule builderClassesShouldHaveNameWithSuffixBuilder =
      InheritedClasses.shouldHaveNameWithSuffix(Builder.class, "Builder");

  @ArchTest
  public static final ArchRule builderClassesShouldResideInDomainLayer =
      ArchRuleDefinition.classes()
          .that()
          .areAssignableTo(Builder.class)
          .and()
          .areNotInterfaces()
          .and()
          .doNotHaveModifier(JavaModifier.ABSTRACT)
          .and()
          .doNotHaveFullyQualifiedName(ObjectBuilder.class.getName())
          .and()
          .haveSimpleNameNotContaining("Test")
          .should()
          .resideInAnyPackage(PACKAGE_ROOT + ".domain..")
          .because(String.format("Classes that implement or extend 'Builder' should reside in any package '%s'", new String[]{PACKAGE_ROOT + ".domain.."}));
}
