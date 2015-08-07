package com.hedgehog.gdzietabiedra.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.adapters.ShopsAdapter;
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop;
import com.hedgehog.gdzietabiedra.utils.Database;
import com.hedgehog.gdzietabiedra.utils.DividerItemDecoration;
import com.hedgehog.gdzietabiedra.utils.MessageEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class ShopListFragment extends Fragment {

    RecyclerView recycler;
    ShopsAdapter mAdapter;
    TextView emptyText;
    com.rey.material.widget.EditText searchInput;

    List<Shop> shops = new ArrayList<Shop>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onEvent(MessageEvent event) {
        if(event.type == MessageEvent.types.DATABASE_UPDATE){
            shops.clear();
            shops.addAll(Database.getListClosest());
            checkShopList();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void checkShopList() {
        if (shops.size() == 0){
            emptyText.setVisibility(View.VISIBLE);
        }else{
            emptyText.setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        shops.clear();
        shops.addAll(Database.getListClosest());
        mAdapter = new ShopsAdapter(shops);
        emptyText = (TextView) view.findViewById(R.id.emptyText);

        checkShopList();

        recycler = (RecyclerView) view.findViewById(R.id.shopList);
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(mAdapter);

        searchInput = (com.rey.material.widget.EditText) view.findViewById(R.id.searchInput);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("zmiana", s.toString());
                shops.clear();
                shops.addAll(Database.getByFilter(s.toString()));
                mAdapter.notifyDataSetChanged();
                checkShopList();
                if(s.equals("")){
                    shops.clear();
                    shops.addAll(Database.getListClosest());
                    mAdapter.notifyDataSetChanged();
                    checkShopList();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
