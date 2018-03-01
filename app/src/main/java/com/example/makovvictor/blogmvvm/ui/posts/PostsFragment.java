package com.example.makovvictor.blogmvvm.ui.posts;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.data.model.Post;
import com.example.makovvictor.blogmvvm.di.Injectable;
import com.example.makovvictor.blogmvvm.ui.NavigationController;

import java.util.List;

import javax.inject.Inject;

public class PostsFragment extends Fragment implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    NavigationController navigationController;

    private PostsViewModel postViewModel;

    private PostsAdapter mListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.posts_fragment, container, false);

        // Set up posts list view
        ListView listView = root.findViewById(R.id.posts_list);
        listView.setAdapter(mListAdapter);

        // Set up floating action button
        //FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_post);

        // Set up progress indicator
        final ScrollSwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(listView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postViewModel.init();
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        postViewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel.class);
        observeViewModel(postViewModel);
    }

    private void observeViewModel(PostsViewModel viewModel) {

        viewModel.getPosts().observe(this, posts -> {
            mListAdapter.replaceData(posts);
        });
    }

    private static class PostsAdapter extends BaseAdapter {

        private List<Post> mPosts;
        private PostItemListener mItemListener;

        public PostsAdapter(List<Post> posts, PostItemListener itemListener) {
            mPosts = posts;
            mItemListener = itemListener;
        }

        public void replaceData(List<Post> posts) {
            mPosts = posts;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mPosts.size();
        }

        @Override
        public Post getItem(int position) {
            return mPosts.get(position);
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
                rowView = inflater.inflate(R.layout.post_item, parent, false);
            }

            final Post post = getItem(position);

            TextView titleTextView = rowView.findViewById(R.id.title);
            titleTextView.setText(post.getTitle());
            TextView authorTextView = rowView.findViewById(R.id.author);
            authorTextView.setText(String.valueOf(post.getUserId()));

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onPostClick(post);
                }
            });

            return rowView;
        }
    }

    PostItemListener mItemListener = new PostItemListener() {

        @Override
        public void onPostClick(Post clickedPost) {

        }
    };

    public interface PostItemListener {

        void onPostClick(Post clickedPost);
    }
}
