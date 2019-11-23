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
package de.openknowledge.projects.todolist.infrastructure.microprofiles.health;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import de.openknowledge.projects.testing.archunit.baserules.InheritedClasses;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

/**
 * ArchUnit rules for HealthCheck classes
 */
public class ArchUnitMicroprofileHealthRules {

  @ArchTest
  public static final ArchRule healthCheckClassesShouldBeAnnotatedWithApplicationScoped =
      InheritedClasses.shouldBeAnnotatedWithAnnotationType(HealthCheck.class, ApplicationScoped.class);

  @ArchTest
  public static final ArchRule healthCheckClassesShouldBeAnnotatedWithLivenessOrReadiness =
      ArchRuleDefinition.classes()
          .that()
          .areAssignableTo(HealthCheck.class)
          .and()
          .doNotHaveModifier(JavaModifier.ABSTRACT)
          .should()
          .beAnnotatedWith(Liveness.class)
          .orShould()
          .beAnnotatedWith(Readiness.class)
          .because(String.format("Classes that implement or extend 'HealthCheck' should be annotated with 'Liveness' or 'Readiness'"));

  @ArchTest
  public static final ArchRule healthCheckClassesShouldNotBeAnnotatedWithHealth =
      ArchRuleDefinition.classes()
          .that()
          .areAssignableTo(HealthCheck.class)
          .and()
          .doNotHaveModifier(JavaModifier.ABSTRACT)
          .should()
          .notBeAnnotatedWith(Health.class)
          .because(String.format("Classes that implement or extend 'HealthCheck' should not be annotated with 'Health' because of deprecation"));

  @ArchTest
  public static final ArchRule healthCheckClassesShouldBeAssignableToHealthCheckInterface =
      ArchRuleDefinition.classes()
          .that()
          .areAnnotatedWith(Liveness.class)
          .or()
          .areAnnotatedWith(Readiness.class)
          .should()
          .beAssignableTo(HealthCheck.class)
          .because(String.format("Classes that are annotated with 'Liveness' or 'Readiness' should be assignable to class 'HealthCheck'"));

  @ArchTest
  public static final ArchRule healthCheckClassesShouldHaveNameWithSuffixHealthCheck =
      ArchRuleDefinition.classes()
          .that()
          .areAnnotatedWith(Liveness.class)
          .or()
          .areAnnotatedWith(Readiness.class)
          .should()
          .haveSimpleNameEndingWith("HealthCheck")
          .because(String.format("Classes that are annotated with 'Liveness' or 'Readiness' should be named with the suffix 'HealthCheck'"));
}
