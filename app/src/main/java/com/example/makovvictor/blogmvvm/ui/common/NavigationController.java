package com.example.makovvictor.blogmvvm.ui.common;

/*
 * Created by victor.makov on 01.03.18.
 */

import android.support.v4.app.FragmentManager;

import com.example.makovvictor.blogmvvm.ui.MainActivity;
import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.ui.comments.editadd.CommentAddFragment;
import com.example.makovvictor.blogmvvm.ui.posts.detail.PostDetailsFragment;
import com.example.makovvictor.blogmvvm.ui.posts.editadd.PostEditAddFragment;
import com.example.makovvictor.blogmvvm.ui.posts.view.PostsFragment;

import javax.inject.Inject;

/**
 * A utility class that handles navigation.
 */
public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToPosts() {
        PostsFragment postsFragment = new PostsFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, postsFragment)
                .commitAllowingStateLoss();
    }

    public void navigateToPostDetails(int postId, boolean isEditable) {
        PostDetailsFragment postDetailsFragment = PostDetailsFragment.create(postId, isEditable);
        fragmentManager.beginTransaction()
                .replace(containerId, postDetailsFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToEditPost(int postId) {
        PostEditAddFragment postEditAddFragment = PostEditAddFragment.create(postId);
        fragmentManager.beginTransaction()
                .replace(containerId, postEditAddFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToAddPost() {
        PostEditAddFragment postEditAddFragment = PostEditAddFragment.create();
        fragmentManager.beginTransaction()
                .replace(containerId, postEditAddFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToAddComment(int postId) {
        CommentAddFragment commentAddFragment = CommentAddFragment.create(postId);
        fragmentManager.beginTransaction()
                .replace(containerId, commentAddFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateBack() {
        fragmentManager.popBackStack();
    }
}
