package com.wfis.wfis_shop.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.adapters.ShoppingListAdapter;
import com.wfis.wfis_shop.core.BaseFragment;
import com.wfis.wfis_shop.dialogs.AddListDialogFragment;
import com.wfis.wfis_shop.models.ShoppingList;

public class ListFragment extends BaseFragment implements AddListDialogFragment.DialogInteraction {

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    private RecyclerView shoppingList;
    private Button addList;
    ShoppingListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        shoppingList = view.findViewById(R.id.fragment_list_shopping_list);
        addList = view.findViewById(R.id.fragment_list_add);

        shoppingList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShoppingListAdapter();
        adapter.setNavigationInterface(getNavigation());
        shoppingList.setAdapter(adapter);
        adapter.setData(SQLite.select().from(ShoppingList.class).queryList());

        addList.setOnClickListener(v -> {
            AddListDialogFragment dialogFragment = AddListDialogFragment.newInstance();
            dialogFragment.setInteraction(this);
            dialogFragment.show(getFragmentManager(), "");
        });

        return view;
    }

    @Override
    public void onDismiss() {
        adapter.setData(SQLite.select().from(ShoppingList.class).queryList());
    }
}
