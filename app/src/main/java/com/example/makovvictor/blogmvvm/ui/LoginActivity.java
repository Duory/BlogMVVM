package com.example.makovvictor.blogmvvm.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.makovvictor.blogmvvm.R;

/**
 * Created by victor.makov on 13.03.18.
 */

public class LoginActivity extends AppCompatActivity {

    private final String PASSWORD = "password";

    //For recovery
    private final String MASTER_PASSWORD = "66";

    private EditText mPasswordEditText;

    private Button mConfirmButton;

    private SharedPreferences mSharedPreferences;

    private boolean mIsFirstStart = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mPasswordEditText = findViewById(R.id.edittext_password);

        mConfirmButton = findViewById(R.id.confirm_button);
        mConfirmButton.setOnClickListener((v) -> {

            if (mPasswordEditText.getText().toString().isEmpty()) {
                Snackbar.make(v, getString(R.string.empty_password), Snackbar.LENGTH_LONG).show();
                return;
            }

            Editor editor = mSharedPreferences.edit();

            if (mPasswordEditText.getText().toString().equals("66")) {
                editor.clear();
                editor.apply();
                Snackbar.make(v, getString(R.string.password_cleared), Snackbar.LENGTH_LONG).show();
                mIsFirstStart = true;;
                return;
            }

            if (mIsFirstStart) {
                editor.putString(PASSWORD, mPasswordEditText.getText().toString());
                editor.apply();
                startApp();
            } else {
                String password = mSharedPreferences.getString(PASSWORD, "");
                if (mPasswordEditText.getText().toString().equals(password)) {
                    startApp();
                } else {
                    Snackbar.make(v, getString(R.string.invalid_password), Snackbar.LENGTH_LONG).show();
                }
            }
        });

        mSharedPreferences = getPreferences(MODE_PRIVATE);
        if (mSharedPreferences.getAll().isEmpty()) {
            mIsFirstStart = true;
        }
    }

    private void startApp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
