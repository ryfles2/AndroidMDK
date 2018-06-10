package com.wfis.wfis_shop.rest;

import com.wfis.wfis_shop.models.RecipesResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RestInterface {

    @GET("bins/i6wgj")
    Call<RecipesResponse> getRecipesCategories();

}
