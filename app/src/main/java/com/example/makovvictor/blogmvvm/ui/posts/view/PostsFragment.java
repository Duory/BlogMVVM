package com.example.makovvictor.blogmvvm.ui.posts.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.makovvictor.blogmvvm.ui.MainActivity;
import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.data.model.Post;
import com.example.makovvictor.blogmvvm.di.Injectable;
import com.example.makovvictor.blogmvvm.ui.common.NavigationController;

import java.util.ArrayList;

import javax.inject.Inject;

public class PostsFragment extends Fragment implements Injectable {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    NavigationController mNavigationController;

    private PostsViewModel mPostViewModel;

    private PostsAdapter mListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.posts_fragment, container, false);

        // Set up posts list view
        ListView listView = root.findViewById(R.id.posts_list);
        mListAdapter = new PostsAdapter(new ArrayList<>(0), mItemListener);
        listView.setAdapter(mListAdapter);

        // Set up floating action button
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        if (((MainActivity) getActivity()).isOnline()) {

            fab.setImageResource(R.drawable.ic_add_24dp);

            fab.setOnClickListener(v -> mNavigationController.navigateToAddPost());

            fab.setVisibility(View.VISIBLE);
        }

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPostViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PostsViewModel.class);
        mPostViewModel.init();
        observeViewModel(mPostViewModel);
    }

    private void observeViewModel(PostsViewModel viewModel) {
        viewModel.getPosts().observe(this, posts -> mListAdapter.replaceData(posts));
    }

    PostItemListener mItemListener = new PostItemListener() {
        @Override
        public void onPostClick(Post clickedPost) {
            MainActivity activity = (MainActivity) getActivity();
            mNavigationController.navigateToPostDetails(clickedPost.getId(), (activity.getCurrentUserId() == clickedPost.getUserId()));
        }
    };

    public interface PostItemListener {
        void onPostClick(Post clickedPost);
    }
}
