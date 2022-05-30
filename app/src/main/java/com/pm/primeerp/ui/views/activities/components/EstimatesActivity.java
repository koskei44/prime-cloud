package com.pm.primeerp.ui.views.activities.components;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.ui.views.adapters.EstimatesStepper;
import com.pm.primeerp.ui.views.adapters.MyStepperAdapter;
import com.stepstone.stepper.StepperLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class EstimatesActivity extends AppCompatActivity {

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

        stepperLayout.setAdapter(new EstimatesStepper(getSupportFragmentManager(), this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        onBackPressed();
        return true;
    }


}

