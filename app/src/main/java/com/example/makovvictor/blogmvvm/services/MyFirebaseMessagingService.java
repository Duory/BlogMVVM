package com.example.makovvictor.blogmvvm.services;

import com.example.makovvictor.blogmvvm.data.source.PostsRepository;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import javax.inject.Inject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }

    @Inject
    PostsRepository postsRepository;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().get("id") != null) {
            postsRepository.refreshPost(Integer.valueOf(
                    remoteMessage.getData().get("id")));
        }
    }
}
