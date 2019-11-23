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

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import java.lang.annotation.Annotation;

/**
 * Classes with suffix rules. ArchUnit rules for classes that have a specific name.
 */
public class ClassesWithSuffix {

  public static ArchRule shouldBeAnnotatedWithAnnotationType(final String suffix, final Class<? extends Annotation> annotationType) {
    return ArchRuleDefinition.classes()
        .that()
        .haveSimpleNameEndingWith(suffix)
        .and()
        .areNotInterfaces()
        .and()
        .doNotHaveModifier(JavaModifier.ABSTRACT)
        .should()
        .beAnnotatedWith(annotationType)
        .because(String.format("Classes with suffix '%s' should be annotated with '@%s'", suffix, annotationType.getSimpleName()));
  }

  public static ArchRule shouldBeAssignableToClassOrInterface(final String suffix, final Class clazz) {
    return ArchRuleDefinition.classes()
        .that()
        .haveSimpleNameEndingWith(suffix)
        .and()
        .areNotInterfaces()
        .and()
        .doNotHaveModifier(JavaModifier.ABSTRACT)
        .should()
        .beAssignableTo(clazz)
        .because(String.format("Classes with suffix '%s' should implement or extend '%s'", suffix, clazz.getSimpleName()));
  }

  public static ArchRule shouldResideInAnySpecifiedPackage(final String suffix, final String... packages) {
    return ArchRuleDefinition.classes()
        .that()
        .haveSimpleNameEndingWith(suffix)
        .and()
        .areNotInterfaces()
        .and()
        .doNotHaveModifier(JavaModifier.ABSTRACT)
        .should()
        .resideInAnyPackage(packages)
        .because(String.format("Classes with suffix '%s' should reside in package '%s'", suffix, packages));
  }
}
