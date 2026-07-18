package com.veroshop.demo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.veroshop.demo.data.SessionManager;

public class SettingsActivity extends AppCompatActivity {

    private SwitchMaterial switchDark, switchNotifications;
    private Spinner spinnerLanguage;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        session = new SessionManager(this);
        MaterialToolbar toolbar = findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        switchDark = findViewById(R.id.switch_dark_mode);
        switchNotifications = findViewById(R.id.switch_notifications);
        spinnerLanguage = findViewById(R.id.spinner_language);

        // Load saved state
        switchDark.setChecked(session.isDarkMode());
        switchNotifications.setChecked(session.isNotificationsEnabled());

        // Language spinner
        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(
                this, R.array.languages, android.R.layout.simple_spinner_item);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(langAdapter);
        spinnerLanguage.setSelection(session.getLanguageIndex());

        switchDark.setOnCheckedChangeListener((v, checked) -> {
            session.setDarkMode(checked);
            AppCompatDelegate.setDefaultNightMode(checked ?
                    AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        });

        switchNotifications.setOnCheckedChangeListener((v, checked) ->
                session.setNotifications(checked));

        spinnerLanguage.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View v, int pos, long id) {
                session.setLanguageIndex(pos);
            }
            @Override public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        findViewById(R.id.ll_about).setOnClickListener(v ->
                startActivity(new android.content.Intent(this, AboutActivity.class)));

        findViewById(R.id.ll_privacy).setOnClickListener(v ->
                new AlertDialog.Builder(this)
                        .setTitle(R.string.privacy_policy)
                        .setMessage(R.string.privacy_policy_text)
                        .setPositiveButton("OK", null)
                        .show());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { onBackPressed(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
