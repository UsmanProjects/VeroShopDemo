package com.veroshop.demo.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.veroshop.demo.EditProfileActivity;
import com.veroshop.demo.R;
import com.veroshop.demo.SettingsActivity;
import com.veroshop.demo.WelcomeActivity;
import com.veroshop.demo.data.SessionManager;

public class ProfileFragment extends Fragment {

    private SessionManager session;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new SessionManager(requireContext());
        refreshProfile(view);

        view.findViewById(R.id.btn_edit_profile).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), EditProfileActivity.class)));

        view.findViewById(R.id.btn_change_password).setOnClickListener(v ->
                new AlertDialog.Builder(requireContext())
                        .setTitle("Change Password")
                        .setMessage("A password reset link would be sent to your email in a real app.")
                        .setPositiveButton("OK", null)
                        .show());

        view.findViewById(R.id.btn_profile_settings).setOnClickListener(v ->
                startActivity(new Intent(getActivity(), SettingsActivity.class)));

        view.findViewById(R.id.btn_logout).setOnClickListener(v -> showLogoutDialog());
    }

    private void refreshProfile(View view) {
        ((TextView) view.findViewById(R.id.tv_profile_name)).setText(session.getName());
        ((TextView) view.findViewById(R.id.tv_profile_email)).setText(session.getEmail());
        ((TextView) view.findViewById(R.id.tv_profile_phone)).setText(session.getPhone());
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle(R.string.logout)
                .setMessage(R.string.logout_confirm)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    session.logout();
                    Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null) refreshProfile(getView());
    }
}
