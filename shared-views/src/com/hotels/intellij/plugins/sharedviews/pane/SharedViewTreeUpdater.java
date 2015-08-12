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

import org.jetbrains.annotations.NotNull;

import com.intellij.ide.util.treeView.AbstractTreeBuilder;
import com.intellij.ide.util.treeView.AbstractTreeUpdater;

/**
 * The {@link com.intellij.ide.util.treeView.AbstractTreeUpdater} is extended as we can use the default behaviour.
 */
public class SharedViewTreeUpdater extends AbstractTreeUpdater {

    /**
     * Create a SharedViewTreeUpdater.
     *
     * @param treeBuilder {@link com.intellij.ide.util.treeView.AbstractTreeUpdater}
     */
    public SharedViewTreeUpdater(@NotNull AbstractTreeBuilder treeBuilder) {
        super(treeBuilder);
    }
}
