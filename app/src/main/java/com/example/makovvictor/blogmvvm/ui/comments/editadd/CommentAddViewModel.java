package com.example.makovvictor.blogmvvm.ui.comments.editadd;

import android.arch.lifecycle.ViewModel;

import com.example.makovvictor.blogmvvm.data.model.Comment;
import com.example.makovvictor.blogmvvm.data.source.CommentsRepository;

import javax.inject.Inject;

/**
 * Created by victor.makov on 12.03.18.
 */

public class CommentAddViewModel extends ViewModel {

    private CommentsRepository mCommentsRepo;

    @Inject CommentAddViewModel(CommentsRepository commentsRepo) {
        mCommentsRepo = commentsRepo;
    }

    public void addComment(String commentBody, String email, String name, int postId) {
        Comment comment = new Comment();
        comment.setName(name);
        comment.setEmail(email);
        comment.setBody(commentBody);
        comment.setPostId(postId);
        mCommentsRepo.addComment(comment);
    }
}
