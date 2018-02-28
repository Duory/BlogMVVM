package com.example.makovvictor.blogmvvm.data.source;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.makovvictor.blogmvvm.data.model.Post;
import com.example.makovvictor.blogmvvm.data.source.local.PostDao;
import com.example.makovvictor.blogmvvm.data.source.remote.PostApiService;
import com.example.makovvictor.blogmvvm.util.AppExecutors;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

@Singleton
public class PostsRepository {

    private PostApiService postsApiService;
    private PostDao postDao;
    private final AppExecutors executors;

    @Inject
    public PostsRepository(PostApiService postApiService, PostDao postDao, AppExecutors executors) {
        this.postsApiService = postApiService;
        this.postDao = postDao;
        this.executors = executors;
    }

    public LiveData<List<Post>> getAllPosts() {

        refreshPosts(true);

        return postDao.getAllPosts();
    }

    private void refreshPosts(boolean isForceUpdate) {
        executors.diskIO().execute(() -> {
            if (isForceUpdate) {
                try {
                    Response response = postsApiService.getAllPosts().execute();
                    postDao.savePosts((List<Post>) response.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
