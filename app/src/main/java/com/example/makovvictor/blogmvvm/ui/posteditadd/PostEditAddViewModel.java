package com.example.makovvictor.blogmvvm.ui.posteditadd;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.makovvictor.blogmvvm.data.model.Post;
import com.example.makovvictor.blogmvvm.data.source.PostsRepository;

import javax.inject.Inject;

/**
 * Created by victor.makov on 07.03.18.
 */

public class PostEditAddViewModel extends ViewModel {

    private LiveData<Post> post;

    private PostsRepository postsRepo;

    @Inject
    public PostEditAddViewModel(PostsRepository postsRepo) {
        this.postsRepo = postsRepo;
    }

    public void init(Integer postId) {
        if (this.post != null) {
            return;
        }
        this.post = postsRepo.getPost(postId);
    }

    public LiveData<Post> getPost() {
        return this.post;
    }

    public void addPost(String postTitle, String postBody, int currentUserId) {
        Post post = new Post();
        post.setTitle(postTitle);
        post.setBody(postBody);
        post.setUserId(currentUserId);
        postsRepo.addPost(post);
    }

    public void updatePost(int postId, String postTitle, String postBody, int currentUserId) {
        Post post = new Post();
        post.setId(postId);
        post.setTitle(postTitle);
        post.setBody(postBody);
        post.setUserId(currentUserId);
        postsRepo.updatePost(post);
    }
}
