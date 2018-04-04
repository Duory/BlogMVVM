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
    private final int mContainerId;
    private final FragmentManager mFragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        mContainerId = R.id.container;
        mFragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToPosts() {
        PostsFragment postsFragment = new PostsFragment();
        mFragmentManager.beginTransaction()
                .replace(mContainerId, postsFragment)
                .commitAllowingStateLoss();
    }

    public void navigateToPostDetails(int postId, boolean isEditable) {
        PostDetailsFragment postDetailsFragment = PostDetailsFragment.create(postId, isEditable);
        mFragmentManager.beginTransaction()
                .replace(mContainerId, postDetailsFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToEditPost(int postId) {
        PostEditAddFragment postEditAddFragment = PostEditAddFragment.create(postId);
        mFragmentManager.beginTransaction()
                .replace(mContainerId, postEditAddFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToAddPost() {
        PostEditAddFragment postEditAddFragment = PostEditAddFragment.create();
        mFragmentManager.beginTransaction()
                .replace(mContainerId, postEditAddFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToAddComment(int postId) {
        CommentAddFragment commentAddFragment = CommentAddFragment.create(postId);
        mFragmentManager.beginTransaction()
                .replace(mContainerId, commentAddFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateBack() {
        mFragmentManager.popBackStack();
    }
}
