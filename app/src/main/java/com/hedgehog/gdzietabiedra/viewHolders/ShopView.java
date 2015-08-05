package com.hedgehog.gdzietabiedra.viewHolders;

import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop;
import com.hedgehog.gdzietabiedra.utils.MessageEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Adam on 2015-06-18.
 */
public class ShopView extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView name;
    TextView shopAddress;
    TextView shopDistance;
    Shop mItem;

    public ShopView(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.shopName);
        shopAddress = (TextView) itemView.findViewById(R.id.shopAddress);
        shopDistance = (TextView) itemView.findViewById(R.id.shopDistance);
        itemView.setOnClickListener(this);
    }

    public void bindItem(Shop item) {
        mItem = item;
        name.setText(item.getName());
        shopAddress.setText(item.getStreet() + " " + item.getStreetNumber());
        shopDistance.setText(Math.round(Double.parseDouble(item.getDistance())) + " m");
    }

    @Override
    public void onClick(View v) {
        Log.d("click", mItem.toString());
        EventBus.getDefault().post(new MessageEvent("pokaż sklep", mItem, MessageEvent.types.ITEM_CLICK));
    }
}
