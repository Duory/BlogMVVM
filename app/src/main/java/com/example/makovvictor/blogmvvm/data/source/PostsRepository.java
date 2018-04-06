package com.example.makovvictor.blogmvvm.data.source;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.makovvictor.blogmvvm.data.model.Post;
import com.example.makovvictor.blogmvvm.data.source.local.PostDao;
import com.example.makovvictor.blogmvvm.data.source.remote.PostApiService;
import com.example.makovvictor.blogmvvm.util.AppExecutors;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
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

        refreshPosts();

        return postDao.getAllPosts();
    }
    private void refreshPosts() {
        executors.diskIO().execute(() -> {
            try {
                Response response = postsApiService.getAllPosts().execute();
                if (response.isSuccessful()) {
                    postDao.savePosts((List<Post>) response.body());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public LiveData<Post> getPost(Integer postId) {

        refreshPost(postId);

        return postDao.getPost(postId);
    }
    public void refreshPost(Integer postId) {
        executors.diskIO().execute(() -> {
            try {
                Response response = postsApiService.getPost(postId).execute();
                //Because remote data immutable our own resources really not created on server. So we do nothing if local data does not match remote.
                if (response.isSuccessful()) {
                    postDao.savePost((Post) response.body());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void addPost(Post post) {
        executors.diskIO().execute(() -> {
            try {
                Response response = postsApiService.addPost(post).execute();
                if (response.isSuccessful()) {
                    postDao.savePost((Post) response.body());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void updatePost(Post post) {
        executors.diskIO().execute(() -> {
            try {
                Response response = postsApiService.updatePost(post.getId(), post).execute();
                //Because remote data immutable our own resources really not created on server. So our created posts cant be updated on remote.
                if (response.isSuccessful()) {
                    postDao.savePost((Post) response.body());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
