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

import java.lang.annotation.Annotation;

/**
 * Annotated classes rules. ArchUnit rules for classes that are annotated with a specific annotation.
 */
public class AnnotatedClasses {

  public static ArchRule shouldBeAssignableToClass(final Class<? extends Annotation> annotationType, final Class clazz) {
    return ArchRuleDefinition.classes()
        .that()
        .areAnnotatedWith(annotationType)
        .should()
        .beAssignableTo(clazz)
        .because(String.format("Classes that are annotated with '@%s' should be assignable to class '%s'", annotationType.getSimpleName(), clazz.getSimpleName()));
  }

  public static ArchRule shouldHaveName(final Class<? extends Annotation> annotationType, final String name) {
    return ArchRuleDefinition.classes()
        .that()
        .areAnnotatedWith(annotationType)
        .should()
        .haveSimpleName(name)
        .because(String.format("Classes that are annotated with '@%s' should be named '%s'", annotationType.getSimpleName(), name));
  }

  public static ArchRule shouldHaveNameWithSuffix(final Class<? extends Annotation> annotationType, final String suffix) {
    return ArchRuleDefinition.classes()
        .that()
        .areAnnotatedWith(annotationType)
        .should()
        .haveSimpleNameEndingWith(suffix)
        .because(String.format("Classes that are annotated with '@%s' should be named with the suffix '%s'", annotationType.getSimpleName(), suffix));
  }

  public static ArchRule shouldResideInAnySpecifiedPackage(final Class<? extends Annotation> annotationType, final String... packages) {
    return ArchRuleDefinition.classes()
        .that()
        .areAnnotatedWith(annotationType)
        .should()
        .resideInAnyPackage(packages)
        .because(String.format("Classes that are annotated with '@%s' should reside in package '%s'", annotationType.getSimpleName(), packages));
  }
}
