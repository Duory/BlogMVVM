package com.example.makovvictor.blogmvvm.ui.posts.detail;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.di.Injectable;
import com.example.makovvictor.blogmvvm.ui.MainActivity;
import com.example.makovvictor.blogmvvm.ui.common.NavigationController;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by victor.makov on 05.03.18.
 */

public class PostDetailsFragment extends Fragment implements Injectable {

    private static final String POST_ID_KEY = "post_id";
    private static final String IS_EDITABLE = "is_editable";

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    NavigationController mNavigationController;

    private PostDetailsViewModel mPostDetailsViewModel;

    private TextView mPostTitle;
    private TextView mPostBody;

    private CommentsAdapter mListAdapter;

    public static PostDetailsFragment create(int postId, boolean isEditable) {
        PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POST_ID_KEY, postId);
        bundle.putBoolean(IS_EDITABLE, isEditable);
        postDetailsFragment.setArguments(bundle);
        return postDetailsFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.post_details_fragment, container, false);

        // Set up list view
        ListView listView = root.findViewById(R.id.comments_list);
        mListAdapter = new CommentsAdapter(new ArrayList<>(0));
        listView.setAdapter(mListAdapter);

        mPostTitle = root.findViewById(R.id.post_details_title);
        mPostBody = root.findViewById(R.id.post_details_body);

        // Set up floating action button
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        if (((MainActivity) getActivity()).isOnline()) {

            fab.setImageResource(R.drawable.ic_comment_24dp);

            fab.setOnClickListener(v -> mNavigationController.navigateToAddComment(getArguments().getInt(POST_ID_KEY)));

            fab.setVisibility(View.VISIBLE);

            // Allow editing
            setHasOptionsMenu(getArguments().getBoolean(IS_EDITABLE));
        }

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.post_details_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_menu_item:
                mNavigationController.navigateToEditPost(getArguments().getInt(POST_ID_KEY));
                return true;
        }
        return false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPostDetailsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PostDetailsViewModel.class);
        mPostDetailsViewModel.init(getArguments().getInt(POST_ID_KEY));
        observeViewModel(mPostDetailsViewModel);
    }

    private void observeViewModel(PostDetailsViewModel viewModel) {
        viewModel.getPost().observe(this, post -> {
            mPostTitle.setText(post.getTitle());
            mPostBody.setText(post.getBody());
        });
        viewModel.getComments().observe(this, comments -> mListAdapter.replaceData(comments));
    }
}
