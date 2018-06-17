package com.wfis.wfis_shop.navigation;

import android.app.FragmentManager;

import com.wfis.wfis_shop.core.BaseFragment;

public interface NavigationInterface {

    void changeFragment(BaseFragment fragment);
    void changeFragment(BaseFragment fragment, boolean addToBackStack, String title);
    void goBack();
    FragmentManager getFragmentManager();

}
