package com.pm.primeerp.ui.views.fragments.Estimates;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.data.model.Items;
import com.pm.primeerp.data.model.Quotation;
import com.pm.primeerp.data.model.Register;
import com.pm.primeerp.data.model.TaxRates;
import com.pm.primeerp.data.pojo.CurrencyData;
import com.pm.primeerp.ui.auth.ui.LoginActivity;
import com.pm.primeerp.util.CustomEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class CreateestimatesActivity extends AppCompatActivity {
    BottomSheetDialog bottomSheetDialog;
    @BindView(R.id.addQuotationItems)
    CardView addQuotationItems;
    @BindView(R.id.submitQuotations)
    MaterialButton submitQuotations;
    @BindView(R.id.etQName)
    CustomEditText etQName;
    @BindView(R.id.etQvalue)
    CustomEditText etQvalue;
    @BindView(R.id.etQdate)
    CustomEditText etQdate;
    @BindView(R.id.etExpdate)
    CustomEditText etExpdate;
    @BindView(R.id.etDescription)
    CustomEditText etDescription;
    @BindView(R.id.ssCurrency)
    AppCompatSpinner ssCurrency;
    PrimeERP app;
    String uri = "";
    Database db;
    private ArrayList<String> currencyearralist = new ArrayList<>();
    private ArrayList<Items> itemsArrayList = new ArrayList<Items>();
    private ArrayList<TaxRates> ratesArrayList = new ArrayList<TaxRates>();

    String qname, qvalue, qdate, qexpdate, qdescription;
    int currency_id, products_id;

    String itemsvalue, amountvalue, itemsquantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cretate_estimates_activity);
        ButterKnife.bind(this);
        app = (PrimeERP) getApplication();
        db = Database.getDatabase(getApplicationContext());

        getCurrencies();
        addQuotationItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createestimatesItems();
//                Intent intent = new Intent(getApplicationContext(), CreateestimatesItems.class);
//                startActivity(intent);
            }
        });

        submitQuotations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                qname = Objects.requireNonNull(etQName.getText()).toString().trim();
                qvalue = Objects.requireNonNull(etQvalue.getText()).toString().trim();
                qdate = Objects.requireNonNull(etQdate.getText()).toString().trim();
                qexpdate = Objects.requireNonNull(etExpdate.getText()).toString().trim();
                qdescription = Objects.requireNonNull(etDescription.getText()).toString().trim();

                Quotation quotation = new Quotation(
                        0,
                        qname,
                        qvalue,
                        qdate,
                        qexpdate,
                        qdescription,
                        true,
                        itemsArrayList
                );

                Call<Quotation> call = app.apiService.createquotation(quotation);
                call.enqueue(new Callback<Quotation>() {
                    @Override
                    public void onResponse(Call<Quotation> call, Response<Quotation> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Account created successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "Internal Server Error!", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<Quotation> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Internal Server Error!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        ssCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = ssCurrency.getSelectedItem().toString();

                List<CurrencyData> recordid = db.currencyDao().getbyName(text);
                for (int k = 0; k < recordid.size(); k++) {
                    currency_id = recordid.get(k).getC_currency_id();
                }
                try {
                    //Your task here
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void createestimatesItems() {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.cretate_estimates_items);
        bottomSheetDialog.show();

        EditText value = bottomSheetDialog.findViewById(R.id.etItemvalue);
        EditText quantiy = bottomSheetDialog.findViewById(R.id.etQuantiy);
        EditText amount = bottomSheetDialog.findViewById(R.id.etamount);
//        AppCompatSpinner taxrates = bottomSheetDialog.findViewById(R.id.ssTaxRates);
        AppCompatSpinner products = bottomSheetDialog.findViewById(R.id.ssProduct);
        MaterialButton saveQuotationItems = bottomSheetDialog.findViewById(R.id.saveQuotationItems);

        amountvalue = Objects.requireNonNull(amount.getText()).toString().trim();
        itemsquantity = Objects.requireNonNull(quantiy.getText()).toString().trim();
        itemsvalue = Objects.requireNonNull(value.getText()).toString().trim();
//        products_id = Integer.parseInt(Objects.requireNonNull(products.getSelectedItem()).toString().trim());

        saveQuotationItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ratesArrayList.add(new TaxRates(0
//                ));
//                itemsArrayList.add(new Items(0, 50,
//                        itemsquantity, amountvalue, true, ratesArrayList));
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        bottomSheetDialog.dismiss();
        onBackPressed();
        return true;
    }

    private void getCurrencies() {
        currencyearralist.add("-Required-");
        List<CurrencyData> currencyList = db.currencyDao().getCurrencies();
        int listSize = currencyList.size();
        for (int i = 0; i < currencyList.size(); i++) {
            currencyearralist.add(currencyList.get(i).getDescription());
            ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item_rg, currencyearralist);
            regionAdapter.setDropDownViewResource(R.layout.spinner_item_rg2);
            ssCurrency.setAdapter(regionAdapter);
        }
    }


}


