package com.example.makovvictor.blogmvvm.data.source;

import android.arch.lifecycle.LiveData;

import com.example.makovvictor.blogmvvm.data.model.Comment;
import com.example.makovvictor.blogmvvm.data.source.local.CommentDao;
import com.example.makovvictor.blogmvvm.data.source.remote.CommentApiService;
import com.example.makovvictor.blogmvvm.util.AppExecutors;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by victor.makov on 02.03.18.
 */

public class CommentsRepository {

    private CommentApiService commentApiService;

    private CommentDao commentDao;

    private final AppExecutors executors;

    @Inject
    CommentsRepository(CommentApiService commentApiService, CommentDao commentDao, AppExecutors executors) {
        this.commentApiService = commentApiService;
        this.commentDao = commentDao;
        this.executors = executors;
    }

    public LiveData<List<Comment>> getCommentsForPost(Integer postId) {

        refreshComments(postId);

        return commentDao.getCommentsForPost(postId);
    }
    private void refreshComments(Integer postId) {
        executors.diskIO().execute(() -> {
            try {
                Response response = commentApiService.getCommentsByPostId(postId).execute();
                if (response.isSuccessful()) {
                    commentDao.insertComments((List<Comment>) response.body());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void addComment(Comment comment) {
        executors.diskIO().execute(() -> {
            try {
                Response response = commentApiService.addComment(comment).execute();
                if (response.isSuccessful()) {
                    commentDao.saveComment((Comment) response.body());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
