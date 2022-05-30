package com.pm.primeerp.ui.views.activities.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.AppBarLayout;
import com.pm.primeerp.PrimeERP;
import com.pm.primeerp.R;
import com.pm.primeerp.data.model.ClientInfo;
import com.pm.primeerp.data.retrofit.ClientResponse;
import com.pm.primeerp.data.view.ClientStatus;
import com.pm.primeerp.data.view.Total;
import com.pm.primeerp.network.APIClient;
import com.pm.primeerp.network.RecordsInterface;
import com.pm.primeerp.ui.auth.ui.LoginActivity;
import com.pm.primeerp.ui.views.activities.components.CreateRecordsActivity;
import com.pm.primeerp.ui.views.viewmodels.RecordViewModel;
import com.pm.primeerp.view.ProfileDialog;
import com.tingyik90.snackprogressbar.SnackProgressBar;
import com.tingyik90.snackprogressbar.SnackProgressBarManager;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Koskei K. Hoseah on 12/12/2021.
 */
public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.appBarMain)
    AppBarLayout appBarMain;
    @BindView(R.id.cardViewAddRecord)
    CardView cardViewAddRecord;
    @BindView(R.id.cardViewEditRecord)
    CardView cardViewEditRecord;
    @BindView(R.id.cardViewUpdateRecord)
    CardView cardViewUpdateRecord;
    @BindView(R.id.cardViewSettings)
    CardView cardViewSettings;
    @BindView(R.id.logoutButton)
    Button logoutButton;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    ProfileDialog profileDialog;
    @BindView(R.id.etDynamicEdit)
    TextView etDynamicEdit;
    @BindView(R.id.etDynamicUpdate)
    TextView etDynamicUpdate;

    private SnackProgressBarManager snackProgressBarManager;
    private PrimeERP app;
    private String imagePath;

    private RecordViewModel recordViewModel;
    private Total total;

    private RecordsInterface recordsInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        ButterKnife.bind(this);

        app = (PrimeERP) getApplication();
        recordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        total = recordViewModel.countRegisteredClients();
        recordViewModel.getUnsyncedData(false).observe(this, this::syncData);
        imagePath = "/MediCapt/Images";

        recordsInterface = APIClient.getClient(app.settings.getBearerToken()).create(RecordsInterface.class);

        createDirectory();

        etDynamicEdit.setText(String.format("Edit Records ( %s )", total.getTotal()));
        etDynamicUpdate.setText(String.format("Update Records ( %s )", total.getTotal()));

        profileDialog = ProfileDialog.newInstance(((dialog, which) -> logout()));

        //Initialize Snackbar Manager -> Attach/pin to the bottom of the layout :)
        snackProgressBarManager = new SnackProgressBarManager(coordinatorLayout)
                .setProgressBarColor(R.color.colorAccent)
                .setOverlayLayoutAlpha(0.6f);


        logoutButton.setOnClickListener(v -> profileDialog.show(getSupportFragmentManager(), "profile"));

        cardViewAddRecord.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CreateRecordsActivity.class);
            startActivity(intent);
        });

        cardViewEditRecord.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), EditRecordsActivity.class);
//            startActivity(intent);

            Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
        });

        cardViewUpdateRecord.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), UpdateRecordActivity.class);
//            startActivity(intent);

            Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
        });

        cardViewSettings.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
//            startActivity(intent);

            Toast.makeText(this, "Under Construction", Toast.LENGTH_SHORT).show();
        });
    }

    private void syncData(List<ClientInfo> clientInfoList) {

        if (clientInfoList != null && !clientInfoList.isEmpty()) {

            for (ClientInfo clientInfo : clientInfoList) {

                Call<ClientResponse> call = recordsInterface.syncRecords(clientInfo);
                call.enqueue(new Callback<ClientResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<ClientResponse> call, @NotNull Response<ClientResponse> response) {
                        if (response.isSuccessful()) {

                            ClientResponse clientResponse = response.body();
                            assert clientResponse != null;

                            recordViewModel.updateClientRecord(new ClientStatus(true, clientResponse.getClient_id(), clientResponse.getRecord_id()));
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<ClientResponse> call, @NotNull Throwable t) {

                    }
                });
            }
        }

    }

    private void createDirectory() {
        String root = Environment.getExternalStorageDirectory().toString();
        System.out.println(root + " Root value in saveImage Function");
        File myDir = new File(root + imagePath);

        if (!myDir.exists()) {
            myDir.mkdirs();
        }
    }


    private void logout() {

        if (!app.settings.IsloggedIn()) {
            return;
        }

        SnackProgressBar snackProgressBar = new SnackProgressBar(
                SnackProgressBar.TYPE_CIRCULAR,
                "Logging Out...")
                .setSwipeToDismiss(false);

        // Show snack progress during logout
        snackProgressBarManager.dismissAll();
        snackProgressBarManager.show(snackProgressBar, SnackProgressBarManager.LENGTH_INDEFINITE);

        //Clear Shared Pref File
        app.settings.SetIsloggedIn(false);
        app.settings.SetIsFirstTime(true);
        app.settings.setFullName("");
        app.settings.setEmail("");
        app.settings.setBearerToken("");

        //Redirect User to Login Page
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();

        //Unreachable anyway
        snackProgressBarManager.dismiss();
    }
}

