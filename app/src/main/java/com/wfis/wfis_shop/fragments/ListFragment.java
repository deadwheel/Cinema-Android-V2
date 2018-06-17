package com.wfis.wfis_shop.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wfis.wfis_shop.MainActivity;
import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.adapters.RecipesAdapter;
import com.wfis.wfis_shop.core.BaseFragment;
import com.wfis.wfis_shop.dialogs.AddListDialogFragment;
import com.wfis.wfis_shop.models.Movies;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment implements AddListDialogFragment.DialogInteraction {

    public static ListFragment newInstance() {
        return new ListFragment();
    }
    public static String title() { return "REPERTUAR";}

    private RecyclerView shoppingList;
    private Button addList;
    RecipesAdapter adapter;
    List<Movies> filmy = new ArrayList<>();

    @Override
    public void onResume(){
        super.onResume();

        // Set title bar
        ((MainActivity) getActivity()).setTitle("REPERTUAR");
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        shoppingList = view.findViewById(R.id.fragment_list_shopping_list);
        addList = view.findViewById(R.id.fragment_list_add);

        shoppingList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecipesAdapter();
        //adapter.setNavigationInterface(getNavigation());
        shoppingList.setAdapter(adapter);


        filmy = Movies.listAll(Movies.class);
        if(filmy.size() == 0) {

            Movies mov = new Movies();
            mov.setDateProd("27 kwietnia 2018");
            mov.setProd("USA 2018");
            mov.setSpectate(false);
            mov.setTitle("Avengers: wojna bez granic");
            mov.setImageMov("https://www.cinema-city.pl/xmedia-cw/repo/feats/posters/2645S2R-md.jpg");
            mov.save();

            Movies mov1 = new Movies();
            mov1.setDateProd("16 marca 2018");
            mov1.setProd("Wielka Brytania 2018");
            mov1.setSpectate(false);
            mov1.setTitle("Gnomeo i Julia. Tajemnica zaginionych krasnali");
            mov1.setImageMov("https://www.cinema-city.pl/xmedia-cw/repo/feats/posters/2656D2R-md.jpg");
            mov1.save();

            Movies mov2 = new Movies();
            mov2.setDateProd("18 maja 2018");
            mov2.setProd("USA 2018");
            mov2.setSpectate(false);
            mov2.setTitle("Deadpool 2");
            mov2.setImageMov("https://www.cinema-city.pl/xmedia-cw/repo/feats/posters/2615S2R-md.jpg");
            mov2.save();

            Movies mov3 = new Movies();
            mov3.setDateProd("16 marca 2018");
            mov3.setProd("Polska 2018");
            mov3.setSpectate(false);
            mov3.setTitle("Pitbull. Ostatni pies");
            mov3.setImageMov("https://www.cinema-city.pl/xmedia-cw/repo/feats/posters/2702O2R-md.jpg");
            mov3.save();

            filmy = Movies.listAll(Movies.class);

        }
/*        List<RecipeCategory> data = new ArrayList<>();
        RecipeCategory neew =  new RecipeCategory();
        neew.setCategoryId(2);
        neew.setImage("https://www.cinema-city.pl/xmedia-cw/repo/feats/posters/2645S2R-md.jpg");
        neew.setName("Gnomeo i Julia. Tajemnica zaginionych krasnali");
        data.add(neew);
        data.add(neew);
        data.add(neew);
        data.add(neew);
        data.add(neew);
        data.add(neew);*/

        adapter.setData(filmy);
        //adapter.setData(SQLite.select().from(ShoppingList.class).queryList());

        addList.setOnClickListener(v -> {
            AddListDialogFragment dialogFragment = AddListDialogFragment.newInstance("Jakis tytuł");
            dialogFragment.setInteraction(this);
            dialogFragment.show(getFragmentManager(), "");
        });

        return view;
    }

    @Override
    public void onDismiss() {
        //adapter.setData(SQLite.select().from(ShoppingList.class).queryList());
    }
}
