package com.hedgehog.gdzietabiedra.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.utils.Database
import com.hedgehog.gdzietabiedra.utils.MessageEvent
import com.hedgehog.gdzietabiedra.utils.PopupAdapter
import com.rey.material.widget.Button
import de.greenrobot.event.EventBus
import java.util.*

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private var map: GoogleMap? = null
    private var naviOn: Button? = null
    private val markerList = HashMap<String, Marker>()
    private var currentMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    fun onEvent(event: MessageEvent) {
        if (event.shop != null) {
            val clickedMarker = markerList[event.shop.id]
            if (clickedMarker != null) {
                map!!.animateCamera(CameraUpdateFactory
                                            .newLatLngZoom(clickedMarker.position, 16f))
                clickedMarker.showInfoWindow()
                naviOn!!.visibility = View.VISIBLE
                currentMarker = clickedMarker
            } else {
                val marker = map!!.addMarker(MarkerOptions().position(
                        LatLng(java.lang.Double.parseDouble(
                                event.shop.latitude),
                               java.lang.Double.parseDouble(
                                       event.shop.longitude)))
                                                     .title(event.shop.name).snippet(
                        event.shop.street + " " + event
                                .shop
                                .streetNumber + "\n" + "pn-pt: " + event
                                .shop
                                .hours + "\n" + "so: " + event
                                .shop
                                .hoursSaturday + "\n" + "nd: " + event
                                .shop.hoursSunday + "\n")
                                                     .flat(false).icon(BitmapDescriptorFactory
                                                                               .fromResource(R.mipmap.ic_launcher)))
                markerList.put(event.shop.id!!, marker)
                currentMarker = marker
            }

        }
        if (event.type === MessageEvent.types.DATABASE_UPDATE) {
            Log.d("event", "update map")
            putMarkers()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_map, container, false)
        val fragment = SupportMapFragment()
        //SupportMapFragment fm = (SupportMapFragment)  getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        fragmentManager.beginTransaction().add(R.id.map, fragment)
                .commit()
        naviOn = view.findViewById(R.id.naviOn) as Button
        naviOn!!.setOnClickListener { navigation() }
        fragment.getMapAsync(this)
        return view
    }

    private fun navigation() {
        if (currentMarker != null) {
            val intent = Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse(
                                        "http://maps.google.com/maps?daddr=" + currentMarker!!
                                                .position.latitude + "," + currentMarker!!
                                                .position.longitude))
            startActivity(intent)
        }
    }


    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)

    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map!!.isMyLocationEnabled = true
        map!!.uiSettings.isMyLocationButtonEnabled = true
        map!!.isTrafficEnabled = false
        map!!.uiSettings.isMapToolbarEnabled = false

        MapsInitializer.initialize(context)

        val zoom = CameraUpdateFactory.zoomTo(16f)
        map!!.animateCamera(zoom)


        map!!.setInfoWindowAdapter(
                PopupAdapter(activity.layoutInflater))
        map!!.setOnMarkerClickListener(this)
        map!!.setOnMapClickListener(this)

        map!!.setOnMyLocationChangeListener { location ->
            putMarkers()
            map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    LatLng(location.latitude,
                           location.longitude), 16f))
        }
    }

    private fun putMarkers() {
        if (map != null) {
            map!!.clear()
        }

        val shops = Database.listClosest
        var i = 0
        val l = shops.size
        while (i < l) {
            val current = shops[i]
            val marker = map!!.addMarker(MarkerOptions().position(
                    LatLng(java.lang.Double.parseDouble(current.latitude),
                           java.lang.Double.parseDouble(current.longitude)))
                                                 .title(current.name).snippet(
                    current.street + " " + current
                            .streetNumber + "\n" + "pn-pt: " + current
                            .hours + "\n" + "so: " + current
                            .hoursSaturday + "\n" + "nd: " + current
                            .hoursSunday + "\n").flat(false)
                                                 .icon(BitmapDescriptorFactory
                                                               .fromResource(R.mipmap.ic_launcher)))
            markerList.put(current.id!!, marker)
            i++
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        currentMarker = marker
        naviOn!!.visibility = View.VISIBLE
        return false
    }

    override fun onMapClick(latLng: LatLng) {
        currentMarker = null
        naviOn!!.visibility = View.GONE
    }
}
