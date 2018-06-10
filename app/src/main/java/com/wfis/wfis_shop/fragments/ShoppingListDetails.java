package com.wfis.wfis_shop.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.adapters.ProductAdapter;
import com.wfis.wfis_shop.adapters.ShoppingListAdapter;
import com.wfis.wfis_shop.core.BaseFragment;
import com.wfis.wfis_shop.dialogs.ProductEditingDialog;
import com.wfis.wfis_shop.models.Product;
import com.wfis.wfis_shop.models.ShoppingList;
import com.wfis.wfis_shop.models.ShoppingList_Table;

public class ShoppingListDetails extends BaseFragment implements ProductEditingDialog.DialogInteraction {

    private static String ID = "id";


    public static ShoppingListDetails newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ID, id);
        ShoppingListDetails fragment = new ShoppingListDetails();
        fragment.setArguments(args);
        return fragment;
    }

    private int id;
    private ShoppingList shoppingList;
    private TextView name, desc;
    private RecyclerView products;
    private Button addProduct;
    private ProductAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt(ID, -1);
        if (id != -1) {
            shoppingList = SQLite.select().from(ShoppingList.class).where(ShoppingList_Table.id.eq(id)).querySingle();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_detail, container, false);
        findViews(view);
        name.setText(shoppingList.getName());
        desc.setText(shoppingList.getDescription());

        products.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProductAdapter();
        adapter.setNavigationInterface(getNavigation());
        adapter.setData(shoppingList.getProductList());
        products.setAdapter(adapter);

        addProduct.setOnClickListener(view1 -> {
            Product product = new Product();
            product.setName("pomidor");
            product.setValue("5");
            product.setShoppingList(shoppingList);
            product.save();
            adapter.setData(shoppingList.getProductList());
        });

        return view;
    }

    private void findViews(View view) {
        name = view.findViewById(R.id.fragment_list_detail_name);
        desc =view.findViewById(R.id.fragment_list_detail_desc);
        products = view.findViewById(R.id.fragment_list_products);
        addProduct = view.findViewById(R.id.add_product);
    }

    @Override
    public void onDismiss() {
        adapter.notifyDataSetChanged();
    }
}
