package com.wfis.wfis_shop.navigation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wfis.wfis_shop.R;

public class MenuView extends RelativeLayout {


    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private LinearLayout home;
    private LinearLayout repertoire;
    private LinearLayout tickets;
    private LinearLayout map;
    private LinearLayout events;
    private LinearLayout tutorial;
    private LinearLayout account;
    private LinearLayout city;
    private MenuInteractions menuInteractions;

    public MenuView(Context context) {
        super(context);
        init(context);
    }

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.menu_view, this);
        home = findViewById(R.id.home);
        repertoire = findViewById(R.id.repertoire);
        tickets = findViewById(R.id.tickets);
        map = findViewById(R.id.map);
        events = findViewById(R.id.events);
        tutorial = findViewById(R.id.tutorial);
        account = findViewById(R.id.account);
        city = findViewById(R.id.city);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        home.setOnClickListener(view -> {
            if (menuInteractions != null) {
                menuInteractions.onHomeClick();
            }
        });

        repertoire.setOnClickListener(view -> {
            if (menuInteractions != null) {
                menuInteractions.onRepertoireClick();
            }
        });

        tickets.setOnClickListener(view -> {
            if (menuInteractions != null) {
                if(currentUser != null)
                {
                    menuInteractions.onTicketsClick();
                }
                else  Toast.makeText(getContext(),"Please log in",Toast.LENGTH_SHORT).show();

            }
        });

        map.setOnClickListener(view -> {
            if (menuInteractions != null) {
                menuInteractions.onMapClick();
            }
        });

        events.setOnClickListener(view -> {
            if (menuInteractions != null) {
                menuInteractions.onEventsClick();
            }
        });

        tutorial.setOnClickListener(view -> {
            if (menuInteractions != null) {
                menuInteractions.onTutorialClick();
            }
        });

        account.setOnClickListener(view -> {
            if (menuInteractions != null) {
                menuInteractions.onAccountClick();
            }
        });

        city.setOnClickListener(view -> {
            if (menuInteractions != null) {
                menuInteractions.onCityClick();
            }
        });
    }



    public void setMenuInteractions(MenuInteractions menuInteractions) {
        this.menuInteractions = menuInteractions;
    }

    public interface MenuInteractions {
        void onHomeClick();
        void onRepertoireClick();
        void onTicketsClick();
        void onMapClick();
        void onEventsClick();
        void onTutorialClick();
        void onAccountClick();
        void onCityClick();
    }
}
