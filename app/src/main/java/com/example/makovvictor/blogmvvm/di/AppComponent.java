package com.example.makovvictor.blogmvvm.di;

import android.app.Application;

import com.example.makovvictor.blogmvvm.posts.PostsFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by victor.makov on 28.02.18.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }

    void inject(PostsFragment postsFragment);

}
