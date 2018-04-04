package com.example.makovvictor.blogmvvm.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.makovvictor.blogmvvm.R;
import com.example.makovvictor.blogmvvm.ui.common.NavigationController;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    private ConnectivityManager mConnectivityManager;

    private int currentUserId = 3;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    NavigationController navigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            navigationController.navigateToPosts();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public boolean isOnline() {
        if (mConnectivityManager == null) {
            mConnectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        try
        {
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
            boolean isOnline = false;
            if (mConnectivityManager != null) {
                isOnline = networkInfo.isConnectedOrConnecting();
            }
            return isOnline;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
