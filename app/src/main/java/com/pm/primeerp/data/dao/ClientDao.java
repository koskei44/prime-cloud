package com.pm.primeerp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pm.primeerp.data.model.ClientInfo;
import com.pm.primeerp.data.view.Total;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ClientDao {

    @Insert(onConflict = REPLACE)
    void addClient(ClientInfo clientInfo);

    @Query("SELECT COUNT(*) as total FROM ClientInfo")
    Total getClientCount();

    @Query("SELECT * FROM ClientInfo WHERE is_synced =:state")
    LiveData<List<ClientInfo>> fetchUnsyncedData(boolean state);

    @Query("UPDATE ClientInfo SET is_synced =:state, remote_id =:remote_id WHERE client_id =:client_id")
    void updateClientSyncState(boolean state, String remote_id, String client_id);
}
