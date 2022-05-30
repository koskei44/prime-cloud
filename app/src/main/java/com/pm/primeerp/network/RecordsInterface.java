package com.pm.primeerp.network;

import com.pm.primeerp.data.model.ClientInfo;
import com.pm.primeerp.data.model.Currency;
import com.pm.primeerp.data.model.Product;
import com.pm.primeerp.data.model.Quotation;
import com.pm.primeerp.data.model.Register;
import com.pm.primeerp.data.pojo.BusinessTypesResponse;
import com.pm.primeerp.data.pojo.CountryResponse;
import com.pm.primeerp.data.pojo.CurrencyReponse;
import com.pm.primeerp.data.pojo.LoginResponse;
import com.pm.primeerp.data.pojo.OrganizationResponse;
import com.pm.primeerp.data.pojo.Productsresponse;
import com.pm.primeerp.data.pojo.RaxRatesResponse;
import com.pm.primeerp.data.pojo.RegisterResponse;
import com.pm.primeerp.data.pojo.RetrofitRepsonse;
import com.pm.primeerp.data.retrofit.ClientResponse;
import com.pm.primeerp.data.retrofit.LoginUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RecordsInterface {
    @POST("records/create")
    Call<ClientResponse> syncRecords(@Body ClientInfo clientInfo);

    @POST("api/auth/signin")
    Call<LoginResponse> signIn(@Body LoginUser loginUser);

    @POST("api/auth/signup")
    Call<RegisterResponse> register(@Body Register register);

    @POST("quotation/createQuotation")
    Call<Quotation> createquotation(@Body Quotation quotation);


    @POST("product/createProduct")
    Call<RetrofitRepsonse> createProduct(@Body Product product);

    @GET("country/all")
    Call<CountryResponse> listallCountries();

    @GET("businessType/all")
    Call<BusinessTypesResponse> listallBusinessTypes();

    @GET("currency/all")
    Call<CurrencyReponse> listallCurrencies();

    @GET("organizationType/all")
    Call<OrganizationResponse> listallOrganizations();

    @GET("product/allProducts?pageNo=0&pageSize=10")
    Call<Productsresponse> listallproducts();

    @GET("tax/allTaxes?pageNo=0&pageSize=10")
    Call<RaxRatesResponse> listallTaxrates();

    @GET("tax/getTaxByOrganization")
    Call<RaxRatesResponse> getRaxRatesResponse(@Query("orgId") long orgId);

    @GET("product/getProductByOrganization?&pageNo=0&pageSize=10000000")
    Call<Productsresponse> getProductByOrganization(@Query("orgId") long orgId);

}
