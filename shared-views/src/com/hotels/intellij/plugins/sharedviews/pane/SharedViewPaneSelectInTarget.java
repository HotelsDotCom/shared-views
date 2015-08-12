/**
 * Copyright 2015 Expedia Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hotels.intellij.plugins.sharedviews.pane;

import org.jetbrains.annotations.Nullable;

import com.intellij.ide.StandardTargetWeights;
import com.intellij.ide.impl.ProjectViewSelectInTarget;
import com.intellij.openapi.project.Project;

/**
 * The {@link com.intellij.ide.impl.ProjectViewSelectInTarget} is extended as we can use the default project behaviour.
 */
public class SharedViewPaneSelectInTarget extends ProjectViewSelectInTarget {

    /**
     * Create a SharedViewPaneSelectInTarget.
     *
     * @param project {@link com.intellij.openapi.project.Project}
     */
    public SharedViewPaneSelectInTarget(Project project) {
        super(project);
    }

    /**
     * Return the title of the {@link com.hotels.intellij.plugins.sharedviews.pane.SharedViewPane}.
     *
     * @return {@link java.lang.String}
     */
    @Override
    public String toString() {
        return SharedViewPane.TITLE;
    }

    /**
     * Return the id of the {@link com.hotels.intellij.plugins.sharedviews.pane.SharedViewPane}.
     *
     * @return {@link java.lang.String}
     */
    @Nullable
    @Override
    public String getMinorViewId() {
        return SharedViewPane.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getWeight() {
        return StandardTargetWeights.FAVORITES_WEIGHT;
    }
}
