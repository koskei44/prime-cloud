package com.pm.primeerp.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ProfileDialog extends DialogFragment {

    private static MaterialDialog.SingleButtonCallback callback;

    PrimeERP app;

    public static ProfileDialog newInstance(MaterialDialog.SingleButtonCallback buttonCallback) {
        callback = buttonCallback;
        return new ProfileDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_profile, null, false);

        TextView name = view.findViewById(R.id.text_view_name);
        TextView email = view.findViewById(R.id.text_view_email);
        ImageView imageView = view.findViewById(R.id.image_view_user);

        app = (PrimeERP) Objects.requireNonNull(getActivity()).getApplication();

//        name.setText(app.settings.getFullName());
//        email.setText(app.settings.getEmail());

        Glide.with(this)
                .load(R.drawable.ic_medicine_dark)
                .into(imageView);

        return new MaterialDialog.Builder(getActivity())
                .customView(view, false)
                .positiveText("Sign out")
                .onPositive(callback)
                .build();
    }
}
