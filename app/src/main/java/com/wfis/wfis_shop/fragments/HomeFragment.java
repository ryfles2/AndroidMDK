package com.wfis.wfis_shop.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.adapters.ViewPagerAdaper;
import com.wfis.wfis_shop.core.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private ViewPager viewPager;
    private View repertoire;
    private View tickets;
    private View map;
    private View event_list;
    private View tutorial;
    private View account;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViews(view);
        setListeners();

        ViewPagerAdaper adaper = new ViewPagerAdaper();
        viewPager.setAdapter(adaper);
        List<String> photos = new ArrayList<>();
        photos.add("http://www.mdkradomsko.pl/system/images/W1siZiIsIjIwMTYvMDQvMDQvMDMvMTAvMTMvMzc2L01ES19sb2dvX1JHQi5qcGVnIl0sWyJwIiwidGh1bWIiLCI5MDB4NjAwIl1d/MDK_logo%20RGB.jpeg");
        photos.add("http://www.mdkradomsko.pl/assets/page-images/news-foto.jpg");
        photos.add("https://www.filepicker.io/api/file/0xFvPm5HSSSbtqi6Awwn/convert?fit=clip&w=270");
        photos.add("https://www.filepicker.io/api/file/UJOIGNaATj23GhRSwSWl/convert?fit=clip&w=270");
        adaper.setPhotos(photos);
        return view;
    }

    private void setListeners() {
        map.setOnClickListener(view -> {
            getNavigation().changeFragment(ShopMapFragment.newInstance());
        });
        repertoire.setOnClickListener(view -> {
            getNavigation().changeFragment(ShopMapFragment.newInstance());
        });
    }

    private void findViews(View view) {
        viewPager = view.findViewById(R.id.view_pager);
        repertoire = view.findViewById(R.id.repertoire);
        tickets = view.findViewById(R.id.tickets);
        map = view.findViewById(R.id.map);
        event_list = view.findViewById(R.id.event_list);
        tutorial = view.findViewById(R.id.tutorial);
        account = view.findViewById(R.id.account);
    }
}
