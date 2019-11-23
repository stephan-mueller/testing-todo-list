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
package de.openknowledge.projects.todolist.infrastructure.domain.entity;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import de.openknowledge.projects.testing.archunit.baserules.AnnotatedClasses;

import de.openknowledge.projects.todolist.ArchUnitTest;

import javax.persistence.Entity;

/**
 * ArchUnit rules for entity classes.
 */
public class ArchUnitEntityRules {

  @ArchTest
  public static final ArchRule entityClassesShouldBeAnnotatedWithEntityAnnotation =
      ArchRuleDefinition.classes()
          .that()
          .areAssignableTo(AbstractEntity.class)
          .and()
          .doNotHaveModifier(JavaModifier.ABSTRACT)
          .and()
          .haveSimpleNameNotContaining("Test")
          .should()
          .beAnnotatedWith(Entity.class)
          .because(String.format("Classes that implement or extend 'AbstractEntity' should be annotated with 'Entity'"));

  @ArchTest
  public static final ArchRule entityClassesShouldBeAssignableToAbstractEntity =
      AnnotatedClasses.shouldBeAssignableToClass(Entity.class, AbstractEntity.class);

  @ArchTest
  public static final ArchRule entityClassesShouldResideInDomainLayer =
      AnnotatedClasses.shouldResideInAnySpecifiedPackage(Entity.class, ArchUnitTest.PACKAGE_ROOT + ".domain..");
}
