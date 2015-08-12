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

import java.util.Map;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Maps;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFileManagerListener;
import com.intellij.openapi.vfs.VirtualFileSystem;

/**
 * MockVirtualFileManager.
 */
public class MockVirtualFileManager extends VirtualFileManager {

    private Map<String, VirtualFile> urlVirtualFileMap = Maps.newHashMap();

    @Override
    public VirtualFileSystem getFileSystem(String protocol) {
        return null;
    }

    @Override
    public long syncRefresh() {
        return 0;
    }

    @Override
    public long asyncRefresh(@Nullable Runnable postAction) {
        return 0;
    }

    @Override
    public void refreshWithoutFileWatcher(boolean asynchronous) {

    }

    @Nullable
    @Override
    public VirtualFile findFileByUrl(@NonNls @NotNull String url) {
        return urlVirtualFileMap.get(url);
    }

    public VirtualFile addFileByUrl(@NonNls @NotNull String url, VirtualFile virtualFile) {
        return urlVirtualFileMap.put(url, virtualFile);
    }

    @Nullable
    @Override
    public VirtualFile refreshAndFindFileByUrl(@NotNull String url) {
        return null;
    }

    @Override
    public void addVirtualFileListener(@NotNull VirtualFileListener listener) {

    }

    @Override
    public void addVirtualFileListener(@NotNull VirtualFileListener listener, @NotNull Disposable parentDisposable) {

    }

    @Override
    public void removeVirtualFileListener(@NotNull VirtualFileListener listener) {

    }

    @Override
    public void addVirtualFileManagerListener(@NotNull VirtualFileManagerListener listener) {

    }

    @Override
    public void addVirtualFileManagerListener(@NotNull VirtualFileManagerListener listener, @NotNull Disposable parentDisposable) {

    }

    @Override
    public void removeVirtualFileManagerListener(@NotNull VirtualFileManagerListener listener) {

    }

    @Override
    public void notifyPropertyChanged(@NotNull VirtualFile virtualFile, @NotNull String property, Object oldValue, Object newValue) {

    }

    @Override
    public long getModificationCount() {
        return 0;
    }
}
