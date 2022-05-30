package com.pm.primeerp.network;


import com.pm.primeerp.data.retrofit.LoginUser;
import com.pm.primeerp.data.retrofit.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthInterface {

    @POST("auth/login")
    Call<UserResponse> loginUser(@Body LoginUser loginUser);

}
