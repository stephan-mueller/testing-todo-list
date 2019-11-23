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
package de.openknowledge.archetype.base.application.todo;

import org.apache.commons.lang3.ObjectUtils;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * A DTO that represents a new {@link Todo}.
 */
@Schema
public class NewTodo extends AbstractTodo {

  public NewTodo() {
    super();
  }

  @Override
  public Boolean getDone() {
    return ObjectUtils.defaultIfNull(super.getDone(), Boolean.FALSE);
  }

  @Override
  public String toString() {
    return "NewTodo{" +
           "title='" + getTitle() + '\'' +
           ", description='" + getDescription() + '\'' +
           ", dueDate='" + getDueDate() + '\'' +
           ", done=" + getDone() +
           '}';
  }
}