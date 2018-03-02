package com.example.makovvictor.blogmvvm.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.makovvictor.blogmvvm.data.model.Comment;
import com.example.makovvictor.blogmvvm.data.model.Post;

/**
 * Created by victor.makov on 28.02.18.
 */

@Database(entities = {Post.class, Comment.class}, version = 1)
public abstract class BlogDatabase extends RoomDatabase {
    public abstract PostDao postDao();
    public abstract CommentDao commentDao();
}
