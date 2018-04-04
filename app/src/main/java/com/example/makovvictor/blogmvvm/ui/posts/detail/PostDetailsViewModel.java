package com.example.makovvictor.blogmvvm.ui.posts.detail;

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

    private LiveData<Post> mPost;

    private LiveData<List<Comment>> mComments;

    private PostsRepository mPostsRepo;

    private CommentsRepository mCommentsRepo;

    @Inject
    public PostDetailsViewModel(PostsRepository postsRepo, CommentsRepository commentsRepo) {
        mPostsRepo = postsRepo;
        mCommentsRepo = commentsRepo;
    }

    public void init(Integer postId){
        if (this.mPost != null) {
            return;
        }
        mPost = mPostsRepo.getPost(postId);
        mComments = mCommentsRepo.getCommentsForPost(postId);
    }

    public LiveData<Post> getPost() {
        return mPost;
    }

    public LiveData<List<Comment>> getComments() {
        return mComments;
    }
}
