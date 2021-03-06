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
package com.hotels.intellij.plugins.sharedviews.mocks;

import org.jetbrains.annotations.NotNull;

import com.intellij.mock.MockVirtualFile;

/**
 * MockVirtualFileWithPath.
 */
public class MockVirtualFileWithPath extends MockVirtualFile {

    private String path;

    public MockVirtualFileWithPath(String name) {
        super(name);
    }

    public MockVirtualFileWithPath(boolean directory, String name) {
        super(directory, name);
    }

    @NotNull
    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
