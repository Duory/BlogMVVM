package com.example.makovvictor.blogmvvm.ui;

/**
 * Created by victor.makov on 01.03.18.
 */

import android.support.v4.app.FragmentManager;

import com.example.makovvictor.blogmvvm.MainActivity;
import com.example.makovvictor.blogmvvm.R;
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
}
