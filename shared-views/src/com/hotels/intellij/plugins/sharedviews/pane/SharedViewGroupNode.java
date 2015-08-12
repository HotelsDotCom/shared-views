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

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.hotels.intellij.plugins.sharedviews.domain.SharedViewTreeNode;
import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

/**
 * A SharedViewGroupNode is the representation of a logical group of project files. These may be any type of file from any package.
 */
public class SharedViewGroupNode extends ProjectViewNode<SharedViewTreeNode> {

    /**
     * Creates an instance of the shared view group node.
     *
     * @param project            {@link com.intellij.openapi.project.Project}
     * @param sharedViewTreeNode {@link com.hotels.intellij.plugins.sharedviews.domain.SharedViewTreeNode}
     * @param viewSettings       {@link com.intellij.ide.projectView.ViewSettings}
     */
    protected SharedViewGroupNode(Project project, SharedViewTreeNode sharedViewTreeNode, ViewSettings viewSettings) {
        super(project, sharedViewTreeNode, viewSettings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(@NotNull VirtualFile file) {
        return false;
    }

    /**
     * Build the children of this group node.
     *
     * @return {@link java.util.Collection}
     */
    @NotNull
    @Override
    public Collection<? extends AbstractTreeNode> getChildren() {
        List<? extends AbstractTreeNode> abstractTreeNodes;

        if (getValue().isView()) {
            List<PsiFileNode> psiFileNodes = Lists.newArrayList();

            VirtualFileManager virtualFileManager = VirtualFileManager.getInstance();
            PsiManager psiManager = PsiManager.getInstance(getProject());
            String basePath = "file://" + getProject().getBasePath() + File.separator;

            for (SharedViewTreeNode sharedViewTreeNode : getValue().getChildren()) {
                String file = sharedViewTreeNode.getData();
                Optional<VirtualFile> virtualFile = Optional.fromNullable(virtualFileManager.findFileByUrl(basePath + file));

                if (virtualFile.isPresent()) {
                    PsiFile psiFile = psiManager.findFile(virtualFile.get());
                    PsiFileNode psiFileNode = new PsiFileNode(getProject(), psiFile, getSettings());
                    psiFileNodes.add(psiFileNode);
                }
            }

            abstractTreeNodes = psiFileNodes;
        } else {
            List<SharedViewGroupNode> sharedViewGroupNodes = Lists.newArrayList();

            for (SharedViewTreeNode sharedViewTreeNode : getValue().getChildren()) {
                SharedViewGroupNode sharedViewGroupNode = new SharedViewGroupNode(getProject(), sharedViewTreeNode, getSettings());
                sharedViewGroupNodes.add(sharedViewGroupNode);
            }

            abstractTreeNodes = sharedViewGroupNodes;
        }

        return abstractTreeNodes;
    }

    /**
     * Draw a SharedViewGroupNode.
     *
     * @param presentation {@link com.intellij.ide.projectView.PresentationData}
     */
    @Override
    protected void update(PresentationData presentation) {
        presentation.setPresentableText(getValue().getData());
        presentation.setIcon(AllIcons.Actions.Module);
    }
}
