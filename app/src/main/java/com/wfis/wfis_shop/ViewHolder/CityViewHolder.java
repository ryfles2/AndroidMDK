package com.wfis.wfis_shop.ViewHolder;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.models.City;

public class CityViewHolder extends RecyclerView.ViewHolder {
    TextView cityName;


    public CityViewHolder(@NonNull View itemView) {
        super(itemView);
        cityName = itemView.findViewById(R.id.cityName);

    }

    public void setItem(City city){
        cityName.setText(city.name);
    }
}