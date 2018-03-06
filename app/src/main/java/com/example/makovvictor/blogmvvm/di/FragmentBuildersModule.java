package com.example.makovvictor.blogmvvm.di;

import com.example.makovvictor.blogmvvm.ui.postdetails.PostDetailsFragment;
import com.example.makovvictor.blogmvvm.ui.posteditadd.PostEditAddFragment;
import com.example.makovvictor.blogmvvm.ui.posts.PostsFragment;

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
}