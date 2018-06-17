package com.wfis.wfis_shop.navigation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wfis.wfis_shop.R;

public class MenuView extends RelativeLayout {

    private LinearLayout pulpit, list, promotion, nearest;
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
        nearest = findViewById(R.id.nearest);

        promotion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuInteractions != null) {
                    menuInteractions.onMapClickx();
                }
            }
        });

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


        nearest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuInteractions != null) {
                    menuInteractions.onNearClick();
                }
            }
        });

    }

    public void setMenuInteractions(MenuInteractions menuInteractions) {
        this.menuInteractions = menuInteractions;
    }

    public interface MenuInteractions {
        void onPulpitClick();
        void onNearClick();
        void onListClick();
        void onMapClickx();
    }
}
