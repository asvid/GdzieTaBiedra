package com.hedgehog.gdzietabiedra.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop;

/**
 * Created by Adam on 2015-06-18.
 */
public class ShopView extends RecyclerView.ViewHolder {

    TextView name;

    public ShopView(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.shopName);
    }

    public void bindItem(Shop item) {
        name.setText(item.getName());
    }
}
