package com.example.makovvictor.blogmvvm.ui.posts.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.data.model.Post;

import java.util.Collections;
import java.util.List;

public class PostsAdapter extends BaseAdapter {

    private List<Post> mPosts;
    private PostsFragment.PostItemListener mItemListener;

    PostsAdapter(List<Post> posts, PostsFragment.PostItemListener itemListener) {
        Collections.reverse(posts);
        mPosts = posts;
        mItemListener = itemListener;
    }

    void replaceData(List<Post> posts) {
        Collections.reverse(posts);
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

        rowView.setOnClickListener(v -> mItemListener.onPostClick(post));

        return rowView;
    }
}
