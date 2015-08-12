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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.hotels.intellij.plugins.sharedviews.domain.SharedViewTreeNode;
import com.hotels.intellij.plugins.sharedviews.mocks.MockProject;
import com.hotels.intellij.plugins.sharedviews.mocks.MockPsiFile;
import com.hotels.intellij.plugins.sharedviews.mocks.MockPsiManager;
import com.hotels.intellij.plugins.sharedviews.mocks.MockVirtualFileManager;
import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.mock.MockApplication;
import com.intellij.mock.MockVirtualFile;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiManager;

public class SharedViewGroupNodeTest {

    private MockProject mockProject = new MockProject();
    private MockVirtualFileManager mockVirtualFileManager = new MockVirtualFileManager();
    MockPsiManager mockPsiManager = new MockPsiManager(mockProject);

    @Before
    public void setUp() throws Exception {
        Disposable parentDisposable = new Disposable() {
            @Override
            public void dispose() {
            }
        };

        MockApplication mockApplication = new MockApplication(parentDisposable);
        mockApplication.addComponent(VirtualFileManager.class, mockVirtualFileManager);

        ApplicationManager.setApplication(mockApplication, parentDisposable);

        mockProject.addComponent(PsiManager.class, mockPsiManager);
        mockProject.setBasePath(getClass().getClassLoader().getResource(".").getPath());
    }

    @Test
    public void testGetChildrenWithGroupNode() throws Exception {
        //given
        SharedViewTreeNode sharedViewTreeNodeRoot = getSharedViewTreeNodeGroupNode();

        //and
        SharedViewGroupNode sharedViewGroupNode = new SharedViewGroupNode(mockProject, sharedViewTreeNodeRoot, ViewSettings.DEFAULT);

        //when
        Collection<? extends AbstractTreeNode> children = sharedViewGroupNode.getChildren();

        //then
        assertThat(children.size(), is(1));
        assertThat(((ArrayList) children).get(0) instanceof SharedViewGroupNode, is(true));
    }

    @Test
    public void testGetChildrenWithViewNode() throws Exception {
        //given
        SharedViewTreeNode sharedViewTreeNodeRoot = getSharedViewTreeNodeViewNode();

        //and
        SharedViewGroupNode sharedViewGroupNode = new SharedViewGroupNode(mockProject, sharedViewTreeNodeRoot, ViewSettings.DEFAULT);

        //and
        MockVirtualFile mockVirtualFile = new MockVirtualFile(false, "any_file.anything");
        mockVirtualFileManager.addFileByUrl("file://" + getClass().getClassLoader().getResource(".").getPath() + File.separator + "any_file.anything", mockVirtualFile);

        //and
        MockPsiFile mockPsiFile = new MockPsiFile(mockVirtualFile);
        mockPsiManager.addFile(mockVirtualFile, mockPsiFile);

        //when
        Collection<? extends AbstractTreeNode> children = sharedViewGroupNode.getChildren();

        //then
        assertThat(children.size(), is(1));
        assertThat(((ArrayList) children).get(0) instanceof PsiFileNode, is(true));
        assertThat(((PsiFileNode) ((ArrayList) children).get(0)).getValue().getVirtualFile(), is((VirtualFile) mockVirtualFile));
    }

    @Test
    public void testUpdate() throws Exception {
        //given
        PresentationData presentationData = new PresentationData();

        //and
        SharedViewTreeNode sharedViewTreeNode = new SharedViewTreeNode("PresentableText");
        SharedViewGroupNode sharedViewGroupNode = new SharedViewGroupNode(mockProject, sharedViewTreeNode, ViewSettings.DEFAULT);

        //when
        sharedViewGroupNode.update(presentationData);

        //then
        assertThat(presentationData.getPresentableText(), is("PresentableText"));
        assertThat(presentationData.getIcon(true), is(AllIcons.Actions.Module));
    }

    private SharedViewTreeNode getSharedViewTreeNodeGroupNode() {
        SharedViewTreeNode sharedViewTreeNodeSubFolderFileAnyFile = new SharedViewTreeNode("any_file.anything");
        SharedViewTreeNode sharedViewTreeNodeSubFolderFile = new SharedViewTreeNode("subFolderFile", true);
        SharedViewTreeNode sharedViewTreeNodeSubFolder = new SharedViewTreeNode("subFolder");
        sharedViewTreeNodeSubFolderFile.addChild(sharedViewTreeNodeSubFolderFileAnyFile);
        sharedViewTreeNodeSubFolder.addChild(sharedViewTreeNodeSubFolderFile);

        return sharedViewTreeNodeSubFolder;
    }

    private SharedViewTreeNode getSharedViewTreeNodeViewNode() {
        SharedViewTreeNode sharedViewTreeNodeAnyFile = new SharedViewTreeNode("any_file.anything");
        SharedViewTreeNode sharedViewTreeNode = new SharedViewTreeNode("sharedViews", true);
        sharedViewTreeNode.addChild(sharedViewTreeNodeAnyFile);

        return sharedViewTreeNode;
    }
}