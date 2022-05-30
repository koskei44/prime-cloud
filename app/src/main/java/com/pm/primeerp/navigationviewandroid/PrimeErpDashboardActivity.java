package com.pm.primeerp.navigationviewandroid;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.Database;
import com.pm.primeerp.data.pojo.Productsresponse;
import com.pm.primeerp.data.pojo.RaxRatesResponse;
import com.pm.primeerp.ui.auth.ui.LoginActivity;
import com.pm.primeerp.ui.tableview.MainActivity;
import com.pm.primeerp.ui.views.activities.components.EstimatesActivity;
import com.pm.primeerp.ui.views.fragments.Estimates.CteateEstimates;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrimeErpDashboardActivity extends AppCompatActivity implements RecyclerAdapter.ItemClickChild {

//    https://icons8.com/icons/set/settings

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    String names[] = Constant.name;
    String subNames[] = Constant.subName;
    String subNamesInvetory[] = Constant.subNameInventory;
    String subNamesSales[] = Constant.subNameSales;
    String subNamesExpense[] = Constant.subNameExpense;
    String subNamesPayments[] = Constant.subNamePayments;
    String subNamesReports[] = Constant.subNameReports;


    @BindView(R.id.cardViewTable)
    CardView cardViewTable;
    @BindView(R.id.cardViewAddQuotations)
    CardView cardViewAddQuotations;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.logoutButton)
    Button logoutButton;

    private PrimeERP app;
    static Context context = PrimeERP.getApp();
    Database db;

    TitleFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        app = (PrimeERP) getApplication();
        db = Database.getDatabase(getApplicationContext());

        setSupportActionBar(toolbar);
        final ActionBar actionar = getSupportActionBar();
        actionar.setDisplayHomeAsUpEnabled(true);
        actionar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        List<TitleMenu> list = getList();
        RecyclerAdapter adapter = new RecyclerAdapter(this, list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        getAllTaxRates();
        getAllProducts();

        System.out.println("organizationorganization" + app.getOrganizationID());

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                finishAffinity();
            }
        });

        cardViewTable.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);

//            Intent intent = new Intent(getApplicationContext(), EstimatesActivity.class);
//            startActivity(intent);


//            Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
        });

        cardViewAddQuotations.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), EstimatesActivity.class);
            startActivity(intent);
        });

    }


    private List<TitleMenu> getList() {
        List<TitleMenu> list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            List<SubTitle> subTitles = new ArrayList<>();
            System.out.println("Namessss" + names[i]);
            if (names[i] == "Inventory") {
                for (int j = 0; j < subNamesInvetory.length; j++) {
                    SubTitle subTitle = new SubTitle(subNamesInvetory[j]);
                    subTitles.add(subTitle);
                }
                TitleMenu model = new TitleMenu(names[i], subTitles, null);
                list.add(model);
            } else if (names[i] == "Sales") {
                for (int j = 0; j < subNamesSales.length; j++) {
                    SubTitle subTitlesales = new SubTitle(subNamesSales[j]);
                    subTitles.add(subTitlesales);
                }
                TitleMenu model = new TitleMenu(names[i], subTitles, null);
                list.add(model);
            } else if (names[i] == "Expenses") {
                for (int j = 0; j < subNamesExpense.length; j++) {
                    SubTitle subTitleexpense = new SubTitle(subNamesExpense[j]);
                    subTitles.add(subTitleexpense);
                }
                TitleMenu model = new TitleMenu(names[i], subTitles, null);
                list.add(model);
            } else if (names[i] == "Reports") {
                for (int j = 0; j < subNamesReports.length; j++) {
                    SubTitle subTitleReports = new SubTitle(subNamesReports[j]);
                    subTitles.add(subTitleReports);
                }
                TitleMenu model = new TitleMenu(names[i], subTitles, null);
                list.add(model);
            } else if (names[i] == "Payments") {
                for (int j = 0; j < subNamesPayments.length; j++) {
                    SubTitle subTitlePayments = new SubTitle(subNamesPayments[j]);
                    subTitles.add(subTitlePayments);
                }
                TitleMenu model = new TitleMenu(names[i], subTitles, null);
                list.add(model);
            } else {
//                for (int j = 0; j < subNames.length; j++) {
//                    SubTitle subTitle = new SubTitle(subNames[j]);
//                    subTitles.add(subTitle);
//                }
//                TitleMenu model = new TitleMenu(names[i], subTitles, null);
//                list.add(model);
            }

        }
        return list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChildClick(int position) {
        String name = subNamesSales[position];
        drawerLayout.closeDrawers();

        if (name == "Estimates") {
            Intent intent = new Intent(getApplicationContext(), EstimatesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void getAllTaxRates() {
        db.taxRatesDao().deleteall();
        Call<RaxRatesResponse> call = app.apiService.getRaxRatesResponse(app.getOrganizationID());
        call.enqueue(new Callback<RaxRatesResponse>() {
            @Override
            public void onResponse(Call<RaxRatesResponse> call, Response<RaxRatesResponse> response) {
                if (response.isSuccessful()) {

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        db.taxRatesDao().insertpTaxRatesData(response.body().getData().get(i));
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<RaxRatesResponse> call, Throwable t) {

            }
        });
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
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<Productsresponse> call, Throwable t) {

            }
        });
    }
}
