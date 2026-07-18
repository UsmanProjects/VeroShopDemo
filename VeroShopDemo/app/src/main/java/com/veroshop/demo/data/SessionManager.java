package com.veroshop.demo.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "VeroShopSession";
    private static final String KEY_LOGGED_IN = "isLoggedIn";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_DARK_MODE = "darkMode";
    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String KEY_LANGUAGE = "language";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void login(String email, String name) {
        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public void register(String email, String firstName, String lastName, String phone) {
        editor.putBoolean(KEY_LOGGED_IN, false);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_NAME, firstName + " " + lastName);
        editor.putString(KEY_PHONE, phone);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED_IN, false);
    }

    public String getEmail() { return prefs.getString(KEY_EMAIL, "user@veroshop.com"); }
    public String getName() { return prefs.getString(KEY_NAME, "Demo User"); }
    public String getPhone() { return prefs.getString(KEY_PHONE, "+1 555 0000"); }
    public String getAddress() { return prefs.getString(KEY_ADDRESS, ""); }

    public void updateProfile(String name, String phone, String address) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_ADDRESS, address);
        editor.apply();
    }

    public void logout() {
        editor.putBoolean(KEY_LOGGED_IN, false);
        editor.apply();
    }

    public boolean isDarkMode() { return prefs.getBoolean(KEY_DARK_MODE, false); }
    public void setDarkMode(boolean dark) { editor.putBoolean(KEY_DARK_MODE, dark); editor.apply(); }

    public boolean isNotificationsEnabled() { return prefs.getBoolean(KEY_NOTIFICATIONS, true); }
    public void setNotifications(boolean enabled) { editor.putBoolean(KEY_NOTIFICATIONS, enabled); editor.apply(); }

    public int getLanguageIndex() { return prefs.getInt(KEY_LANGUAGE, 0); }
    public void setLanguageIndex(int index) { editor.putInt(KEY_LANGUAGE, index); editor.apply(); }
}
