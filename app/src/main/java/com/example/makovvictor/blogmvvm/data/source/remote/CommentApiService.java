package com.example.makovvictor.blogmvvm.data.source.remote;

import com.example.makovvictor.blogmvvm.data.model.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by victor.makov on 02.03.18.
 */

public interface CommentApiService {

    @GET("/posts/{id}/comments")
    Call<List<Comment>> getCommentsByPostId(@Path("id") int id);
}
