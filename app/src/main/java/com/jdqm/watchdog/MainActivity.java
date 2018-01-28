package com.jdqm.watchdog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jdqm.watchdog.view.PasswordView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private PasswordView passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordView = findViewById(R.id.passwordView);
        passwordView.setOnResultListener(new PasswordView.ResultListener() {
            @Override
            public void onCorrect() {

            }

            @Override
            public void onError() {

            }
        });
    }

    public void enterNumber(View view) {
        if (view instanceof Button) {
            String text = ((Button) view).getText().toString();
            Log.d(TAG, "enterNumber: " + text);
            passwordView.appendPassword(text);
        }
    }

    public void delete(View view) {

    }
}
