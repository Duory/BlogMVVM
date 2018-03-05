package com.example.makovvictor.blogmvvm.ui.postdetails;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.di.Injectable;
import com.example.makovvictor.blogmvvm.ui.common.NavigationController;

import javax.inject.Inject;

/**
 * Created by victor.makov on 05.03.18.
 */

public class PostDetailsFragment extends Fragment implements Injectable {

    private static final String POST_ID_KEY = "post_id";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    NavigationController navigationController;

    private PostDetailsViewModel postDetailsViewModel;

    private TextView postTitle;
    private TextView postBody;

    //private CommentsAdapter listAdapter;

    public static PostDetailsFragment create(int postId) {
        PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POST_ID_KEY, postId);
        postDetailsFragment.setArguments(bundle);
        return postDetailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.post_details_fragment, container, false);

        postTitle = root.findViewById(R.id.post_details_title);
        postBody = root.findViewById(R.id.post_details_body);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        postDetailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(PostDetailsViewModel.class);
        postDetailsViewModel.init(getArguments().getInt(POST_ID_KEY));
        observeViewModel(postDetailsViewModel);

    }

    private void observeViewModel(PostDetailsViewModel viewModel) {
        viewModel.getPost().observe(this, post -> {
            postTitle.setText(post.getTitle());
            postBody.setText(post.getBody());
        });
        viewModel.getComments().observe(this, comments -> {
            //TODO: display comments for post
        });
    }
}
