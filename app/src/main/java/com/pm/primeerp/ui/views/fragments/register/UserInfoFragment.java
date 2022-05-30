package com.pm.primeerp.ui.views.fragments.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.bus.UserAccountBus;
import com.pm.primeerp.util.CustomEditText;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoFragment extends Fragment implements BlockingStep {
//
    @BindView(R.id.etuseremail)
    CustomEditText etuseremail;
    @BindView(R.id.etFirstname)
    CustomEditText etFirstname;
    @BindView(R.id.etLastName)
    CustomEditText etLastName;
    @BindView(R.id.etTelephoneNUmber)
    CustomEditText etTelephoneNUmber;

    String useremail, firstname, lastname, telephoneno;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        ButterKnife.bind(this, view);

        PrimeERP app = (PrimeERP) Objects.requireNonNull(getActivity()).getApplication();

        return view;
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

        useremail= Objects.requireNonNull(etuseremail.getText()).toString().trim();
        firstname = Objects.requireNonNull(etFirstname.getText()).toString().trim();
        lastname = Objects.requireNonNull(etLastName.getText()).toString().trim();
        telephoneno = Objects.requireNonNull(etTelephoneNUmber.getText()).toString().trim();

        if (TextUtils.isEmpty(useremail)) {
            etuseremail.setError("Email Required");
            return;
        }

        else if (TextUtils.isEmpty(firstname)) {
            etFirstname.setError("First Name Required");
            return;
        } else if (TextUtils.isEmpty(lastname)) {
            etLastName.setError("Last Name Required");
            return;
        } else if (TextUtils.isEmpty(telephoneno)) {
            etTelephoneNUmber.setError("Tel Required");
            return;
        }else {
            UserAccountBus userAccountBus = UserAccountBus.getInstance();
            userAccountBus.setUseremail(useremail);
            userAccountBus.setFirstname(firstname);
            userAccountBus.setLastname(lastname);
            userAccountBus.setTelephoneno(telephoneno);
            callback.goToNextStep();

        }



    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
