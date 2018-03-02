package com.example.makovvictor.blogmvvm.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.makovvictor.blogmvvm.ui.postdetails.PostDetailsViewModel;
import com.example.makovvictor.blogmvvm.ui.posts.PostsViewModel;

import javax.inject.Scope;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by victor.makov on 28.02.18.
 */

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    abstract ViewModel postsViewModel(PostsViewModel postsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailsViewModel.class)
    abstract ViewModel postDetailsViewModel(PostDetailsViewModel postDetailsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(BlogViewModelFactory factory);
}
