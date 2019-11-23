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
 * Inherited classes rules. ArchUnit rules for classes that implement or extend a specific class.
 */
public class InheritedClasses {

  public static ArchRule shouldBeAnnotatedWithAnnotationType(final Class clazz, final Class<? extends Annotation> annotationType) {
    return ArchRuleDefinition.classes()
        .that()
        .areAssignableTo(clazz)
        .and()
        .doNotHaveModifier(JavaModifier.ABSTRACT)
        .should()
        .beAnnotatedWith(annotationType)
        .because(String.format("Classes that implement or extend '%s' should be annotated with '@%s'", clazz.getSimpleName(), annotationType.getSimpleName()));
  }

  public static ArchRule shouldHaveName(final Class clazz, final String name) {
    return ArchRuleDefinition.classes()
        .that()
        .areAssignableTo(clazz)
        .should()
        .haveSimpleName(name)
        .because(String.format("Classes that implement or extend '@%s' should be named '%s'", clazz.getSimpleName(), name));
  }

  public static ArchRule shouldHaveNameWithSuffix(final Class clazz, final String suffix) {
    return ArchRuleDefinition.classes()
        .that()
        .areAssignableTo(clazz)
        .should()
        .haveSimpleNameEndingWith(suffix)
        .because(String.format("Classes that implement or extend '%s' should be named with the suffix %s", clazz.getSimpleName(), suffix));
  }

  public static ArchRule shouldResideInSpecifiedPackages(final Class clazz, final String... packages) {
    return ArchRuleDefinition.classes()
        .that()
        .areAssignableTo(clazz)
        .and()
        .areNotInterfaces()
        .and()
        .doNotHaveModifier(JavaModifier.ABSTRACT)
        .should()
        .resideInAnyPackage(packages)
        .because(String.format("Classes that implement or extend '%s' should reside in package '%s'", clazz.getSimpleName(), packages));
  }
}
