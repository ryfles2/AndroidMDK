package com.wfis.wfis_shop.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.adapters.RecipesAdapter;
import com.wfis.wfis_shop.core.BaseFragment;
import com.wfis.wfis_shop.models.RecipeCategory;
import com.wfis.wfis_shop.models.RecipesResponse;
import com.wfis.wfis_shop.rest.Rest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesFragment extends BaseFragment {

    private RecyclerView recipesList;
    private RecipesAdapter adapter;

    public static RecipesFragment newInstance() {

        Bundle args = new Bundle();

        RecipesFragment fragment = new RecipesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        adapter = new RecipesAdapter();
        recipesList = view.findViewById(R.id.recipes_list);
        recipesList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recipesList.setAdapter(adapter);
        Rest.getRestInterface().getRecipesCategories().enqueue(new Callback<RecipesResponse>() {
            @Override
            public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setData(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {
                Toast.makeText(getContext(), "BRAK INTERNETU", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private List<RecipeCategory> testdata() {
        List<RecipeCategory> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            RecipeCategory item = new RecipeCategory();
            item.setName("name " + i);
            item.setImage("https://i.wpimg.pl/985x0/m.fotoblogia.pl/1-029869d4f35786fed476f24f738d2d,0,0,0,0,8-0,1736-1152.jpg");
            list.add(item);
        }
        return list;
    }
}
