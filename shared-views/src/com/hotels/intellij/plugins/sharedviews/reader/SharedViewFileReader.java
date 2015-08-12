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

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jetbrains.annotations.NonNls;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.hotels.intellij.plugins.sharedviews.domain.SharedViewTreeNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * The SharedViewFileReader is responsible for building the {@link com.hotels.intellij.plugins.sharedviews.domain.SharedViewTreeNode}s from the project files.
 * <p/>
 * The project is considered to have shared views if there is a sharedviews folder at the base of the project. Each file with the suffix of .view will be read recursively
 * from the folder and a shared view created.
 */
public class SharedViewFileReader {
    private static final Logger LOG = Logger.getInstance("com.hotels.intellij.plugins.sharedviews.reader.SharedViewFileReader");

    @NonNls
    public static final String SHARED_VIEWS_DIRECTORY = "sharedviews";

    @NonNls
    public static final String SHARED_VIEW_SUFFIX = ".view";

    /**
     * Create the root SharedViewTreeNode and children from the project files.
     *
     * @param project {@link com.intellij.openapi.project.Project}
     * @return {@link com.hotels.intellij.plugins.sharedviews.domain.SharedViewTreeNode}
     */
    public static SharedViewTreeNode getSharedViewsRootNodeForProject(Project project) {
        SharedViewTreeNode rootNode = new SharedViewTreeNode("root");

        VirtualFile sharedViewFolder = project.getBaseDir().findChild(SHARED_VIEWS_DIRECTORY);
        if (sharedViewFolder != null && sharedViewFolder.isDirectory()) {
            processFolder(sharedViewFolder, rootNode);
        }

        return rootNode;
    }

    private static void processFolder(VirtualFile folder, SharedViewTreeNode node) {
        for (VirtualFile virtualFile : folder.getChildren()) {
            if (virtualFile.isDirectory()) {
                SharedViewTreeNode folderSharedViewTreeNode = new SharedViewTreeNode(virtualFile.getName());
                node.addChild(folderSharedViewTreeNode);
                processFolder(virtualFile, folderSharedViewTreeNode);
            } else if (virtualFile.getName().endsWith(SHARED_VIEW_SUFFIX)) {
                SharedViewTreeNode fileSharedViewTreeNode = new SharedViewTreeNode(virtualFile.getName().replace(SHARED_VIEW_SUFFIX, ""), true);
                node.addChild(fileSharedViewTreeNode);
                processFile(virtualFile, fileSharedViewTreeNode);
            }
        }
    }

    private static void processFile(VirtualFile virtualFile, SharedViewTreeNode node) {
        try {
            List<String> lines = Files.readLines(new File(virtualFile.getPath()), Charsets.UTF_8);

            for (String line : lines) {
                node.addChild(new SharedViewTreeNode(line));
            }
        } catch (IOException e) {
            LOG.error("Failed to read shared view from [" + virtualFile.getPath() + "].");
        }
    }
}
