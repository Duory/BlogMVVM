package com.example.makovvictor.blogmvvm.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.makovvictor.blogmvvm.data.model.Comment;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by victor.makov on 02.03.18.
 */

@Dao
public interface CommentDao {

    @Query("SELECT * FROM comments WHERE postId=:postId")
    LiveData<List<Comment>> getCommentsForPost(Integer postId);

    @Insert(onConflict = REPLACE)
    void insertComments(List<Comment> comments);
}
