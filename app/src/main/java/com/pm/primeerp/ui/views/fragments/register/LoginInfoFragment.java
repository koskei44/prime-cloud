package com.pm.primeerp.ui.views.fragments.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.data.bus.UserAccountBus;
import com.pm.primeerp.data.model.Organization;
import com.pm.primeerp.data.model.Register;
import com.pm.primeerp.data.model.Roles;
import com.pm.primeerp.data.pojo.RegisterResponse;
import com.pm.primeerp.ui.auth.ui.LoginActivity;
import com.pm.primeerp.util.CustomEditText;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInfoFragment extends Fragment implements BlockingStep {

    @BindView(R.id.etusername)
    CustomEditText etusername;
    @BindView(R.id.etpassword)
    CustomEditText etpassword;


    PrimeERP app;
    Database db;
    String username, password;
    ArrayList<Roles> roles = new ArrayList<Roles>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logincredentialls, container, false);
        ButterKnife.bind(this, view);

        app = (PrimeERP) requireActivity().getApplication();
        db = Database.getDatabase(requireContext());

        return view;
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        username = Objects.requireNonNull(etusername.getText()).toString().trim();
        password = Objects.requireNonNull(etpassword.getText()).toString().trim();

        if (TextUtils.isEmpty(username)) {
            etusername.setError("Username Required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etpassword.setError("Password Required");
            return;
        } else {
            UserAccountBus userAccountBus = UserAccountBus.getInstance();
            Organization organization = new Organization(
                    0,
                    userAccountBus.getAbbrvalue(),
                    userAccountBus.getCompanyname(),
                    userAccountBus.getDescription(),
                    1,
                    userAccountBus.getBusinesstype(),
                    userAccountBus.getCountry(),
                    userAccountBus.getOrganizationType(),
                    userAccountBus.getCurrency(),
                    userAccountBus.getCity(),
                    userAccountBus.getEmail(),
                    userAccountBus.getPostaladdres(),
                    userAccountBus.getTel(),
                    "primeerp.co.ke",
                    "primeerp.co.ke",
                    "P.O Box 21",
                    "P.O Box 21",
                    true
            );
            Register register = new Register(
                    username,
                    userAccountBus.getEmail(),
                    userAccountBus.getFirstname(),
                    userAccountBus.getLastname(),
                    password,
                    userAccountBus.getTelephoneno(),
                    "https//:primeerp.co.ke",
                    roles,
                    organization
            );
            Call<RegisterResponse> call = app.apiService.register(register);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (response.code() == 200) {
                        if (response.body().getSuccess() == "true"){
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);

                        }else {
                            if (response.body().getSuccess() == "false"){
//                                Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else   if (response.code() == 400) {
                        Gson gson = new Gson();
                        RegisterResponse message = null;
                        if (response.errorBody() != null) {
                            message = gson.fromJson(response.errorBody().charStream(), RegisterResponse.class);
                        }
                        if (message != null) {
                            Toast.makeText(getActivity(), message.getData(), Toast.LENGTH_LONG).show();
                        } else {
                            String errors = "";
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                errors = jObjError.getString("data");

                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                            if (!errors.equals("")) {
                                Toast.makeText(getActivity(), errors, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), response.message(), Toast.LENGTH_LONG).show();
                            }
                        }
//

                    }
                    else {
//                        Toast.makeText(getActivity(), "Internal Server Error!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {


                    Toast.makeText(getActivity(), "Internal Server Error!", Toast.LENGTH_SHORT).show();
                }
            });


        }


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
