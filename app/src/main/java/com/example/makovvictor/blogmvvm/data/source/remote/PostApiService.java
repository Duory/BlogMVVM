package com.example.makovvictor.blogmvvm.data.source.remote;


import com.example.makovvictor.blogmvvm.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApiService {

    @GET("/posts")
    Call<List<Post>> getAllPosts();

}
