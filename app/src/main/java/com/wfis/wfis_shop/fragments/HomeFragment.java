package com.wfis.wfis_shop.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wfis.wfis_shop.MainActivity;
import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.adapters.ViewPagerAdaper;
import com.wfis.wfis_shop.core.BaseFragment;
import com.wfis.wfis_shop.models.News;
import com.wfis.wfis_shop.rest.Rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    public static String title() { return "NEWSY";}

    private ViewPager viewPager;
    private View findShop;
    private int currentPage = 0;


    @Override
    public void onResume(){
        super.onResume();

        // Set title bar
        ((MainActivity) getActivity()).setTitle("NEWSY");
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((MainActivity) getActivity()).setTitle("NEWSY");
        findViews(view);
        setListeners();

        ViewPagerAdaper adaper = new ViewPagerAdaper();
        viewPager.setAdapter(adaper);
        List<News> photos = new ArrayList<>();
        adaper.setPhotos(photos);

        Rest.getRestInterface().getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                if(response.isSuccessful()) {

                    photos.addAll(response.body());
                    adaper.notifyDataSetChanged();;

                }

            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });




        return view;
    }

    private void setListeners() {
/*        findShop.setOnClickListener(view -> {
            getNavigation().changeFragment(ShopMapFragment.newInstance());
        });*/
    }

    private void findViews(View view) {
        viewPager = view.findViewById(R.id.view_pager);
    }
}
