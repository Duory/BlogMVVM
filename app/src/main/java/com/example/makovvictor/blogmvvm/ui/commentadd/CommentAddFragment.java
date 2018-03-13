package com.example.makovvictor.blogmvvm.ui.commentadd;

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

import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.di.Injectable;
import com.example.makovvictor.blogmvvm.ui.MainActivity;
import com.example.makovvictor.blogmvvm.ui.common.NavigationController;

import javax.inject.Inject;

/**
 * Created by victor.makov on 12.03.18.
 */

public class CommentAddFragment extends Fragment implements Injectable {

    private static final String POST_ID_KEY = "post_id";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    NavigationController navigationController;

    private CommentAddViewModel commentAddViewModel;

    private EditText commentBody;
    private EditText email;
    private EditText name;

    public static CommentAddFragment create(int postId) {
        CommentAddFragment commentAddFragment = new CommentAddFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POST_ID_KEY, postId);
        commentAddFragment.setArguments(bundle);
        return commentAddFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.comment_add_fragment, container, false);

        commentBody = root.findViewById(R.id.edittext_comment_body);
        email = root.findViewById(R.id.edittext_comment_email);
        name = root.findViewById(R.id.edittext_comment_name);

        // Set up floating action button
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        if (((MainActivity) getActivity()).isOnline()) {

            fab.setImageResource(R.drawable.ic_check_24dp);

            fab.setOnClickListener(v -> {
                if (isEmpty(commentBody)) {
                    Snackbar.make(v, getString(R.string.empty_comment_body), Snackbar.LENGTH_LONG).show();
                } else if (isEmpty(email)) {
                    Snackbar.make(v, getString(R.string.empty_email), Snackbar.LENGTH_LONG).show();
                } else if (isEmpty(name)) {
                    Snackbar.make(v, getString(R.string.empty_name), Snackbar.LENGTH_LONG).show();
                } else {
                    commentAddViewModel.addComment(
                            commentBody.getText().toString(),
                            email.getText().toString(),
                            name.getText().toString(),
                            getArguments().getInt(POST_ID_KEY));
                    Snackbar.make(v, getString(R.string.comment_added), Snackbar.LENGTH_LONG).show();
                    navigationController.navigateBack();
                }

            });

            fab.setVisibility(View.VISIBLE);
        }


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        commentAddViewModel = ViewModelProviders.of(this, viewModelFactory).get(CommentAddViewModel.class);
    }

    private boolean isEmpty(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return false;
        }
        return true;
    }
}
