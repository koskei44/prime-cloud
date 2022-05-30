package com.pm.primeerp.ui.views.fragments.Estimates;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.data.bus.EstimatesBus;
import com.pm.primeerp.data.bus.UserAccountBus;
import com.pm.primeerp.data.model.Items;
import com.pm.primeerp.data.model.Quotation;
import com.pm.primeerp.data.model.TaxRates;
import com.pm.primeerp.data.pojo.CountryData;
import com.pm.primeerp.data.pojo.CurrencyData;
import com.pm.primeerp.data.pojo.Data;
import com.pm.primeerp.data.pojo.OrganizationData;
import com.pm.primeerp.util.CustomEditText;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class estimatestep1 extends Fragment implements BlockingStep {
    //
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
    SearchableSpinner ssCurrency;
    PrimeERP app;
    String uri = "";
    Database db;
    @BindView(R.id.datepicker)
    TextInputLayout datepicker;
    @BindView(R.id.datepicker2)
    TextInputLayout datepicker2;
    private ArrayList<String> currencyearralist = new ArrayList<>();
    private ArrayList<Items> itemsArrayList = new ArrayList<Items>();
    private ArrayList<TaxRates> ratesArrayList = new ArrayList<TaxRates>();

    String qname, qvalue, qdate, qexpdate, qdescription;
    int currency_id, products_id;

    String itemsvalue, amountvalue, itemsquantity;
    final Calendar myCalendar= Calendar.getInstance();
    final Calendar myCalendar2= Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener date2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.estimates_activity_step1, container, false);
        ButterKnife.bind(this, view);



        app = (PrimeERP) requireActivity().getApplication();
        db = Database.getDatabase(requireContext());

        date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        date2 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH,month);
                myCalendar2.set(Calendar.DAY_OF_MONTH,day);
                updateexdate();
            }
        };
        etQdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
        etQdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        etExpdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(getActivity(), date2, myCalendar2.get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH), myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
        etExpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),date2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        return view;
    }

    private void updateLabel(){
        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        etQdate.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void updateexdate(){
        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        etExpdate.setText(dateFormat.format(myCalendar2.getTime()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("===============OnActivityCreated==========");
        getCurrencies();
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        datepicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        qname = Objects.requireNonNull(etQName.getText()).toString().trim();
        qvalue = Objects.requireNonNull(etQvalue.getText()).toString().trim();
        qdate = Objects.requireNonNull(etQdate.getText()).toString().trim();
        qexpdate = Objects.requireNonNull(etExpdate.getText()).toString().trim();
        qdescription = Objects.requireNonNull(etDescription.getText()).toString().trim();


        EstimatesBus estimatesBus = EstimatesBus.getInstance();

        estimatesBus.setName(qname);
        estimatesBus.setValue(qvalue);
        estimatesBus.setQuotationdate(qdate);
        estimatesBus.setExpirydate(qexpdate);
        estimatesBus.setDescription(qdescription);

        callback.goToNextStep();


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

    private void getCurrencies() {
        currencyearralist.clear();
        currencyearralist.add("-Required-");
        List<CurrencyData> currencyList = db.currencyDao().getCurrencies();
        int listSize = currencyList.size();
        for (int i = 0; i < currencyList.size(); i++) {
            currencyearralist.add(currencyList.get(i).getDescription());
            ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(requireActivity(), R.layout.spinner_item_rg, currencyearralist);
            regionAdapter.setDropDownViewResource(R.layout.spinner_item_rg2);
            ssCurrency.setAdapter(regionAdapter);
        }
    }
}

