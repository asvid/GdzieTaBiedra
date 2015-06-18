package com.hedgehog.gdzietabiedra.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.adapters.ShopsAdapter;
import com.hedgehog.gdzietabiedra.utils.Database;

public class ShopListFragment extends Fragment {

    RecyclerView recycler;
    ShopsAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ShopsAdapter(Database.getAll());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recycler = (RecyclerView) view.findViewById(R.id.shopList);

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(mAdapter);

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
