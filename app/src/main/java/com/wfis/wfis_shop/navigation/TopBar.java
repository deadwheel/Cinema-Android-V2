package com.wfis.wfis_shop.navigation;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wfis.wfis_shop.R;

public class TopBar extends RelativeLayout {

    private ImageView backArrow, hamburger;
    private TextView titlebar;
    private TopBarInteractions topBarInteractions;

    public TopBar(Context context) {
        super(context);
        init(context, null, -1, -1);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, -1, -1);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, -1);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TopBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        inflate(context, R.layout.top_bar, this);

        backArrow = findViewById(R.id.back_arrow);
        hamburger = findViewById(R.id.hamburger);
        titlebar = findViewById(R.id.title_bar);

        hamburger.setOnClickListener(view -> {
            if (topBarInteractions != null) {
                topBarInteractions.onHamburgerClick();
            }
        });

        backArrow.setOnClickListener(view -> {
            if (topBarInteractions != null) {
                topBarInteractions.onBackArrowClick();
            }
        });
    }

    public void showBackArrow(boolean shouldShow) {
        if (shouldShow) {
            backArrow.setVisibility(VISIBLE);
        } else {
            backArrow.setVisibility(GONE);
        }
    }

    public void setTitlebar(String title) {

        titlebar.setText(title);
    }

    public void setTopBarInteractions(TopBarInteractions interactions) {
        this.topBarInteractions = interactions;
    }

    public interface TopBarInteractions {
        void onHamburgerClick();
        void onBackArrowClick();
    }
}
