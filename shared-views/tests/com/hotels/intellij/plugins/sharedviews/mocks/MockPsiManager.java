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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Maps;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiTreeChangeListener;
import com.intellij.psi.util.PsiModificationTracker;

/**
 * MockPsiManager.
 */
public class MockPsiManager extends PsiManager {

    private final Project project;
    private Map<VirtualFile, PsiFile> virtualFilePsiFileMap = Maps.newHashMap();

    public MockPsiManager(Project project) {
        this.project = project;
    }

    @NotNull
    @Override
    public Project getProject() {
        return project;
    }

    @Nullable
    @Override
    public PsiFile findFile(@NotNull VirtualFile file) {
        return virtualFilePsiFileMap.get(file);
    }

    public PsiFile addFile(@NotNull VirtualFile file, PsiFile psiFile) {
        return virtualFilePsiFileMap.put(file, psiFile);
    }

    @Nullable
    @Override
    public FileViewProvider findViewProvider(@NotNull VirtualFile file) {
        return null;
    }

    @Nullable
    @Override
    public PsiDirectory findDirectory(@NotNull VirtualFile file) {
        return null;
    }

    @Override
    public boolean areElementsEquivalent(@Nullable PsiElement element1, @Nullable PsiElement element2) {
        return false;
    }

    @Override
    public void reloadFromDisk(@NotNull PsiFile file) {

    }

    @Override
    public void addPsiTreeChangeListener(@NotNull PsiTreeChangeListener listener) {

    }

    @Override
    public void addPsiTreeChangeListener(@NotNull PsiTreeChangeListener listener, @NotNull Disposable parentDisposable) {

    }

    @Override
    public void removePsiTreeChangeListener(@NotNull PsiTreeChangeListener listener) {

    }

    @NotNull
    @Override
    public PsiModificationTracker getModificationTracker() {
        return null;
    }

    @Override
    public void startBatchFilesProcessingMode() {

    }

    @Override
    public void finishBatchFilesProcessingMode() {

    }

    @Override
    public boolean isDisposed() {
        return false;
    }

    @Override
    public void dropResolveCaches() {

    }

    @Override
    public boolean isInProject(@NotNull PsiElement element) {
        return false;
    }
}
