package com.example.makovvictor.blogmvvm.ui.common;

/**
 * Created by victor.makov on 01.03.18.
 */

import android.support.v4.app.FragmentManager;

import com.example.makovvictor.blogmvvm.MainActivity;
import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.ui.postdetails.PostDetailsFragment;
import com.example.makovvictor.blogmvvm.ui.posteditadd.PostEditAddFragment;
import com.example.makovvictor.blogmvvm.ui.posts.PostsFragment;

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
        PostEditAddFragment postEditAddFragment = new PostEditAddFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, postEditAddFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}