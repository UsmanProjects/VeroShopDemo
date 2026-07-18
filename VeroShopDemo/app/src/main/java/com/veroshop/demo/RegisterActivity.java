package com.veroshop.demo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.veroshop.demo.data.SessionManager;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout tilFirstName, tilLastName, tilEmail, tilPhone, tilPassword, tilConfirm;
    private TextInputEditText etFirstName, etLastName, etEmail, etPhone, etPassword, etConfirm;
    private CheckBox cbTerms;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        session = new SessionManager(this);

        tilFirstName = findViewById(R.id.til_first_name);
        tilLastName = findViewById(R.id.til_last_name);
        tilEmail = findViewById(R.id.til_email);
        tilPhone = findViewById(R.id.til_phone);
        tilPassword = findViewById(R.id.til_password);
        tilConfirm = findViewById(R.id.til_confirm_password);

        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        etConfirm = findViewById(R.id.et_confirm_password);
        cbTerms = findViewById(R.id.cb_terms);

        findViewById(R.id.btn_register).setOnClickListener(v -> attemptRegister());
        findViewById(R.id.btn_already_login).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    private void attemptRegister() {
        clearErrors();
        boolean valid = true;

        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();

        if (TextUtils.isEmpty(firstName)) { tilFirstName.setError(getString(R.string.error_empty_field)); valid = false; }
        if (TextUtils.isEmpty(lastName)) { tilLastName.setError(getString(R.string.error_empty_field)); valid = false; }
        if (TextUtils.isEmpty(email)) { tilEmail.setError(getString(R.string.error_empty_field)); valid = false; }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { tilEmail.setError(getString(R.string.error_invalid_email)); valid = false; }
        if (TextUtils.isEmpty(phone)) { tilPhone.setError(getString(R.string.error_empty_field)); valid = false; }
        else if (phone.length() < 7) { tilPhone.setError(getString(R.string.error_invalid_phone)); valid = false; }
        if (TextUtils.isEmpty(password)) { tilPassword.setError(getString(R.string.error_empty_field)); valid = false; }
        else if (password.length() < 6) { tilPassword.setError(getString(R.string.error_weak_password)); valid = false; }
        if (TextUtils.isEmpty(confirm)) { tilConfirm.setError(getString(R.string.error_empty_field)); valid = false; }
        else if (!password.equals(confirm)) { tilConfirm.setError(getString(R.string.error_password_mismatch)); valid = false; }
        if (!cbTerms.isChecked()) { Toast.makeText(this, R.string.error_accept_terms, Toast.LENGTH_SHORT).show(); valid = false; }

        if (valid) {
            session.register(email, firstName, lastName, phone);
            Toast.makeText(this, "Account created! Please login.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private void clearErrors() {
        tilFirstName.setError(null); tilLastName.setError(null);
        tilEmail.setError(null); tilPhone.setError(null);
        tilPassword.setError(null); tilConfirm.setError(null);
    }
}
