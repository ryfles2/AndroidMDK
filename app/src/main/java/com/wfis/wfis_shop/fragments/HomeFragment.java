package com.wfis.wfis_shop.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    final long DELAY_MS = 3000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;



    private ViewPager viewPager;
    private View repertoire;
    private View tickets;
    private View map;
    private View event_list;
    private View tutorial;
    private View account;
    private View city;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViews(view);
        setListeners();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        ViewPagerAdaper adaper = new ViewPagerAdaper();
        viewPager.setAdapter(adaper);
        List<String> photos = new ArrayList<>();
        photos.add("https://mojegizycko.pl/wp-content/uploads/2018/12/Otwarcie_Kino_Nowa_Fala_17-06-2017_fot_Tomasz_Karolski_nr_2.jpg");
        photos.add("https://oknonawagrowiec.pl/wp-content/uploads/2019/10/kino.png");
        photos.add("https://www.radio90.pl/files/2019/11/cinema-city-cieszyn-kino-r90-1.jpg");
        photos.add("http://gdziebylec.pl/img/obiekty/16485/8bc44c6749_thmb300_pict_156829_jpg.jpg");
        photos.add("https://bi.im-g.pl/im/17/9b/15/z22658583V,Kino.jpg");
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

            if(currentUser != null)
            {
                getNavigation().changeFragment(MyTicketsFragment.newInstance());
            }
            else  Toast.makeText(getContext(),"Please log in",Toast.LENGTH_SHORT).show();
        });
        map.setOnClickListener(view -> {
            getNavigation().changeFragment(ShopMapFragment.newInstance());
        });
//        event_list.setOnClickListener(view -> {
//            getNavigation().changeFragment(fragment_event_list.newInstance());
//        });
        tutorial.setOnClickListener(view -> {
            getNavigation().changeFragment(ToolsFragment.newInstance());
        });
        account.setOnClickListener(view -> {
            getNavigation().changeFragment(LoginFragment.newInstance());
        });
        city.setOnClickListener(view -> {
            getNavigation().changeFragment(CityFragment.newInstance());
        });
    }

    private void findViews(View view) {
        viewPager = view.findViewById(R.id.view_pager);
        repertoire = view.findViewById(R.id.repertoire);
        tickets = view.findViewById(R.id.tickets);
        map = view.findViewById(R.id.map);
//        event_list = view.findViewById(R.id.event_list);
        tutorial = view.findViewById(R.id.tutorial);
        account = view.findViewById(R.id.account);
        city = view.findViewById(R.id.city_home);
    }
}
