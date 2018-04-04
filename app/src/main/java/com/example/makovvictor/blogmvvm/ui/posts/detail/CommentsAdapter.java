package com.example.makovvictor.blogmvvm.ui.posts.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.data.model.Comment;

import java.util.List;

public class CommentsAdapter extends BaseAdapter {

    private List<Comment> mComments;

    CommentsAdapter(List<Comment> comments) {
        this.mComments = comments;
    }

    void replaceData(List<Comment> comments) {
        mComments = comments;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mComments.size();
    }

    @Override
    public Comment getItem(int position) {
        return mComments.get(position);
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
