package com.pm.primeerp.ui.views.fragments.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.data.pojo.CountryData;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyLocationFragment extends Fragment implements BlockingStep {

    @BindView(R.id.ssCountry)
    SearchableSpinner ssCountry;
    PrimeERP app;
    Database db;
    private File f;
    private ArrayList<String> countryarralist = new ArrayList<>();

    private boolean taken = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, view);
        app = (PrimeERP) requireActivity().getApplication();
        db = Database.getDatabase(requireContext());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("===============OnActivityCreated==========");

        getCountryList();

    }


    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
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

    private void getCountryList() {
        countryarralist.add("-Required-");
        List<CountryData> countryList = db.countryDao().getCountries();
        for (int i = 0; i < countryList.size(); i++) {
          countryarralist.add(countryList.get(i).getName());
            ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(requireActivity(), R.layout.spinner_item_rg, countryarralist);
            regionAdapter.setDropDownViewResource(R.layout.spinner_item_rg2);
            ssCountry.setAdapter(regionAdapter);
        }
    }

}
