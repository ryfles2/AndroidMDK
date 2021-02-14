package com.wfis.wfis_shop.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wfis.wfis_shop.Common.Common;
import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.adapters.CityAdapter;
import com.wfis.wfis_shop.core.BaseFragment;
import com.wfis.wfis_shop.models.City;

import java.util.ArrayList;
import java.util.List;

public class CityFragment extends BaseFragment {


    //private TextView txtTools;
    private View view;


    private TextView selectedCity;
    private List<City> viewItems = new ArrayList<>();
    private CityAdapter adapter;
    private RecyclerView recipesList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    public static CityFragment newInstance() {

        Bundle args = new Bundle();

        CityFragment fragment = new CityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_city, container, false);

        selectedCity = view.findViewById(R.id.selectedCity);
        database= FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        addItemsFromFirebase();
        adapter = new CityAdapter();
        adapter.setNavigationInterface(getNavigation());
        adapter.setData(viewItems, getContext(), selectedCity);


        recipesList = view.findViewById(R.id.recycler_view_city);
        recipesList.setLayoutManager(new LinearLayoutManager(getContext()));
        recipesList.setAdapter(adapter);


        return view;
    }

    private void addItemsFromFirebase() {
        String test =  databaseReference.toString();
        Log.e("TAG", test);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    Log.e("Add City", child.getKey());
                    adapter.setCity(new City(child.getKey()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Read Fail", "Error");
            }
        });
    }


}
