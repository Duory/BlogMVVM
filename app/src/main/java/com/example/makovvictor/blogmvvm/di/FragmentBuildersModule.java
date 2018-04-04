package com.example.makovvictor.blogmvvm.di;

import com.example.makovvictor.blogmvvm.ui.comments.editadd.CommentAddFragment;
import com.example.makovvictor.blogmvvm.ui.posts.detail.PostDetailsFragment;
import com.example.makovvictor.blogmvvm.ui.posts.editadd.PostEditAddFragment;
import com.example.makovvictor.blogmvvm.ui.posts.view.PostsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by victor.makov on 01.03.18.
 */

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();
    @ContributesAndroidInjector
    abstract PostDetailsFragment contributePostDetailsFragment();
    @ContributesAndroidInjector
    abstract PostEditAddFragment contributePostEditAddFragment();
    @ContributesAndroidInjector
    abstract CommentAddFragment contributeCommentAddFragment();
}