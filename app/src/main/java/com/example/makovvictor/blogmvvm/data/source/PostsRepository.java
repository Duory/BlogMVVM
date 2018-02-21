package com.example.makovvictor.blogmvvm.data.source;

import android.arch.lifecycle.LiveData;

import com.example.makovvictor.blogmvvm.data.model.Post;
import com.example.makovvictor.blogmvvm.data.source.remote.PostsRemoteDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PostsRepository {

    private PostsRemoteDataSource mRemoteDataSource;

    @Inject
    public PostsRepository(PostsRemoteDataSource postsRemoteDataSource) {
        mRemoteDataSource = postsRemoteDataSource;
    }

    public LiveData<List<Post>> getAllPosts() {
        return mRemoteDataSource.getAllPosts();
    }


}
