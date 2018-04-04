package com.example.makovvictor.blogmvvm.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.makovvictor.blogmvvm.ui.comments.editadd.CommentAddViewModel;
import com.example.makovvictor.blogmvvm.ui.posts.detail.PostDetailsViewModel;
import com.example.makovvictor.blogmvvm.ui.posts.editadd.PostEditAddViewModel;
import com.example.makovvictor.blogmvvm.ui.posts.view.PostsViewModel;

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
    @IntoMap
    @ViewModelKey(PostEditAddViewModel.class)
    abstract ViewModel postEditAddViewModel(PostEditAddViewModel postEditAddViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CommentAddViewModel.class)
    abstract ViewModel commentAddViewModel(CommentAddViewModel commentAddViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(BlogViewModelFactory factory);
}
