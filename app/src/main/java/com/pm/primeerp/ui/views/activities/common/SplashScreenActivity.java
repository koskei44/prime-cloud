package com.pm.primeerp.ui.views.activities.common;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TimingLogger;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.data.pojo.BusinessTypesResponse;
import com.pm.primeerp.data.pojo.CountryResponse;
import com.pm.primeerp.data.pojo.CurrencyReponse;
import com.pm.primeerp.data.pojo.OrganizationResponse;
import com.pm.primeerp.data.pojo.Productsresponse;
import com.pm.primeerp.data.pojo.RaxRatesResponse;
import com.pm.primeerp.ui.Onboarding.OnBoardingActivity;
import com.pm.primeerp.ui.auth.ui.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends Activity {


    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    private static final int REQ_CODE_VERSION_UPDATE = 530;

    private static int SPLASH_TIME_OUT = 3000;
    private static Database db;

    protected static PrimeERP app;

    private static Context mContext;

    static String userName;
    static String password;

    private static final String CHANNEL_ID = "1000000";


    static String uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        TextView appname = findViewById(R.id.appname);
        appname.setTextColor(getResources().getColor(R.color.colorAccent));

        app = (PrimeERP) getApplication();

        uri = getResources().getString(R.string.url);
        mContext = getApplicationContext();
        db = Database.getDatabase(getApplicationContext());

        new FetchStats().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        checkNewAppVersionState();
    }


    @Override
    protected void onDestroy() {
//        unregisterInstallStateUpdListener();
        super.onDestroy();
    }


    public static class InternetConnection {
        /**
         * CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT
         */
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

    private class FetchStats extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... params) {

            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            getAllCountriesList();
            getAllBusinessTypes();
            getAllCurrencies();
            getAllOrganizations();
            return null;
        }

        protected void onPostExecute(String result) {
//            if (mprogress.isShowing()) {
//                mprogress.dismiss();
//            }
            runOnUiThread(new Runnable() {
                public void run() {
                    // Start your app main activity
                    startActivity(new Intent(SplashScreenActivity.this, OnBoardingActivity.class));
//                   overridePendingTransition(R.anim.slide_up_anim, R.anim.slide_down_anim);

                    finish();
                    finishAffinity();
                }
            });
        }
    }

    public void getAllCountriesList() {
        db.countryDao().deleteall();
        Call<CountryResponse> call = app.apiService.listallCountries();
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        db.countryDao().insertCountry(response.body().getData().get(i));
                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {

            }
        });
    }

    public void getAllBusinessTypes() {
        db.bosinessTypeDao().deleteall();
        Call<BusinessTypesResponse> call = app.apiService.listallBusinessTypes();
        call.enqueue(new Callback<BusinessTypesResponse>() {
            @Override
            public void onResponse(Call<BusinessTypesResponse> call, Response<BusinessTypesResponse> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        db.bosinessTypeDao().insertRecords(response.body().getData().get(i));
                    }
                } else {

                }
            }
            @Override
            public void onFailure(Call<BusinessTypesResponse> call, Throwable t) {

            }
        });
    }

    public void getAllCurrencies() {
        db.currencyDao().deleteall();
        Call<CurrencyReponse> call = app.apiService.listallCurrencies();
        call.enqueue(new Callback<CurrencyReponse>() {
            @Override
            public void onResponse(Call<CurrencyReponse> call, Response<CurrencyReponse> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        db.currencyDao().insertCurrency(response.body().getData().get(i));
                    }
                } else {

                }
            }
            @Override
            public void onFailure(Call<CurrencyReponse> call, Throwable t) {

            }
        });
    }

    public void getAllOrganizations() {
        db.organizationDao().deleteall();
        Call<OrganizationResponse> call = app.apiService.listallOrganizations();
        call.enqueue(new Callback<OrganizationResponse>() {
            @Override
            public void onResponse(Call<OrganizationResponse> call, Response<OrganizationResponse> response) {
                if (response.isSuccessful()) {

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        System.out.println("responsebody"+response.body().getData().get(i));
                        db.organizationDao().insertOrganization(response.body().getData().get(i));
                    }
                } else {

                }
            }
            @Override
            public void onFailure(Call<OrganizationResponse> call, Throwable t) {

            }
        });
    }



}