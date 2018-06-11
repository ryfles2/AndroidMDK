package com.wfis.wfis_shop.fragments;

import android.os.Bundle;
import android.os.Handler;
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
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.




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

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 3) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        return view;
    }

    private void setListeners() {
        repertoire.setOnClickListener(view -> {
            getNavigation().changeFragment(RepertoireFragment.newInstance());
        });
        tickets.setOnClickListener(view -> {
            getNavigation().changeFragment(MyTicketsFragment.newInstance());
        });
        map.setOnClickListener(view -> {
            getNavigation().changeFragment(ShopMapFragment.newInstance());
        });
        event_list.setOnClickListener(view -> {
            getNavigation().changeFragment(ShopMapFragment.newInstance());
        });
        tutorial.setOnClickListener(view -> {
            getNavigation().changeFragment(ToolsFragment.newInstance());
        });
        account.setOnClickListener(view -> {
            getNavigation().changeFragment(LoginFragment.newInstance());
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
