package com.veroshop.demo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.veroshop.demo.data.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilEmail, tilPassword;
    private TextInputEditText etEmail, etPassword;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(this);

        tilEmail = findViewById(R.id.til_login_email);
        tilPassword = findViewById(R.id.til_login_password);
        etEmail = findViewById(R.id.et_login_email);
        etPassword = findViewById(R.id.et_login_password);

        // Pre-fill if registered
        if (!TextUtils.isEmpty(session.getEmail()) && !session.getEmail().equals("user@veroshop.com")) {
            etEmail.setText(session.getEmail());
        }

        findViewById(R.id.btn_login).setOnClickListener(v -> attemptLogin());
        findViewById(R.id.btn_go_register).setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
        findViewById(R.id.btn_login_back).setOnClickListener(v -> finish());
        findViewById(R.id.btn_forgot_password).setOnClickListener(v ->
                Snackbar.make(findViewById(android.R.id.content),
                        "Password reset link sent to your email!", Snackbar.LENGTH_LONG).show());
    }

    private void attemptLogin() {
        tilEmail.setError(null);
        tilPassword.setError(null);
        boolean valid = true;

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(email)) { tilEmail.setError(getString(R.string.error_empty_field)); valid = false; }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { tilEmail.setError(getString(R.string.error_invalid_email)); valid = false; }
        if (TextUtils.isEmpty(password)) { tilPassword.setError(getString(R.string.error_empty_field)); valid = false; }
        else if (password.length() < 6) { tilPassword.setError(getString(R.string.error_weak_password)); valid = false; }

        if (valid) {
            // Accept any valid-format email+password for demo
            String name = session.getName().isEmpty() ? "Demo User" : session.getName();
            session.login(email, name);
            Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
