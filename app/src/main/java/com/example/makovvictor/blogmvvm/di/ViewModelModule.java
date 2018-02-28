package com.example.makovvictor.blogmvvm.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.makovvictor.blogmvvm.posts.PostsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by victor.makov on 28.02.18.
 */

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    abstract ViewModel bindPostsViewModel(PostsViewModel postsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(BlogViewModelFactory factory);
}
