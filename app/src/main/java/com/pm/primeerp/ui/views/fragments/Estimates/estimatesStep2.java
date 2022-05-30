package com.pm.primeerp.ui.views.fragments.Estimates;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.data.bus.EstimatesBus;
import com.pm.primeerp.data.model.Items;
import com.pm.primeerp.data.model.Product;
import com.pm.primeerp.data.model.Quotation;
import com.pm.primeerp.data.model.TaxRates;
import com.pm.primeerp.data.pojo.CurrencyData;
import com.pm.primeerp.data.pojo.ProductsData;
import com.pm.primeerp.data.pojo.Productsresponse;
import com.pm.primeerp.data.pojo.RegisterResponse;
import com.pm.primeerp.data.pojo.RetrofitRepsonse;
import com.pm.primeerp.data.pojo.TaxRatesData;
import com.pm.primeerp.data.view.MultipleSelectionSpinner;
import com.pm.primeerp.ui.auth.ui.LoginActivity;
import com.pm.primeerp.util.CustomEditText;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
public class estimatesStep2  extends Fragment implements BlockingStep {
    //
    @BindView(R.id.etItemvalue)
    CustomEditText etItemvalue;
    @BindView(R.id.etQuantiy)
    CustomEditText etQuantiy;
    @BindView(R.id.etamount)
    CustomEditText etamount;
    @BindView(R.id.ssProduct)
    SearchableSpinner ssProduct;
    @BindView(R.id.ssTaxRates)
    SearchableSpinner ssTaxRates;

    @BindView(R.id.saveQuotationItems)
    MaterialButton saveQuotationItems;
    @BindView(R.id.addproducts)
    AppCompatButton addproducts;

    PrimeERP app;
    String uri = "";
    Database db;

    ItemsCustomListAdapter adapter;

    String product, value, name;
    int currency_id, taxratesID,price;
    private ArrayList<String>taxratearraylist = new ArrayList<>();
    private ArrayList<String>productsarraylist = new ArrayList<>();
    private ArrayList<Items> itemsArrayList = new ArrayList<Items>();
    private List<Items> itesmList = new ArrayList<Items>();
    private ArrayList<TaxRates> ratesArrayList = new ArrayList<TaxRates>();
    private ArrayList<String> currencyearralist = new ArrayList<>();

    ArrayList<Integer> raxratesArrays = new ArrayList<Integer>();
    ArrayList<Integer> raxratesArraysproducts = new ArrayList<Integer>();
    int productsid, taxid;
    String itemsvalue, amountvalue, itemsquantity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cretate_estimates_items, container, false);
        ButterKnife.bind(this, view);

        app = (PrimeERP) requireActivity().getApplication();
        db = Database.getDatabase(requireContext());
        //adding items to list

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getProductslist();
        getTaxRateslist();
        getCurrencies();
        getItemsList();

