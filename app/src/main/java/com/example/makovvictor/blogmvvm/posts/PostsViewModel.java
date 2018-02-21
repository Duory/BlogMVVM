package com.example.makovvictor.blogmvvm.posts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.makovvictor.blogmvvm.data.model.Post;
import com.example.makovvictor.blogmvvm.data.source.PostsRepository;

import java.util.List;

import javax.inject.Inject;


public class PostsViewModel extends ViewModel {

    private LiveData<List<Post>> mPosts;

    private PostsRepository mPostsRepo;

    @Inject
    public PostsViewModel(PostsRepository postsRepo) {
        mPostsRepo = postsRepo;
    }

    public void init() {
        mPosts = mPostsRepo.getAllPosts();
    }

    public LiveData<List<Post>> getPosts() {
        return mPosts;
    }
}
