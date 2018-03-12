package com.example.makovvictor.blogmvvm.ui.postdetails;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.data.model.Comment;
import com.example.makovvictor.blogmvvm.di.Injectable;
import com.example.makovvictor.blogmvvm.ui.common.NavigationController;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by victor.makov on 05.03.18.
 */

public class PostDetailsFragment extends Fragment implements Injectable {

    private static final String POST_ID_KEY = "post_id";
    private static final String IS_EDITABLE = "is_editable";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    NavigationController navigationController;

    private PostDetailsViewModel postDetailsViewModel;

    private TextView postTitle;
    private TextView postBody;

    private CommentsAdapter listAdapter;

    public static PostDetailsFragment create(int postId, boolean isEditable) {
        PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POST_ID_KEY, postId);
        bundle.putBoolean(IS_EDITABLE, isEditable);
        postDetailsFragment.setArguments(bundle);
        return postDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.post_details_fragment, container, false);

        postTitle = root.findViewById(R.id.post_details_title);
        postBody = root.findViewById(R.id.post_details_body);

        // Set up floating action button
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_comment_24dp);

        fab.setOnClickListener(v -> {

        });

        // Allow editing
        setHasOptionsMenu(getArguments().getBoolean(IS_EDITABLE));

        // Set up list view
        ListView listView = root.findViewById(R.id.comments_list);
        listAdapter = new CommentsAdapter(new ArrayList<>(0));
        listView.setAdapter(listAdapter);

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
                navigationController.navigateToEditPost(getArguments().getInt(POST_ID_KEY));
                return true;
        }
        return false;
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
        viewModel.getComments().observe(this, comments -> listAdapter.replaceData(comments));
    }

    private static class CommentsAdapter extends BaseAdapter {

        private List<Comment> comments;

        CommentsAdapter(List<Comment> comments) {
            this.comments = comments;
        }

        void replaceData(List<Comment> comments) {
            this.comments = comments;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return this.comments.size();
        }

        @Override
        public Comment getItem(int position) {
            return this.comments.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.comment_item, parent, false);
            }

            final Comment comment = getItem(position);

            TextView commentAuthorName = rowView.findViewById(R.id.comment_author_name);
            commentAuthorName.setText(comment.getName());

            TextView commentAuthorEmail = rowView.findViewById(R.id.comment_author_email);
            commentAuthorEmail.setText(comment.getEmail());

            TextView commentBody = rowView.findViewById(R.id.comment_body);
            commentBody.setText(comment.getBody());

            return rowView;
        }
    }
}
