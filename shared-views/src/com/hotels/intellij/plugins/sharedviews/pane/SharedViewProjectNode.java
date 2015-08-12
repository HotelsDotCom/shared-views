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

import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.Lists;
import com.hotels.intellij.plugins.sharedviews.domain.SharedViewTreeNode;
import com.hotels.intellij.plugins.sharedviews.reader.SharedViewFileReader;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * SharedViewProjectNode.
 */
public class SharedViewProjectNode extends ProjectViewNode<Project> {


    /**
     * Creates an instance of the project view node.
     *
     * @param project      the project containing the node.
     * @param viewSettings the settings of the project view.
     */
    protected SharedViewProjectNode(Project project, ViewSettings viewSettings) {
        super(project, project, viewSettings);
    }

    /**
     * Build the children of this SharedViewProjectNode from the project files.
     *
     * @return {@link java.util.Collection}
     */
    @NotNull
    @Override
    public Collection<? extends AbstractTreeNode> getChildren() {
        final List<AbstractTreeNode> children = Lists.newArrayList();

        SharedViewTreeNode rootNode = SharedViewFileReader.getSharedViewsRootNodeForProject(myProject);
        if (rootNode.getChildren().size() > 0) {

            for (SharedViewTreeNode sharedView : rootNode.getChildren()) {
                SharedViewGroupNode sharedViewGroupNode = new SharedViewGroupNode(getProject(), sharedView, getSettings());
                children.add(sharedViewGroupNode);
            }
        }

        return children;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(@NotNull VirtualFile file) {
        return false;
    }

    /**
     * Draw a SharedViewProjectNode.
     *
     * @param presentation {@link com.intellij.ide.projectView.PresentationData}
     */
    @Override
    protected void update(PresentationData presentation) {

    }
}
