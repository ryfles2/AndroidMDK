package com.wfis.wfis_shop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wfis.wfis_shop.Common.Common;
import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.fragments.RepertoireFragment;
import com.wfis.wfis_shop.models.City;
import com.wfis.wfis_shop.navigation.NavigationInterface;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>  {


    private List<City> data = new ArrayList<>();
    private NavigationInterface navigationInterface;
    private Context context;
    private TextView selectedCity;

    public void setNavigationInterface(NavigationInterface navigationInterface) {
        this.navigationInterface = navigationInterface;
    }

    public void setData(List<City> data, Context context, TextView selectedCity) {
        this.context = context;
        notifyDataSetChanged();
        this.selectedCity = selectedCity;
    }

    public void setCity(City city) {
        data.add(city);
        notifyDataSetChanged();
    }


    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(CityAdapter.ViewHolder holder, int position) {
        City item = data.get(position);

        holder.name.setText(item.getName());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (navigationInterface != null) {
//                    navigationInterface.changeFragment(RepertoireFragment.newInstance());
                    Common.city=item.getName();
                    selectedCity.setText(item.getName());
//                    Toast.makeText(context, Common.city, Toast.LENGTH_SHORT).show();
//                    Log.e("onClick", Common.city);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cityName);
        }
    }
}
