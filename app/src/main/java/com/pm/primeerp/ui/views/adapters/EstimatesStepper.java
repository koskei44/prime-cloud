package com.pm.primeerp.ui.views.adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.pm.primeerp.ui.views.fragments.Estimates.estimatesStep2;
import com.pm.primeerp.ui.views.fragments.Estimates.estimatestep1;
import com.pm.primeerp.ui.views.fragments.register.CompanyinfoFragment;
import com.pm.primeerp.ui.views.fragments.register.LoginInfoFragment;
import com.pm.primeerp.ui.views.fragments.register.UserInfoFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class EstimatesStepper extends AbstractFragmentStepAdapter {

    private static final String CURRENT_STEP_POSITION_KEY = "";

    public EstimatesStepper(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        if (position == 0) {
            final estimatestep1 step = new estimatestep1();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step.setArguments(b);
            return step;
        }  else if (position == 1) {
            final estimatesStep2 step = new estimatesStep2();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step.setArguments(b);
            return step;
        }
        final estimatestep1 step = new estimatestep1();
        Bundle b = new Bundle();
        b.putInt(CURRENT_STEP_POSITION_KEY, position);
        step.setArguments(b);
        return step;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        if (position == 0) {
            return new StepViewModel.Builder(context)
                    .setTitle("Add Quotation")
                    .setEndButtonLabel("Proceed")
                    .create();
        } else if (position == 1) {
            return new StepViewModel.Builder(context)
                    .setTitle("Quotation Items")
                    .setEndButtonLabel("Create Quotation")
                    .create();
        }
        return new StepViewModel.Builder(context)
                .setTitle("Main Information")
                .setEndButtonLabel("Proceed")
                .create();
    }
}

