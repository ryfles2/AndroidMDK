package com.wfis.wfis_shop.models;

import java.util.List;


public class RecipesResponse {

    private boolean success;
    private List<RecipeCategory> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<RecipeCategory> getData() {
        return data;
    }

    public void setData(List<RecipeCategory> data) {
        this.data = data;
    }
}
