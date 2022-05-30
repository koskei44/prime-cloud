package com.pm.primeerp.ui.auth.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.transition.Fade;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.data.pojo.LoginResponse;
import com.pm.primeerp.data.retrofit.LoginUser;
import com.pm.primeerp.navigationviewandroid.PrimeErpDashboardActivity;
import com.pm.primeerp.ui.views.activities.components.CreateRecordsActivity;
import com.pm.primeerp.util.CustomEditText;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pm.primeerp.settings.Settings.hasPermissions;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    CustomEditText editTextEmail;
    @BindView(R.id.editTextPassword)
    CustomEditText editTextPassword;
    @BindView(R.id.loginButton)
    MaterialButton loginButton;
    @BindView(R.id.registerButton)
    LinearLayout registerButton;
    static String username;
    static String password;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private PrimeERP app;
    static Context context = PrimeERP.getApp();
    Database db;

    private HashMap<Integer, String> userRoles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setupWindowAnimations();

        app = (PrimeERP) getApplication();
        db = Database.getDatabase(getApplicationContext());

        checkPermissions();
        userRoles = new HashMap<>();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = editTextEmail.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                if (username.isEmpty() || username == null) {
                    Toast.makeText(context.getApplicationContext(), "Username Required!!!", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(context.getApplicationContext(), "Password Required!!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (InternetConnection.checkConnection(context)) {

                        LoginUser user = new LoginUser(username, password);

                        Call<LoginResponse> call = app.apiService.signIn(user);
                        call.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                if (response.code() == 200) {
                                    if (response.body() != null) {
                                        userRoles.put(response.body().getSummary().getOrganizations().get(0).getId(),response.body().getSummary().getOrganizations().get(0).getName());
//                                                new FetchLogin().execute();
                                        String accessToken = response.body().getAccessToken();
                                        String organization = response.body().getSummary().getOrganizations().get(0).getName();
//                                        String userName = response.body().getUsername();
//                                        String name = response.body().getName();
//                                        String email = response.body().getEmail();
                                        app.setToken(accessToken);
//                                        app.setLoginName(userName);
//                                        app.setName(name);
//                                        app.setEmail(email);
                                        app.settings.SetIsloggedIn(true);
                                        System.out.println("organizationorganization"+organization);

                                        selectRoleDialog().show();

//                                        showDashboard(new ArrayList<>(userRoles.keySet()).get(0));

                                    }


                                } else {
                                    Toast.makeText(context.getApplicationContext(), "Invalid Username or Password!!!", Toast.LENGTH_SHORT).show();

                                }


                            }


                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
//
                            }
                        });
                    } else {
                    }


                }
            }
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CreateRecordsActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @SuppressLint("NewApi")
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);
    }
    private AlertDialog selectRoleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (this).getLayoutInflater();
        final View update_layout = getLayoutInflater().inflate(R.layout.login_role_selector, null);

        ArrayList<String> roles = new ArrayList<>(userRoles.values());
        System.out.println("userRoles size = " + userRoles.size());
        final int[] roleID = {1000000};

        final SearchableSpinner spinner = update_layout.findViewById(R.id.ssSelectRole);
        ArrayAdapter<String> adapterspinner = new ArrayAdapter<>(this, R.layout.spinner_item_rg, roles);
        adapterspinner.setDropDownViewResource(R.layout.spinner_item_rg2);
        spinner.setAdapter(adapterspinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                roleID[0] = new ArrayList<>(userRoles.keySet()).get(position);
                app.setOrganizationID( roleID[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        builder.setTitle(R.string.select_role);
        builder.setCancelable(false);
        builder.setView(update_layout)
                .setPositiveButton(R.string.proceed, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new loggingin().execute();
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    public static class InternetConnection {
        public static boolean checkConnection(Context context) {

            context = PrimeERP.getApp();
            final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connMgr != null) {
                NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

                if (activeNetworkInfo != null) { // connected to the internet
//                    Toast.makeText(context.getApplicationContext(), "Internet Connected!!!", Toast.LENGTH_SHORT).show();

                    // connected to the mobile provider's data plan
                    if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        // connected to wifi
                        return true;
                    } else return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
                } else {
                    Toast.makeText(context.getApplicationContext(), "No Internet Connection!!!", Toast.LENGTH_SHORT).show();
                }
            }

            return false;
        }
    }



    private class loggingin extends AsyncTask<Void, Void, Void> {
        private ProgressDialog mprogress = new ProgressDialog(LoginActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mprogress.show();
            mprogress.setTitle("Logging in please wait...");
            mprogress.setMessage("Logging in please wait......");
            mprogress.setCancelable(true);
            mprogress.setIndeterminate(false);

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void result) {

            if (mprogress.isShowing()) {
                mprogress.dismiss();
            }
            runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), PrimeErpDashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }


    private void checkPermissions() {
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    public void ShowHidePass(View view) {

        if(view.getId()==R.id.show_pass_btn){
            if(editTextPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                //Show Password
                editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                //Hide Password
                editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
}
