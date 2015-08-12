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
package com.hotels.intellij.plugins.sharedviews.domain;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * A SharedViewTreeNode is a basic tree node which holds the structure of the shared views files.
 */
public class SharedViewTreeNode {
    private final String data;
    private boolean view;
    private final List<SharedViewTreeNode> sharedViewTreeNodes;

    /**
     * Create a SharedViewTreeNode.
     *
     * @param data {@link java.lang.String}
     */
    public SharedViewTreeNode(String data) {
        this.data = data;
        this.sharedViewTreeNodes = Lists.newArrayList();
    }

    /**
     * Create a SharedViewTreeNode.
     *
     * @param data {@link java.lang.String}
     * @param view {@link java.lang.Boolean}
     */
    public SharedViewTreeNode(String data, boolean view) {
        this.data = data;
        this.view = view;
        this.sharedViewTreeNodes = Lists.newArrayList();
    }

    /**
     * Return the relative path of the file pointed to by this node.
     *
     * @return {@link java.lang.String}
     */
    public String getData() {
        return data;
    }

    /**
     * Add a child to this SharedViewTreeNode.
     *
     * @param sharedViewTreeNode {@link com.hotels.intellij.plugins.sharedviews.domain.SharedViewTreeNode}
     */
    public void addChild(SharedViewTreeNode sharedViewTreeNode) {
        this.sharedViewTreeNodes.add(sharedViewTreeNode);
    }

    /**
     * Return the children of this SharedViewTreeNode.
     *
     * @return {@link java.util.List}
     */
    public List<SharedViewTreeNode> getChildren() {
        return sharedViewTreeNodes;
    }

    /**
     * Used to determine whether this SharedViewTreeNode holds any files.
     *
     * @return {@link java.lang.Boolean}
     */
    public boolean isView() {
        return view;
    }
}
