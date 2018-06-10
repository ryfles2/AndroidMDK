package com.wfis.wfis_shop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.models.RecipeCategory;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<RecipeCategory> data = new ArrayList<>();

    public void setData(List<RecipeCategory> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecipeCategory item = data.get(position);

        holder.name.setText(item.getName());
        Glide.with(holder.itemView.getContext())
                .load(item.getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.recipe_category_name);
        }
    }
}