//        taxratearraylist.add("20 %");
//        taxratearraylist.add("5 %");
//        taxratearraylist.add("8 %");
//        mSpinner.setItems(taxratearraylist);

        saveQuotationItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsvalue = Objects.requireNonNull(etItemvalue.getText()).toString().trim();
                amountvalue = Objects.requireNonNull(etamount.getText()).toString().trim();
                itemsquantity = Objects.requireNonNull(etQuantiy.getText()).toString().trim();

                itemsArrayList.add(new Items(0, productsid,
                        itemsquantity, amountvalue, true, raxratesArrays));

                getItemsList();
            }
        });

        addproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectRoleDialog().show();            }
        });

        ssProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = ssProduct.getSelectedItem().toString();

                List<ProductsData> recordid = db.productsdao().getbyName(text);
                for (int k = 0; k < recordid.size(); k++) {
                    productsid = recordid.get(k).getProducts_id();
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

        ssTaxRates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = ssTaxRates.getSelectedItem().toString();

                List<TaxRatesData> recordid = db.taxRatesDao().getbyName(text);
                for (int k = 0; k < recordid.size(); k++) {
                    taxid = recordid.get(k).getTaxId();
                    raxratesArrays.add(taxid);
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

    private AlertDialog selectRoleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (this).getLayoutInflater();
        final View update_layout = getLayoutInflater().inflate(R.layout.add_products, null);

        final int[] roleID = {1000000};

        final EditText productName = update_layout.findViewById(R.id.etProductName);
        final EditText productvalue = update_layout.findViewById(R.id.etProductvalue);
        final EditText productprice = update_layout.findViewById(R.id.etProductprice);
        final SearchableSpinner currency = update_layout.findViewById(R.id.ssCurrency);
        final SearchableSpinner sstaxrates = update_layout.findViewById(R.id.ssTaxRates);

        ArrayAdapter<String> adapterspinner = new ArrayAdapter<>(getActivity(), R.layout.spinner_item_rg, currencyearralist);
        adapterspinner.setDropDownViewResource(R.layout.spinner_item_rg2);
        currency.setAdapter(adapterspinner);


        ArrayAdapter<String> adapterspinnertax = new ArrayAdapter<>(getActivity(), R.layout.spinner_item_rg, taxratearraylist);
        adapterspinnertax.setDropDownViewResource(R.layout.spinner_item_rg2);
        sstaxrates.setAdapter(adapterspinnertax);


        currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                String text = currency.getSelectedItem().toString();

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

        sstaxrates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String text = sstaxrates.getSelectedItem().toString();

                List<TaxRatesData> recordid = db.taxRatesDao().getbyName(text);
                for (int k = 0; k < recordid.size(); k++) {
                    taxratesID = recordid.get(k).getTaxId();
                    raxratesArraysproducts.add(taxratesID);
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

        builder.setTitle(R.string.add_products);
        builder.setCancelable(false);
        builder.setView(update_layout)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        name = productName.getText().toString().trim();
                        value = productvalue.getText().toString().trim();
                        price = Integer.parseInt(productprice.getText().toString().trim());

                        Product product = new Product(
                                0,
                                app.getOrganizationID(),
                                0,
                                value,
                                name,
                                "ABCD",
                                true,
                                true,
                                true,
                                true,
                                raxratesArraysproducts,
                                currency_id,
                                price,
                                true
                        );

                        Call<RetrofitRepsonse> call = app.apiService.createProduct(product);
                        call.enqueue(new Callback<RetrofitRepsonse>() {
                            @Override
                            public void onResponse(Call<RetrofitRepsonse> call, Response<RetrofitRepsonse> response) {
//
                                if (response.code() == 200) {
                                    if (response.body().getSuccess() == "true"){
                                        Toast.makeText(getActivity(), "Product Added successfully!", Toast.LENGTH_SHORT).show();
                                        getAllProducts();

                                    }else {
                                        if (response.body().getSuccess() == "false"){
//                                Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                } else   if (response.code() == 400) {
                                    Gson gson = new Gson();
                                    RegisterResponse message = null;
                                    if (response.errorBody() != null) {
                                        message = gson.fromJson(response.errorBody().charStream(), RegisterResponse.class);
                                    }
                                    if (message != null) {
                                        Toast.makeText(getActivity(), message.getData(), Toast.LENGTH_LONG).show();
                                    } else {
                                        String errors = "";
                                        try {
                                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                                            errors = jObjError.getString("data");

                                        } catch (JSONException | IOException e) {
                                            e.printStackTrace();
                                        }
                                        if (!errors.equals("")) {
                                            Toast.makeText(getActivity(), errors, Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(getActivity(), response.message(), Toast.LENGTH_LONG).show();
                                        }
                                    }
//

                                }
                                else {
                                }


                            }

                            @Override
                            public void onFailure(Call<RetrofitRepsonse> call, Throwable t) {
                                Toast.makeText(getActivity(), "Internal Server Error!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {



    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

        EstimatesBus estimatesBus = EstimatesBus.getInstance();

        Quotation quotation = new Quotation(
                0,
                estimatesBus.getName(),
                estimatesBus.getValue(),
                estimatesBus.getQuotationdate(),
                estimatesBus.getExpirydate(),
                estimatesBus.getDescription(),
                true,
                itemsArrayList
        );

        Call<Quotation> call = app.apiService.createquotation(quotation);
        call.enqueue(new Callback<Quotation>() {
            @Override
            public void onResponse(Call<Quotation> call, Response<Quotation> response) {
                if (response.code() == 200) {
                    Toast.makeText(getActivity(), "Quotation Created successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getActivity(), "Internal Server Error!", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Quotation> call, Throwable t) {
                Toast.makeText(getActivity(), "Internal Server Error!", Toast.LENGTH_SHORT).show();
            }
        });
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


    private void getProductslist() {
        productsarraylist.add("-Required-");
        List<ProductsData> productsDataList = db.productsdao().getproductsData();
        int listSize = productsDataList.size();
        for (int i = 0; i < productsDataList.size(); i++) {
            productsarraylist.add(productsDataList.get(i).getName());
            ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(requireActivity(), R.layout.spinner_item_rg, productsarraylist);
            regionAdapter.setDropDownViewResource(R.layout.spinner_item_rg2);
            ssProduct.setAdapter(regionAdapter);
        }
    }

    public void getAllProducts() {
        db.productsdao().deleteall();
        Call<Productsresponse> call = app.apiService.getProductByOrganization(app.getOrganizationID());
        call.enqueue(new Callback<Productsresponse>() {
            @Override
            public void onResponse(Call<Productsresponse> call, Response<Productsresponse> response) {
                if (response.isSuccessful()) {

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        db.productsdao().insertproductsData(response.body().getData().get(i));
                        try {
                            Thread.sleep(1000);
                            getProductslist();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {

                }
            }
            @Override
            public void onFailure(Call<Productsresponse> call, Throwable t) {

            }
        });
    }

    private void getTaxRateslist() {
        taxratearraylist.add("-Required-");
        List<TaxRatesData> taxrateList = db.taxRatesDao().getptaxRatesData();
        int listSize = taxrateList.size();
        for (int i = 0; i < taxrateList.size(); i++) {
            taxratearraylist.add(taxrateList.get(i).getName());
            ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(requireActivity(), R.layout.spinner_item_rg, taxratearraylist);
            regionAdapter.setDropDownViewResource(R.layout.spinner_item_rg2);
            ssTaxRates.setAdapter(regionAdapter);
        }
    }

    private void getItemsList() {
        adapter = new ItemsCustomListAdapter(getActivity(), itemsArrayList);
        ListView listView = (ListView) getView().findViewById(R.id.quotaionlist);
        listView.setAdapter(adapter);
    }

    private void getCurrencies() {
        currencyearralist.clear();
        currencyearralist.add("-Required-");
        List<CurrencyData> currencyList = db.currencyDao().getCurrencies();
        int listSize = currencyList.size();
        for (int i = 0; i < currencyList.size(); i++) {
            currencyearralist.add(currencyList.get(i).getDescription());
        }
    }
}


