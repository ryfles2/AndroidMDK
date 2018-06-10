package com.wfis.wfis_shop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wfis.wfis_shop.core.BaseFragment;
import com.wfis.wfis_shop.fragments.HomeFragment;
import com.wfis.wfis_shop.fragments.ListFragment;
import com.wfis.wfis_shop.navigation.MenuView;
import com.wfis.wfis_shop.navigation.NavigationInterface;
import com.wfis.wfis_shop.navigation.TopBar;

public class MainActivity extends AppCompatActivity implements NavigationInterface, MenuView.MenuInteractions, TopBar.TopBarInteractions, FragmentManager.OnBackStackChangedListener {

    private TopBar topBar;
    private DrawerLayout drawerLayout;
    private MenuView menuView;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        findViews();
        setListeners();
        changeFragment(HomeFragment.newInstance(), false);
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
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.END);
    }

    @Override
    public void changeFragment(BaseFragment fragment) {
        navigateTo(fragment, true);
    }

    @Override
    public void changeFragment(BaseFragment fragment, boolean addToBackStack) {
        navigateTo(fragment, addToBackStack);
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
    public void onPulpitClick() {
        changeFragment(HomeFragment.newInstance());
        closeDrawer();
    }

    @Override
    public void onListClick() {
        changeFragment(ListFragment.newInstance());
        closeDrawer();
    }


    // ---------------- TopBar INTERACTIONS
    @Override
    public void onHamburgerClick() {
        openDrawer();
    }

    @Override
    public void onBackArrowClick() {
        onBackPressed();
    }

    // ------------------- BACKSTACK

    @Override
    public void onBackStackChanged() {
        if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {
            topBar.showBackArrow(true);
        } else {
            topBar.showBackArrow(false);
        }
    }
}
