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

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import com.intellij.ide.SelectInTarget;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.AbstractProjectViewPSIPane;
import com.intellij.ide.projectView.impl.ProjectAbstractTreeStructureBase;
import com.intellij.ide.projectView.impl.ProjectTreeStructure;
import com.intellij.ide.projectView.impl.ProjectViewTree;
import com.intellij.ide.util.treeView.AbstractTreeBuilder;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.ide.util.treeView.AbstractTreeUpdater;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;

/**
 * The SharedViewPane is responsible for creating the link in the Project drop down and describing that the
 * {@link com.hotels.intellij.plugins.sharedviews.pane.SharedViewProjectNode} should be used as the project node.
 *
 * The {@link com.intellij.ide.projectView.impl.AbstractProjectViewPSIPane} is extended as the file based links are {@link com.intellij.ide.projectView.impl.nodes.PsiFileNode}s.
 */
public class SharedViewPane extends AbstractProjectViewPSIPane {
    @NonNls
    public static final String TITLE = "SharedViews";
    @NonNls
    public static final String ID = "SharedViewPane";

    public static final Icon HCOM_LOGO = IconLoader.getIcon("/resources/hcom-logo.png");

    /**
     * Create a SharedViewPane.
     *
     * @param project {@link com.intellij.openapi.project.Project}
     */
    protected SharedViewPane(Project project) {
        super(project);
    }

    /**
     * Return the name used in the Project drop down menu.
     *
     * @return {@link java.lang.String}
     */
    @Override
    public String getTitle() {
        return TITLE;
    }

    /**
     * Return the icon used in the Project drop down menu.
     *
     * @return {@link javax.swing.Icon}
     */
    @Override
    public Icon getIcon() {
        return HCOM_LOGO;
    }

    /**
     * Return the ID of the SharedViewPane.
     *
     * @return {@link java.lang.String}
     */
    @NotNull
    @Override
    public String getId() {
        return ID;
    }

    @Override
    protected ProjectAbstractTreeStructureBase createStructure() {
        return new ProjectTreeStructure(myProject, ID) {
            @Override
            protected AbstractTreeNode createRoot(final Project project, ViewSettings settings) {
                return new SharedViewProjectNode(project, settings);
            }
        };
    }

    @Override
    protected ProjectViewTree createTree(DefaultTreeModel treeModel) {
        return new ProjectViewTree(myProject, treeModel) {
            public String toString() {
                return getTitle() + " " + super.toString();
            }

            @Override
            public DefaultMutableTreeNode getSelectedNode() {
                return SharedViewPane.this.getSelectedNode();
            }
        };
    }

    @Override
    protected AbstractTreeUpdater createTreeUpdater(AbstractTreeBuilder treeBuilder) {
        return new SharedViewTreeUpdater(treeBuilder);
    }

    /**
     * Return the index into the drop down menu.
     *
     * @return {@link java.lang.Integer}
     */
    @Override
    public int getWeight() {
        //Index into the project drop down menu.
        return 2;
    }

    /**
     * Build the SelectInTarget object to be used.
     *
     * @return {@link com.intellij.ide.SelectInTarget}
     */
    @Override
    public SelectInTarget createSelectInTarget() {
        return new SharedViewPaneSelectInTarget(myProject);
    }
}
