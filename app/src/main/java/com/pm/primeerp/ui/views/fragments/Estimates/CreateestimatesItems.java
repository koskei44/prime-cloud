package com.pm.primeerp.ui.views.fragments.Estimates;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;

import butterknife.ButterKnife;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class CreateestimatesItems extends AppCompatActivity {
    PrimeERP app;
    String uri = "";
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cretate_estimates_items);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        onBackPressed();
        return true;
    }
}


