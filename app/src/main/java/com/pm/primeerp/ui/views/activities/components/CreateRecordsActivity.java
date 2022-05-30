package com.pm.primeerp.ui.views.activities.components;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.data.pojo.BusinessTypesResponse;
import com.pm.primeerp.data.pojo.CountryResponse;
import com.pm.primeerp.data.pojo.CurrencyReponse;
import com.pm.primeerp.data.pojo.OrganizationResponse;
import com.pm.primeerp.ui.Onboarding.OnBoardingActivity;
import com.pm.primeerp.ui.auth.ui.LoginActivity;
import com.pm.primeerp.ui.views.adapters.MyStepperAdapter;
import com.stepstone.stepper.StepperLayout;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRecordsActivity extends AppCompatActivity {

    @BindView(R.id.stepperLayout)
    StepperLayout stepperLayout;

    PrimeERP app;
    String uri = "";
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_records);
        ButterKnife.bind(this);

        app = (PrimeERP) getApplication();

        db = Database.getDatabase(getApplicationContext());
//        db = OsbpDatabase.getDatabase(getApplicationContext());
        uri = getResources().getString(R.string.url);

//        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_white_24dp);
//        toolbar.setNavigationIcon(upArrow);
//        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        stepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this));

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        onBackPressed();
        return true;
    }




}
