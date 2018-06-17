package com.wfis.wfis_shop;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.wfis.wfis_shop.core.BaseFragment;
import com.wfis.wfis_shop.fragments.HomeFragment;
import com.wfis.wfis_shop.fragments.ListFragment;
import com.wfis.wfis_shop.fragments.NearestC;
import com.wfis.wfis_shop.fragments.ShopMapFragment;
import com.wfis.wfis_shop.navigation.MenuView;
import com.wfis.wfis_shop.navigation.NavigationInterface;
import com.wfis.wfis_shop.navigation.TopBar;
import com.wfis.wfis_shop.rest.Rest;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationInterface, MenuView.MenuInteractions, TopBar.TopBarInteractions, FragmentManager.OnBackStackChangedListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private TopBar topBar;
    private DrawerLayout drawerLayout;
    private MenuView menuView;
    private FragmentManager manager;
    private static final int PERMISSION_REQUEST_CAMERA = 0;

    ArrayList<String> permissions=new ArrayList<>();
    PermissionUtils permissionUtils;
    boolean isPermissionGranted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);

/*        permissionUtils=new PermissionUtils(MainActivity.this);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        permissionUtils.check_permission(permissions,"Need GPS permission for getting your location",1);*/


/*        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        PERMISSION_REQUEST_CAMERA);
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        PERMISSION_REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }*/

        boolean wifiEnabled = wifiManager.isWifiEnabled();
        Rest.init();
        manager = getSupportFragmentManager();
        findViews();
        setListeners();
        if(savedInstanceState==null) {
            changeFragment(HomeFragment.newInstance(), false, HomeFragment.title());
        }
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
        navigateTo(fragment, true, "");
    }

    @Override
    public void changeFragment(BaseFragment fragment, boolean addToBackStack, String title) {
        navigateTo(fragment, addToBackStack,title);
    }

    private void navigateTo(Fragment fragment, boolean addToBackStack, String title) {
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

        if(!title.equals("")) {

            topBar.setTitlebar(title);

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
        //manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        //changeFragment(HomeFragment.newInstance());
        changeFragment(HomeFragment.newInstance(),true,"NEWSY");
        closeDrawer();
    }

    @Override
    public void onListClick() {
        //manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        //changeFragment(ListFragment.newInstance(),true,ListFragment.title());
        changeFragment(ListFragment.newInstance(),true,"REPERTUAR");
        closeDrawer();
    }

    @Override
    public void onMapClickx() {

        changeFragment(ShopMapFragment.newInstance(),true,"MAPA KIN");
        closeDrawer();


    }


    @Override
    public void onNearClick() {

        changeFragment(NearestC.newInstance(),true,NearestC.title());
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




    public void setTitle(String title) {

        topBar.setTitlebar(title);
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

    // Permission check functions


/*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                    Log.w("BaseActivity", "Permission granted");
                //startCamera();
            } else {
                // Permission request was denied.
                Log.w("BaseActivity", "Permission was denied");
            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }*/

}
