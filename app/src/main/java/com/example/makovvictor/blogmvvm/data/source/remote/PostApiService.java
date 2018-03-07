package com.example.makovvictor.blogmvvm.data.source.remote;


import com.example.makovvictor.blogmvvm.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostApiService {

    @GET("/posts")
    Call<List<Post>> getAllPosts();

    @GET("/posts/{id}")
    Call<Post> getPost(@Path("id") int id);

    @POST("/posts")
    Call<Post> addPost(@Body Post post);

}
