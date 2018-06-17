package com.wfis.wfis_shop.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.wfis.wfis_shop.R;
import com.wfis.wfis_shop.models.Shop;

public class ShopClusterRenderer extends DefaultClusterRenderer<Shop> {

    private int MIN_MARKERS = 4;
    private IconGenerator iconGenerator;

    public ShopClusterRenderer(Context context, GoogleMap map, ClusterManager<Shop> clusterManager) {
        super(context, map, clusterManager);
        setupCluster(context, map, clusterManager);

    }

    private void setupCluster(Context context, GoogleMap map, ClusterManager<Shop> clusterManager) {
        iconGenerator = createClusterIconGenerator(context, LayoutInflater.from(context), R.drawable.green_circle);
    }

    @Override
    protected void onBeforeClusterItemRendered(Shop item, MarkerOptions markerOptions) {
        markerOptions.anchor(0.35f,0f);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.clapperboard));
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<Shop> cluster, MarkerOptions markerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon("" + cluster.getSize())));

    }


    @Override
    protected boolean shouldRenderAsCluster(Cluster<Shop> cluster) {
        return cluster.getSize() >= MIN_MARKERS;
    }

    protected IconGenerator createClusterIconGenerator(Context context, LayoutInflater layoutInflater, @DrawableRes int drawableRes) {
        View clusterView = layoutInflater.inflate(R.layout.cluster_view, null, false);
        ((ImageView) clusterView.findViewById(R.id.cluster_view_image))
                .setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), drawableRes, null));

        IconGenerator iconGenerator = new IconGenerator(context);
        iconGenerator.setContentView(clusterView);
        iconGenerator.setBackground(null);

        return iconGenerator;
    }
}
