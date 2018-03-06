package com.example.makovvictor.blogmvvm.ui.posteditadd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.makovvictor.blogmvvm.di.Injectable;
import com.example.makovvictor.blogmvvm.ui.common.NavigationController;

import javax.inject.Inject;

/**
 * Created by victor.makov on 06.03.18.
 */

public class PostEditAddFragment extends Fragment implements Injectable {

    private static final String POST_ID_KEY = "post_id";

    @Inject
    NavigationController navigationController;

    public static PostEditAddFragment create(int postId) {
        PostEditAddFragment postEditAddFragment = new PostEditAddFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POST_ID_KEY, postId);
        postEditAddFragment.setArguments(bundle);
        return postEditAddFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}