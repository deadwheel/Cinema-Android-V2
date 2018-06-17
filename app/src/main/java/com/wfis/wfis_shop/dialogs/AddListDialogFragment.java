package com.wfis.wfis_shop.dialogs;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wfis.wfis_shop.R;

import org.w3c.dom.Text;

import java.nio.InvalidMarkException;

/**
 * Created by kacper on 27.03.2018.
 */

public class AddListDialogFragment extends DialogFragment {

    String tytul;
    int ilosc_norm = 0;
    int ilosc_ulg = 0;
    TextView iloscNOR;
    TextView iloscULG;

    public static AddListDialogFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title",title);
        AddListDialogFragment fragment = new AddListDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private DialogInteraction interaction;

    public void setInteraction(DialogInteraction interaction) {
        this.interaction = interaction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tytul = getArguments().getString("title");
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
    }

    @Override
    public void onStart() {
        super.onStart();
        //getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_add_list, container, false);
        Button add = view.findViewById(R.id.add);
        Button addnormalne = view.findViewById(R.id.add_normalne);
        Button minnormalne = view.findViewById(R.id.min_normalne);
        Button addulgowe = view.findViewById(R.id.add_ulgowe);
        Button minulgowe = view.findViewById(R.id.min_ulgowe);
        iloscNOR = view.findViewById(R.id.normalne_ilosc);
        iloscULG = view.findViewById(R.id.ulgowe_ilosc);
        TextView tyt = view.findViewById(R.id.tytul);
        ImageView obrazek = view.findViewById(R.id.image);
        tyt.setText(tytul);
        iloscNOR.setText(Integer.toString(ilosc_norm));
        iloscULG.setText(Integer.toString(ilosc_ulg));


        Glide.with(this)
                .load(R.drawable.miejsca)
                .into(obrazek);


        add.setOnClickListener(view1 -> {

            interaction.onDismiss();
            dismiss();
        });


        addnormalne.setOnClickListener(view1 -> {

            ilosc_norm++;
            iloscNOR.setText(String.valueOf(ilosc_norm));


        });

        addulgowe.setOnClickListener(view1 -> {

            ilosc_ulg++;
            iloscULG.setText(String.valueOf(ilosc_ulg));

        });

        minnormalne.setOnClickListener(view1 -> {

            if(ilosc_norm>0) {

                ilosc_norm--;
                iloscNOR.setText(String.valueOf(ilosc_norm));

            }

        });

        minulgowe.setOnClickListener(view1 ->  {

            if(ilosc_ulg>0) {

                ilosc_ulg--;
                iloscULG.setText(String.valueOf(ilosc_ulg));

            }

        });

        return view;
    }

    public interface DialogInteraction {
        void onDismiss();
    }
}
