package com.example.makovvictor.blogmvvm.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.makovvictor.blogmvvm.data.model.Post;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by victor.makov on 28.02.18.
 */

@Dao
public interface PostDao {

    @Insert(onConflict = REPLACE)
    void savePosts(List<Post> posts);

    @Insert(onConflict = REPLACE)
    void savePost(Post post);

    @Query("SELECT * FROM posts")
    LiveData<List<Post>> getAllPosts();

    @Query("SELECT * FROM posts WHERE id =:postId")
    LiveData<Post> getPost(Integer postId);
}
