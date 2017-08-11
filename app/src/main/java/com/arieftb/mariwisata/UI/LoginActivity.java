package com.arieftb.mariwisata.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arieftb.mariwisata.R;
import com.arieftb.mariwisata.session.SessionManager;

public class LoginActivity extends AppCompatActivity {

    final private String USERNAME = "admin";
    final private String PASSWORD = "admin";

    private Button btnLogin;
    private EditText etUsername, etPassword;
    private String uname, pword;

    SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = etUsername.getText().toString();
                pword = etPassword.getText().toString();

                if (uname.equals(USERNAME) && pword.equals(PASSWORD)) {
                    mSessionManager.createLoginSession(uname,pword);
                    Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();

                    Intent mIntent = new Intent(LoginActivity.this, SplashActivity.class);
                    startActivity(mIntent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
