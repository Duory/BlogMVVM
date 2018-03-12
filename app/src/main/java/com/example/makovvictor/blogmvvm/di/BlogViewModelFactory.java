package com.example.makovvictor.blogmvvm.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Created by victor.makov on 28.02.18.
 */

@Singleton
public class BlogViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends  ViewModel>, Provider<ViewModel>> viewModels;

    @Inject
    public BlogViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels) {
        this.viewModels = viewModels;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> viewModelProvider = viewModels.get(modelClass);
        if (viewModelProvider == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : viewModels.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    viewModelProvider = entry.getValue();
                    break;
                }
            }
        }
        if (viewModelProvider == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) viewModelProvider.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
