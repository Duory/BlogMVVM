package com.example.makovvictor.blogmvvm.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.example.makovvictor.blogmvvm.data.source.local.BlogDatabase;
import com.example.makovvictor.blogmvvm.data.source.local.CommentDao;
import com.example.makovvictor.blogmvvm.data.source.local.PostDao;
import com.example.makovvictor.blogmvvm.data.source.remote.CommentApiService;
import com.example.makovvictor.blogmvvm.data.source.remote.PostApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by victor.makov on 28.02.18.
 */

@Module(includes = ViewModelModule.class)
class AppModule {

    private static final String baseUrl = "https://jsonplaceholder.typicode.com/";

    @Singleton
    @Provides
    PostApiService providePostApiService() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PostApiService.class);
    }

    @Singleton
    @Provides
    CommentApiService provideCommentApiService() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CommentApiService.class);
    }

    @Singleton
    @Provides
    BlogDatabase provideBlogDb(Application application) {
        return Room.databaseBuilder(application, BlogDatabase.class, "blog.db").build();
    }

    @Singleton
    @Provides
    PostDao providePostDao(BlogDatabase blogDatabase) {
        return blogDatabase.postDao();
    }

    @Singleton
    @Provides
    CommentDao provideCommentDao(BlogDatabase blogDatabase) {
        return blogDatabase.commentDao();
    }

}
