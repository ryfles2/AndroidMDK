package com.wfis.wfis_shop.models;

import com.google.gson.annotations.SerializedName;

public class RecipeCategory {

    private String name;

    @SerializedName("category_id")
    private int categoryId;

    @SerializedName("img_link")
    private String image;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
