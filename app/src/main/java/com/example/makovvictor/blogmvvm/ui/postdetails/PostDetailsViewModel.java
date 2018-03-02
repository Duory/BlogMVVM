package com.example.makovvictor.blogmvvm.ui.postdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.makovvictor.blogmvvm.data.model.Comment;
import com.example.makovvictor.blogmvvm.data.model.Post;
import com.example.makovvictor.blogmvvm.data.source.CommentsRepository;
import com.example.makovvictor.blogmvvm.data.source.PostsRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by victor.makov on 02.03.18.
 */

public class PostDetailsViewModel extends ViewModel {

    private LiveData<Post> post;

    private LiveData<List<Comment>> comments;

    private PostsRepository postsRepo;

    private CommentsRepository commentsRepo;

    @Inject
    public PostDetailsViewModel(PostsRepository postsRepo, CommentsRepository commentsRepo) {
        this.postsRepo = postsRepo;
        this.commentsRepo = commentsRepo;
    }

    public void init(Integer postId){
        if (this.post != null) {
            return;
        }
        this.post = postsRepo.getPost(postId);
        this.comments = commentsRepo.getCommentsForPost(postId);
    }

    public LiveData<Post> getPost() {
        return this.post;
    }

    public LiveData<List<Comment>> getComments() {
        return this.comments;
    }
}
