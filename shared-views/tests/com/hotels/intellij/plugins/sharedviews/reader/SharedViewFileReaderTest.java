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
package com.hotels.intellij.plugins.sharedviews.reader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.hotels.intellij.plugins.sharedviews.domain.SharedViewTreeNode;
import com.hotels.intellij.plugins.sharedviews.mocks.MockProject;
import com.hotels.intellij.plugins.sharedviews.mocks.MockVirtualFileWithPath;
import com.intellij.mock.MockVirtualFile;
import com.intellij.openapi.vfs.VirtualFile;

public class SharedViewFileReaderTest {

    private MockProject mockProject = new MockProject();

    @Before
    public void setUp() throws Exception {
        mockProject.setBaseDir(createRootVirtualFile());
    }

    @Test
    public void testGetSharedViewsRootNodeForProject() throws Exception {
        //given
        mockProject.setBaseDir(createRootVirtualFile());

        //when
        SharedViewTreeNode sharedViewsRootNodeForProject = SharedViewFileReader.getSharedViewsRootNodeForProject(mockProject);

        //then
        assertThat(sharedViewsRootNodeForProject.getData(), is("root"));
        assertThat(sharedViewsRootNodeForProject.getChildren().size(), is(2));
        assertThat(sharedViewsRootNodeForProject.getChildren().get(0).getData(), is("sharedViews"));
        assertThat(sharedViewsRootNodeForProject.getChildren().get(0).getChildren().size(), is(3));
        assertThat(sharedViewsRootNodeForProject.getChildren().get(0).getChildren().get(0).getData(), is("any_java_file.java"));
        assertThat(sharedViewsRootNodeForProject.getChildren().get(0).getChildren().get(1).getData(), is("any_properties_file.properties"));
        assertThat(sharedViewsRootNodeForProject.getChildren().get(0).getChildren().get(2).getData(), is("any_file.anything"));
        assertThat(sharedViewsRootNodeForProject.getChildren().get(1).getData(), is("subfolder"));
        assertThat(sharedViewsRootNodeForProject.getChildren().get(1).getChildren().size(), is(1));
        assertThat(sharedViewsRootNodeForProject.getChildren().get(1).getChildren().get(0).getData(), is("subFolderFile"));
        assertThat(sharedViewsRootNodeForProject.getChildren().get(1).getChildren().get(0).getChildren().size(), is(1));
        assertThat(sharedViewsRootNodeForProject.getChildren().get(1).getChildren().get(0).getChildren().get(0).getData(), is("any_file.anything"));
    }

    private VirtualFile createRootVirtualFile() {
        MockVirtualFileWithPath mockVirtualFileWithPath = new MockVirtualFileWithPath("resources");
        mockVirtualFileWithPath.addChild(createSharedViewsFolderVirtualFile());

        return mockVirtualFileWithPath;
    }

    private MockVirtualFile createSharedViewsFolderVirtualFile() {
        MockVirtualFileWithPath sharedviewsFolder = new MockVirtualFileWithPath(true, "sharedviews");
        MockVirtualFileWithPath sharedViewsFile = new MockVirtualFileWithPath("sharedViews.view");
        sharedViewsFile.setPath(getClass().getClassLoader().getResource("resources/sharedviews/sharedViews.view").getPath());
        sharedviewsFolder.addChild(sharedViewsFile);

        MockVirtualFileWithPath subFolder = new MockVirtualFileWithPath(true, "subfolder");
        MockVirtualFileWithPath subFolderFile = new MockVirtualFileWithPath("subFolderFile.view");
        subFolderFile.setPath(getClass().getClassLoader().getResource("resources/sharedviews/subfolder/subFolderFile.view").getPath());
        subFolder.addChild(subFolderFile);
        sharedviewsFolder.addChild(subFolder);

        return sharedviewsFolder;
    }
}

