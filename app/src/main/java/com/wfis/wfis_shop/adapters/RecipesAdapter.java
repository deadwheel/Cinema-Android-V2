package com.wfis.wfis_shop.adapters;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.dialogs.AddListDialogFragment;
import com.wfis.wfis_shop.models.Movies;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<Movies> data = new ArrayList<>();

    public void setData(List<Movies> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movies item = data.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                AddListDialogFragment dialogFragment = AddListDialogFragment.newInstance(item.getTitle());
                dialogFragment.setInteraction(new AddListDialogFragment.DialogInteraction() {
                    @Override
                    public void onDismiss() {

                    }
                });
                dialogFragment.show(activity.getSupportFragmentManager(),"");
            }
        });

        holder.name.setText(item.getTitle());
        Glide.with(holder.itemView.getContext())
                .load(item.getImageMov())
                .into(holder.image);

        holder.produkcja.setText(item.getProd());
        holder.data.setText(item.getDateProd());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView data;
        TextView produkcja;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.tytul);
            data = itemView.findViewById(R.id.data);
            produkcja = itemView.findViewById(R.id.produkcja);


        }
    }
}
