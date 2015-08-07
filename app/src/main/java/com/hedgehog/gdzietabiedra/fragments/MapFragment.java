package com.hedgehog.gdzietabiedra.fragments;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop;
import com.hedgehog.gdzietabiedra.utils.Biedra;
import com.hedgehog.gdzietabiedra.utils.Database;
import com.hedgehog.gdzietabiedra.utils.MessageEvent;
import com.hedgehog.gdzietabiedra.utils.PopupAdapter;
import com.rey.material.widget.Button;

import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap map;
    private Button naviOn;
    private HashMap<String, Marker> markerList = new HashMap<String, Marker>();
    private Marker currentMarker;
    private GoogleMap.OnMyLocationChangeListener passiveListener;
    private LocationManager mLocationManager;
    private Location lastLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void onEvent(MessageEvent event) {
        if(event.shop != null){
            Marker clickedMarker = markerList.get(event.shop.getId());
            if(clickedMarker!=null){
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(clickedMarker.getPosition(), 16));
                clickedMarker.showInfoWindow();
            }else{
                Marker marker = map.addMarker(new MarkerOptions()
                                .position(new LatLng(Double.parseDouble(event.shop.getLatitude()), Double.parseDouble(event.shop.getLongitude())))
                                .title(event.shop.getName())
                                .snippet(event.shop.getStreet() + " " + event.shop.getStreetNumber() + "\n" +
                                                "pn-pt: " + event.shop.getHours() + "\n" +
                                                "so: " + event.shop.getHoursSaturday() + "\n" +
                                                "nd: " + event.shop.getHoursSunday() + "\n"
                                )
                                .flat(false)
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                );
                markerList.put(event.shop.getId(), marker);
            }
        }
        if(event.type == MessageEvent.types.DATABASE_UPDATE){
            Log.d("event", "update map");
            putMarkers();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment fragment = new SupportMapFragment();
        //SupportMapFragment fm = (SupportMapFragment)  getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        getFragmentManager().beginTransaction() .add(R.id.map, fragment).commit();
        naviOn = (Button) view.findViewById(R.id.naviOn);
        naviOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation();
            }
        });
        fragment.getMapAsync(this);
        return view;
    }

    private void navigation() {
        if(currentMarker != null){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr="+currentMarker.getPosition().latitude + "," + currentMarker.getPosition().longitude));
            startActivity(intent);
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setTrafficEnabled(false);
        map.getUiSettings().setMapToolbarEnabled(false);

        MapsInitializer.initialize(Biedra.getAppContext());

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
        map.animateCamera(zoom);


        map.setInfoWindowAdapter(new PopupAdapter(getActivity().getLayoutInflater()));
        map.setOnMarkerClickListener(this);
        map.setOnMapClickListener(this);

        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                putMarkers();
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(location.getLatitude(),location.getLongitude()), 16));
                map.setOnMyLocationChangeListener(passiveListener);
            }
        });
    }

    private void putMarkers() {
        if(map!=null){
            map.clear();
        }

        List<Shop> shops = Database.getListClosest();
        for(int i = 0, l = shops.size(); i<l ; i++){
            Shop current = shops.get(i);
            Marker marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(current.getLatitude()), Double.parseDouble(current.getLongitude())))
                            .title(current.getName())
                            .snippet(current.getStreet() + " " + current.getStreetNumber() + "\n" +
                                            "pn-pt: " + current.getHours() + "\n" +
                                            "so: " + current.getHoursSaturday() + "\n" +
                                            "nd: " + current.getHoursSunday() + "\n"
                            )
                            .flat(false)
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
            );
            markerList.put(current.getId(), marker);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        currentMarker = marker;
        naviOn.setVisibility(View.VISIBLE);
        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        currentMarker = null;
        naviOn.setVisibility(View.GONE);
    }
}
