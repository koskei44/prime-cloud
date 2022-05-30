package com.pm.primeerp.ui.views.viewmodels;

import android.app.Application;
import android.os.AsyncTask;

import com.pm.primeerp.data.Database;
import com.pm.primeerp.data.model.ClientInfo;
import com.pm.primeerp.data.view.ClientStatus;
import com.pm.primeerp.data.view.Total;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class RecordViewModel extends AndroidViewModel {

    private Database database;

    public RecordViewModel(Application application) {

        super(application);

        database = Database.getDatabase(this.getApplication());
    }

    public void addRecord(final ClientInfo clientInfo) {
        new RecordViewModel.addAsyncTask(database).execute(clientInfo);
    }

    public Total countRegisteredClients() {
        return database.clientDao().getClientCount();
    }

    public LiveData<List<ClientInfo>> getUnsyncedData(boolean state) {
        return database.clientDao().fetchUnsyncedData(state);
    }

    public void updateClientRecord(final ClientStatus clientStatus) {
        new RecordViewModel.updateClientInfoStatusAsyncTask(database).execute(clientStatus);
    }

    private static class addAsyncTask extends AsyncTask<ClientInfo, Void, Void> {

        private Database db;

        addAsyncTask(Database database) {
            db = database;
        }

        @Override
        protected Void doInBackground(final ClientInfo... params) {
            db.clientDao().addClient(params[0]);
            return null;
        }

    }

    private static class updateClientInfoStatusAsyncTask extends AsyncTask<ClientStatus, Void, Void> {

        private Database db;

        updateClientInfoStatusAsyncTask(Database database) {
            db = database;
        }

        @Override
        protected Void doInBackground(final ClientStatus... params) {
            db.clientDao().updateClientSyncState(params[0].isState(), params[0].getRemote_id(), params[0].getClient_id());
            return null;
        }
    }

}
