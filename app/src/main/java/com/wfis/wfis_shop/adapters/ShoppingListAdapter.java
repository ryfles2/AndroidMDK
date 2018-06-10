package com.wfis.wfis_shop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.fragments.ShoppingListDetails;
import com.wfis.wfis_shop.models.ShoppingList;
import com.wfis.wfis_shop.navigation.NavigationInterface;

import java.util.ArrayList;
import java.util.List;


public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private List<ShoppingList> data = new ArrayList<>();
    private NavigationInterface navigationInterface;

    public void setNavigationInterface(NavigationInterface navigationInterface) {
        this.navigationInterface = navigationInterface;
    }

    public void setData(List<ShoppingList> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ShoppingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ShoppingListAdapter.ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(view -> {
            if (navigationInterface != null) {
                navigationInterface.changeFragment(ShoppingListDetails.newInstance(data.get(position).getId()));
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
            name = itemView.findViewById(R.id.item_shopping_list_name);
        }
    }
}
