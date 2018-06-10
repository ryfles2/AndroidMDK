package com.wfis.wfis_shop.navigation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wfis.wfis_shop.R;

public class MenuView extends RelativeLayout implements View.OnClickListener {

    private LinearLayout pulpit, list, promotion;
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
        pulpit = findViewById(R.id.pulpit);
        list = findViewById(R.id.list);
        promotion = findViewById(R.id.promocje);

        promotion.setOnClickListener(this);

        pulpit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuInteractions != null) {
                    menuInteractions.onPulpitClick();
                }
            }
        });

        list.setOnClickListener(view -> {
            if (menuInteractions != null) {
                menuInteractions.onListClick();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.promocje:
                Toast.makeText(getContext(), "HURA UDALO SIE", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setMenuInteractions(MenuInteractions menuInteractions) {
        this.menuInteractions = menuInteractions;
    }

    public interface MenuInteractions {
        void onPulpitClick();

        void onListClick();
    }
}
