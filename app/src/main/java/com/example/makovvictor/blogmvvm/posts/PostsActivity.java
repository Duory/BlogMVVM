package com.example.makovvictor.blogmvvm.posts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.util.ActivityUtils;

public class PostsActivity extends AppCompatActivity {

    private PostsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.posts_activity);

        PostsFragment postsFragment = (PostsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.postContentFrame);
        if (postsFragment == null) {
            postsFragment = new PostsFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), postsFragment, R.id.postContentFrame
            );
        }

    }
}
