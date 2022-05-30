package com.pm.primeerp.ui.views.adapters;

import android.content.Context;
import android.os.Bundle;

import com.pm.primeerp.ui.views.fragments.register.CompanyinfoFragment;
import com.pm.primeerp.ui.views.fragments.register.LoginInfoFragment;
import com.pm.primeerp.ui.views.fragments.register.UserInfoFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

public class MyStepperAdapter extends AbstractFragmentStepAdapter {

    private static final String CURRENT_STEP_POSITION_KEY = "";

    public MyStepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        if (position == 0) {
            final CompanyinfoFragment step = new CompanyinfoFragment();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step.setArguments(b);
            return step;
        }  else if (position == 1) {
            final UserInfoFragment step = new UserInfoFragment();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step.setArguments(b);
            return step;
        } else if (position == 2) {
            final LoginInfoFragment step = new LoginInfoFragment();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step.setArguments(b);
            return step;
        }
        final CompanyinfoFragment step = new CompanyinfoFragment();
        Bundle b = new Bundle();
        b.putInt(CURRENT_STEP_POSITION_KEY, position);
        step.setArguments(b);
        return step;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        if (position == 0) {
            return new StepViewModel.Builder(context)
                    .setTitle("Company Information")
                    .setEndButtonLabel("Proceed")
                    .create();
        } else if (position == 1) {
            return new StepViewModel.Builder(context)
                    .setTitle("User Information")
                    .setEndButtonLabel("Proceed")
                    .create();
        } else if (position == 2) {
            return new StepViewModel.Builder(context)
                    .setTitle("Login Credentials")
                    .setEndButtonLabel("Create Account")
                    .create();
        }
        return new StepViewModel.Builder(context)
                .setTitle("Company Information")
                .setEndButtonLabel("Proceed")
                .create();
    }
}
