package com.example.makovvictor.blogmvvm.data.source.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.makovvictor.blogmvvm.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsRemoteDataSource {

    private static final String BASE_API_URL = "https://jsonplaceholder.typicode.com/";

    private static PostsRemoteDataSource instance;
    private PostApi mPostApi;
    private Retrofit mRetrofit;

    public PostsRemoteDataSource() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mPostApi = mRetrofit.create(PostApi.class);
    }

    public LiveData<List<Post>> getAllPosts() {

        Call<List<Post>> getAllPost = mPostApi.getAllPosts();
        final MutableLiveData<List<Post>> data = new MutableLiveData<>();

        getAllPost.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
        return data;
    }
}
