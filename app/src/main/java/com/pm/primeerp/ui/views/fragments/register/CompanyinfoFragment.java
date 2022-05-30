package com.pm.primeerp.ui.views.fragments.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.data.bus.UserAccountBus;
import com.pm.primeerp.data.pojo.CountryData;
import com.pm.primeerp.data.pojo.CurrencyData;
import com.pm.primeerp.data.pojo.Data;
import com.pm.primeerp.data.pojo.OrganizationData;
import com.pm.primeerp.data.view.ClientBus;
import com.pm.primeerp.util.CustomEditText;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyinfoFragment extends Fragment implements BlockingStep {
    @BindView(R.id.etcompanyName)
    CustomEditText etcompanyName;
    @BindView(R.id.ssBusinessType)
    AppCompatSpinner ssBusinessType;
    @BindView(R.id.ssOrgType)
    AppCompatSpinner ssOrgType;
    @BindView(R.id.ssCurrency)
    AppCompatSpinner ssCurrency;
    @BindView(R.id.ssCountry)
    AppCompatSpinner ssCountry;
    @BindView(R.id.etAbbreviationvalue)
    CustomEditText etAbbreviationvalue;
    @BindView(R.id.etTelephonenUmber)
    CustomEditText etTelephonenUmber;
    @BindView(R.id.etemailAddress)
    CustomEditText etemailAddress;
    @BindView(R.id.etcity)
    CustomEditText etcity;
    @BindView(R.id.etPostalAddress)
    CustomEditText etPostalAddress;
    @BindView(R.id.etdescription)
    CustomEditText etdescription;

    PrimeERP app;
    Database db;
    int businesstype, organizationType, currency, country;
    String companyname, abbrvalue, tel, email, postaladdres, city, description;

    private ArrayList<String> businestypearralist = new ArrayList<>();
    private ArrayList<String> currencyearralist = new ArrayList<>();
    private ArrayList<String> orgearralist = new ArrayList<>();
    private ArrayList<String> countryarralist = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        ButterKnife.bind(this, view);
        app = (PrimeERP) requireActivity().getApplication();
        db = Database.getDatabase(requireContext());
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("===============OnActivityCreated==========");

        getbusinesstypes();
        getCurrencies();
        getOrgType();
        getCountryList();

        ssBusinessType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = ssBusinessType.getSelectedItem().toString();

                List<Data> recordid = db.bosinessTypeDao().getbyName(text);
                for (int k = 0; k < recordid.size(); k++) {
                    businesstype = recordid.get(k).getAd_businesstype_id();
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


        ssCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = ssCurrency.getSelectedItem().toString();

                List<CurrencyData> recordid = db.currencyDao().getbyName(text);
                for (int k = 0; k < recordid.size(); k++) {
                    currency = recordid.get(k).getC_currency_id();
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

        ssOrgType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = ssOrgType.getSelectedItem().toString();

                List<OrganizationData> recordid = db.organizationDao().getbyName(text);
                for (int k = 0; k < recordid.size(); k++) {
                    organizationType = recordid.get(k).getAd_org_type_id();
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


        ssCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = ssCountry.getSelectedItem().toString();
                List<CountryData> recordid = db.countryDao().getbyName(text);
                for (int k = 0; k < recordid.size(); k++) {
                    country = recordid.get(k).getC_country_id();
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

    private void getbusinesstypes() {
        businestypearralist.clear();
        businestypearralist.add("-Required-");
        List<Data> businessTypeList = db.bosinessTypeDao().getBusinessType();
        int listSize = businessTypeList.size();
        for (int i = 0; i < businessTypeList.size(); i++) {
            System.out.println("Namer=== " + businessTypeList.get(i).getName());
            System.out.println("Country ID=== " + businessTypeList.get(i).getAd_businesstype_id());
            businestypearralist.add(businessTypeList.get(i).getName());
            ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(requireActivity(), R.layout.spinner_item_rg, businestypearralist);
            regionAdapter.setDropDownViewResource(R.layout.spinner_item_rg2);
            ssBusinessType.setAdapter(regionAdapter);
        }
    }

    private void getCurrencies() {
        currencyearralist.clear();
        currencyearralist.add("-Required-");
        List<CurrencyData> currencyList = db.currencyDao().getCurrencies();
        int listSize = currencyList.size();
        for (int i = 0; i < currencyList.size(); i++) {
            System.out.println("Namer=== " + currencyList.get(i).getDescription());
            System.out.println("Country ID=== " + currencyList.get(i).getDescription());
            currencyearralist.add(currencyList.get(i).getDescription());
            ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(requireActivity(), R.layout.spinner_item_rg, currencyearralist);
            regionAdapter.setDropDownViewResource(R.layout.spinner_item_rg2);
            ssCurrency.setAdapter(regionAdapter);
        }
    }

    private void getOrgType() {
        orgearralist.clear();
        orgearralist.add("-Required-");
        List<OrganizationData> orgList = db.organizationDao().getOrganizationData();
        int listSize = orgList.size();
        for (int i = 0; i < orgList.size(); i++) {
            System.out.println("Namer=== " + orgList.get(i).getDescription());
            System.out.println("Country ID=== " + orgList.get(i).getDescription());
            orgearralist.add(orgList.get(i).getDescription());
            ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(requireActivity(), R.layout.spinner_item_rg, orgearralist);
            regionAdapter.setDropDownViewResource(R.layout.spinner_item_rg2);
            ssOrgType.setAdapter(regionAdapter);
        }
    }

    private void getCountryList() {
        countryarralist.clear();
        countryarralist.add("-Required-");
        List<CountryData> countryList = db.countryDao().getCountries();
        for (int i = 0; i < countryList.size(); i++) {
            countryarralist.add(countryList.get(i).getName());
            ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(requireActivity(), R.layout.spinner_item_rg, countryarralist);
            regionAdapter.setDropDownViewResource(R.layout.spinner_item_rg2);
            ssCountry.setAdapter(regionAdapter);
        }
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

        companyname = Objects.requireNonNull(etcompanyName.getText()).toString().trim();
        abbrvalue = Objects.requireNonNull(etAbbreviationvalue.getText()).toString().trim();
        tel = Objects.requireNonNull(etTelephonenUmber.getText()).toString().trim();
        email = Objects.requireNonNull(etemailAddress.getText()).toString().trim();
        postaladdres = Objects.requireNonNull(etPostalAddress.getText()).toString().trim();
        city = Objects.requireNonNull(etcity.getText()).toString().trim();
        description = Objects.requireNonNull(etdescription.getText()).toString().trim();

        if (TextUtils.isEmpty(companyname)) {
            etcompanyName.setError("Client Name Required");
            return;
        } else if (TextUtils.isEmpty(tel)) {
            etTelephonenUmber.setError("Client Name Required");
            return;
        } else if (TextUtils.isEmpty(email)) {
            etemailAddress.setError("Client Name Required");
            return;
        } else if (TextUtils.isEmpty(postaladdres)) {
            etPostalAddress.setError("Client Name Required");
            return;
        } else if (TextUtils.isEmpty(city)) {
            etcity.setError("Client Name Required");
            return;
        } else if (TextUtils.isEmpty(description)) {
            etdescription.setError("Client Name Required");
            return;
        } else {
            UserAccountBus userAccountBus = UserAccountBus.getInstance();
            userAccountBus.setCompanyname(companyname);
            userAccountBus.setOrganizationType(organizationType);
            userAccountBus.setCurrency(currency);
            userAccountBus.setBusinesstype(businesstype);
            userAccountBus.setAbbrvalue(abbrvalue);
            userAccountBus.setTel(tel);
            userAccountBus.setEmail(email);
            userAccountBus.setCountry(country);
            userAccountBus.setCity(city);
            userAccountBus.setPostaladdres(postaladdres);
            userAccountBus.setDescription(description);
            callback.goToNextStep();
        }
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

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
