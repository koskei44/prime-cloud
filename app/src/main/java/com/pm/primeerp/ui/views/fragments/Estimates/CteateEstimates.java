package com.pm.primeerp.ui.views.fragments.Estimates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.ui.views.activities.components.CreateRecordsActivity;
import com.pm.primeerp.ui.views.adapters.MyStepperAdapter;
import com.stepstone.stepper.StepperLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class CteateEstimates extends AppCompatActivity {
    @BindView(R.id.createstimatesbtn)
    CardView createstimatesbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estimates_create_estimates);
        ButterKnife.bind(this);

        createstimatesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateestimatesActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        onBackPressed();
        return true;
    }




}

