package com.pm.primeerp.data;

import android.content.Context;

import com.pm.primeerp.data.dao.BosinessTypeDao;
import com.pm.primeerp.data.dao.ClientDao;
import com.pm.primeerp.data.dao.CountryDao;
import com.pm.primeerp.data.dao.CurrencyDao;
import com.pm.primeerp.data.dao.OrganizationDao;
import com.pm.primeerp.data.dao.Productsdao;
import com.pm.primeerp.data.dao.TaxRatesDao;
import com.pm.primeerp.data.dao.UserDao;
import com.pm.primeerp.data.model.BusinessType;
import com.pm.primeerp.data.model.ClientInfo;
import com.pm.primeerp.data.model.Country;
import com.pm.primeerp.data.model.Currency;
import com.pm.primeerp.data.model.User;
import com.pm.primeerp.data.pojo.CountryData;
import com.pm.primeerp.data.pojo.CurrencyData;
import com.pm.primeerp.data.pojo.Data;
import com.pm.primeerp.data.pojo.OrganizationData;
import com.pm.primeerp.data.pojo.ProductsData;
import com.pm.primeerp.data.pojo.TaxRatesData;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@androidx.room.Database(entities = {User.class, ProductsData.class, TaxRatesData.class, OrganizationData.class, Data.class, CountryData.class, CurrencyData.class, ClientInfo.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class Database extends RoomDatabase {

    private static Database INSTANCE;

    public static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), Database.class, "primeerpv1")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();

    public abstract ClientDao clientDao();
    public abstract CountryDao countryDao();
    public abstract CurrencyDao currencyDao();
    public abstract BosinessTypeDao  bosinessTypeDao();
    public abstract OrganizationDao organizationDao();
    public abstract Productsdao productsdao();
    public abstract TaxRatesDao taxRatesDao();

}

