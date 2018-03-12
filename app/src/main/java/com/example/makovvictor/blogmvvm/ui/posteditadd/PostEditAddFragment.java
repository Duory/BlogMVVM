package com.example.makovvictor.blogmvvm.ui.posteditadd;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.makovvictor.blogmvvm.MainActivity;
import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.di.Injectable;
import com.example.makovvictor.blogmvvm.ui.common.NavigationController;

import javax.inject.Inject;

/**
 * Created by victor.makov on 06.03.18.
 */

public class PostEditAddFragment extends Fragment implements Injectable {

    private static final String POST_ID_KEY = "post_id";
    private static final String IS_EDITABLE = "is_editable";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    NavigationController navigationController;

    private PostEditAddViewModel postEditAddViewModel;

    private EditText postTitle;
    private EditText postBody;

    public static PostEditAddFragment create(int postId) {
        PostEditAddFragment postEditAddFragment = new PostEditAddFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POST_ID_KEY, postId);
        bundle.putBoolean(IS_EDITABLE, true);
        postEditAddFragment.setArguments(bundle);
        return postEditAddFragment;
    }

    public static PostEditAddFragment create() {
        PostEditAddFragment postEditAddFragment = new PostEditAddFragment();
        Bundle bundle = new Bundle();
        postEditAddFragment.setArguments(bundle);
        return postEditAddFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.post_edit_add_fragment, container, false);

        postTitle = root.findViewById(R.id.edittext_post_title);
        postBody = root.findViewById(R.id.edittext_post_body);

        // Set up floating action button
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_check_24dp);

        fab.setOnClickListener(v -> {
            if (isEmpty(postTitle)) {
                Snackbar.make(v, getString(R.string.empty_post_title), Snackbar.LENGTH_LONG).show();
            } else if (isEmpty(postBody)) {
                Snackbar.make(v, getString(R.string.empty_post_body), Snackbar.LENGTH_LONG).show();
            } else {
                MainActivity activity = (MainActivity) getActivity();
                if (getArguments().getBoolean(IS_EDITABLE, false)) {
                    int postId = getArguments().getInt(POST_ID_KEY);
                    postEditAddViewModel.updatePost(
                            postId,
                            postTitle.getText().toString(),
                            postBody.getText().toString(),
                            activity.getCurrentUserId());
                    Snackbar.make(v, getString(R.string.post_updated), Snackbar.LENGTH_LONG).show();
                } else {
                    postEditAddViewModel.addPost(
                            postTitle.getText().toString(),
                            postBody.getText().toString(),
                            activity.getCurrentUserId());
                    Snackbar.make(v, getString(R.string.post_added), Snackbar.LENGTH_LONG).show();
                }
                navigationController.navigateToPosts();
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        postEditAddViewModel = ViewModelProviders.of(this, viewModelFactory).get(PostEditAddViewModel.class);
        if (getArguments().get(POST_ID_KEY) != null) {
            postEditAddViewModel.init(getArguments().getInt(POST_ID_KEY));
            observeViewModel(postEditAddViewModel);
        }
    }

    private void observeViewModel(PostEditAddViewModel viewModel) {
        viewModel.getPost().observe(this, post -> {
            postTitle.setText(post.getTitle());
            postBody.setText(post.getBody());
        });

    }

    private boolean isEmpty(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return false;
        }
        return true;
    }
}