package com.wfis.wfis_shop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.dialogs.ProductEditingDialog;
import com.wfis.wfis_shop.models.Product;
import com.wfis.wfis_shop.models.Product_Table;
import com.wfis.wfis_shop.models.ShoppingList_Table;
import com.wfis.wfis_shop.navigation.NavigationInterface;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements ProductEditingDialog.DialogInteraction {

    private List<Product> data = new ArrayList<>();
    private NavigationInterface navigationInterface;

    public void setNavigationInterface(NavigationInterface navigationInterface) {
        this.navigationInterface = navigationInterface;
    }

    public void setData(List<Product> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.productName.setText(data.get(position).getName() + "     " + data.get(position).getValue());

        holder.itemView.setOnClickListener(view -> {

            ProductEditingDialog dialog = ProductEditingDialog.newInstance(data.get(position).getId());
            dialog.setInteraction(this::onDismiss);
            dialog.show(navigationInterface.getFragmentManager(), "");
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onDismiss() {
        setData(SQLite.select().from(Product.class).where(ShoppingList_Table.id.eq(data.get(0).getId())).queryList());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.item_shopping_list_name);
        }
    }
}
