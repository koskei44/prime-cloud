package com.pm.primeerp;

import android.app.Application;
import android.graphics.Typeface;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pm.primeerp.network.RecordsInterface;
import com.pm.primeerp.settings.Settings;
import com.pm.primeerp.util.ItemTypeAdapterFactory;
import com.pm.primeerp.util.TypeFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Response;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//import retrofit2.converter.scalars.ScalarsConverterFactory;
public class PrimeERP extends Application {

    private static PrimeERP mInstance;
    public Settings settings;
    private TypeFactory mFontFactory;
    private String token;
    private String loginName;
    private String name;
    private String email;
    private Gson gson;
    public RecordsInterface apiService;
    private OkHttpClient.Builder httpClient;
    private HttpLoggingInterceptor loggingInterceptor;
    private static Retrofit retrofit;

    public static synchronized PrimeERP getApp() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        settings = new Settings(getApplicationContext());

        Stetho.initializeWithDefaults(this);
        token = "";
        loginName = "";
        initializeRetrofit();
        mInstance = this;
    }
    private void initializeRetrofit() {
        gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();
        loggingInterceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        httpClient = new OkHttpClient.Builder();
        httpClient.interceptors().add(loggingInterceptor);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public @NotNull  Response intercept(@NotNull Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        httpClient.callTimeout(1, TimeUnit.MINUTES);
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        httpClient.writeTimeout(30, TimeUnit.SECONDS);

        OkHttpClient client = httpClient.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(RecordsInterface.class);
    }

    public Typeface getTypeFace(int type) {
        if (mFontFactory == null)
            mFontFactory = new TypeFactory(this);

        switch (type) {
            case Constants.REGULAR:
                return mFontFactory.getRegular();

            case Constants.BOLD:
                return mFontFactory.getBold();

            default:
                return mFontFactory.getRegular();
        }
    }

    public interface Constants {
        int REGULAR = 1,
                BOLD = 2;
    }

    private int organizationID;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }
}
