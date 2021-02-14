package com.wfis.wfis_shop;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wfis.wfis_shop.core.BaseFragment;
import com.wfis.wfis_shop.fragments.CityFragment;
import com.wfis.wfis_shop.fragments.fragment_event_list;
import com.wfis.wfis_shop.fragments.HomeFragment;
import com.wfis.wfis_shop.fragments.LoginFragment;
import com.wfis.wfis_shop.fragments.MyTicketsFragment;
import com.wfis.wfis_shop.fragments.RepertoireFragment;
import com.wfis.wfis_shop.fragments.ShopMapFragment;
import com.wfis.wfis_shop.fragments.ToolsFragment;
import com.wfis.wfis_shop.navigation.MenuView;
import com.wfis.wfis_shop.navigation.NavigationInterface;
import com.wfis.wfis_shop.navigation.TopBar;

public class MainActivity extends AppCompatActivity implements NavigationInterface, MenuView.MenuInteractions, TopBar.TopBarInteractions, FragmentManager.OnBackStackChangedListener {

    private TopBar topBar;
    private DrawerLayout drawerLayout;
    private MenuView menuView;
    private FragmentManager manager;
    private static  BaseFragment statFragment = HomeFragment.newInstance();
    private static  BaseFragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        findViews();
        setListeners();

        if (statFragment.toString().startsWith("HomeFragment")) changeFragment(statFragment, false);
        else changeFragment(statFragment, true);

        lastFragment = statFragment;

    }

    private void findViews() {
        topBar = findViewById(R.id.top_bar);
        drawerLayout = findViewById(R.id.drawer_layout);
        menuView = findViewById(R.id.menu);
    }

    private void setListeners() {
        menuView.setMenuInteractions(this);
        topBar.setTopBarInteractions(this);
        manager.addOnBackStackChangedListener(this);
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void changeFragment(BaseFragment fragment) {

        //Toast.makeText(this,fragment.toString() +"  "+ MyTicketsFragment.class.toString(),Toast.LENGTH_SHORT).show();

//        if (fragment.toString().startsWith("MyTicketsFragment")) setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
//        else setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED );


        statFragment=fragment;

        if (!lastFragment.toString().startsWith("MyTicketsFragment"))  navigateTo(fragment, true);

        lastFragment = fragment;
    }


    @Override
    public void changeFragment(BaseFragment fragment, boolean addToBackStack) {
        navigateTo(fragment, addToBackStack);
        lastFragment = fragment;
    }

    private void navigateTo(Fragment fragment, boolean addToBackStack) {
        if (manager == null || fragment == null) {
            return;
        }

        if (manager.getFragments() != null) {
            Fragment current = manager.findFragmentById(R.id.fragment_container);
            if (current != null && fragment.getClass().equals(current.getClass())) {
                Log.w("BaseActivity", "Fragment navigation failed, possible duplicate entry %s");
            }
            //home
            if (fragment.getClass().equals(HomeFragment.class)) {
                topBar.showBackArrow(false);
            }
            else
            {
                topBar.showBackArrow(true);
            }

        }

        FragmentTransaction transaction = manager.beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(fragment.toString());
        }
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void goBack() {
        getSupportFragmentManager().popBackStack();
    }

    // -------------- MENU INTERACTIONS !
    @Override
    public void onHomeClick() {
        changeFragment(HomeFragment.newInstance());
        closeDrawer();
    }

    @Override
    public void onRepertoireClick() {
        changeFragment(RepertoireFragment.newInstance());
        closeDrawer();
    }

    @Override
    public void onTicketsClick() {
        changeFragment(MyTicketsFragment.newInstance());
        closeDrawer();
    }

    @Override
    public void onMapClick() {
        changeFragment(ShopMapFragment.newInstance());
        closeDrawer();
    }

    @Override
    public void onEventsClick() {
        changeFragment(fragment_event_list.newInstance());
        closeDrawer();
    }

    @Override
    public void onTutorialClick() {
        changeFragment(ToolsFragment.newInstance());
        closeDrawer();
    }

    @Override
    public void onAccountClick() {
        changeFragment(LoginFragment.newInstance());
        closeDrawer();
    }

    @Override
    public void onCityClick() {
        changeFragment(CityFragment.newInstance());
        closeDrawer();
    }



    // ---------------- TopBar INTERACTIONS
    @Override
    public void onHamburgerClick() {
        openDrawer();
    }

    @Override
    public void onBackArrowClick() {
        //onBackPressed();
        changeFragment(HomeFragment.newInstance());
    }

    // ------------------- BACKSTACK

    @Override
    public void onBackStackChanged() {
//        if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {
//            topBar.showBackArrow(true);
//        } else {
//            topBar.showBackArrow(false);
//        }



    }
}
