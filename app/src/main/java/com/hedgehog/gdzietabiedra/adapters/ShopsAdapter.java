package com.hedgehog.gdzietabiedra.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.anim.AnimationUtils;

import com.hedgehog.gdzietabiedra.pojo.Shops.Shop;
import com.hedgehog.gdzietabiedra.utils.Api;
import com.hedgehog.gdzietabiedra.utils.Biedra;
import com.hedgehog.gdzietabiedra.viewHolders.ShopView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam on 2015-06-18.
 */
public class ShopsAdapter extends RecyclerView.Adapter<ShopView>{

    private List<Shop> mItems;
    private int mPreviousPosition;

    private final List<Shop> filteredItems;

    public ShopsAdapter(List<Shop> data) {
        mItems = data;
        this.filteredItems = new ArrayList<>();
    }

    @Override
    public ShopView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(Biedra.getAppContext()).inflate(R.layout.shop_item, parent, false);
        return new ShopView(view);
    }

    @Override
    public void onBindViewHolder(ShopView holder, int position) {
        Shop item = mItems.get(position);
        holder.bindItem(item);
        if (position > mPreviousPosition) {
            AnimationUtils.animateFadeIn(holder, true);
        } else {
            AnimationUtils.animateFadeIn(holder, false);
        }
        mPreviousPosition = position;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
