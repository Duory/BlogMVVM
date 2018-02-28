package com.example.makovvictor.blogmvvm.posts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.makovvictor.blogmvvm.data.model.Post;
import com.example.makovvictor.blogmvvm.data.source.PostsRepository;

import java.util.List;

import javax.inject.Inject;


public class PostsViewModel extends ViewModel {

    private LiveData<List<Post>> posts;

    private PostsRepository postsRepo;

    @Inject
    public PostsViewModel(PostsRepository postsRepo) {
        this.postsRepo = postsRepo;
    }

    public void init() {
        if (this.posts != null) {
            return;
        }
        this.posts = postsRepo.getAllPosts();
    }

    public LiveData<List<Post>> getPosts() {
        return this.posts;
    }
}
