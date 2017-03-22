package com.hedgehog.gdzietabiedra.fragments

import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.hedgehog.gdzietabiedra.Di
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop
import com.hedgehog.gdzietabiedra.utils.Database
import com.hedgehog.gdzietabiedra.utils.EventBusClasses
import com.hedgehog.gdzietabiedra.utils.PopupAdapter
import com.rey.material.widget.Button
import de.greenrobot.event.EventBus
import io.reactivex.subjects.ReplaySubject
import java.lang.Double
import kotlin.properties.Delegates

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap
.OnMapClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private var map: GoogleMap by Delegates.notNull()
    private var naviOn: Button by Delegates.notNull()
    private val markerList = HashMap<String, Marker>()
    private var currentMarker: Marker? = null
    private var observable: ReplaySubject<GoogleMap> = ReplaySubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    private fun createAndSelectMarker(shop: Shop) {
        val marker = map.addMarker(MarkerOptions().position(
                LatLng(Double.parseDouble(
                        shop.latitude),
                       Double.parseDouble(
                               shop.longitude)))
                                           .title(shop.name).snippet(
                shop.street + " " + shop.streetNumber + "\n" + "pn-pt: " +
                        shop.hours + "\n" + "so: " +
                        shop.hoursSaturday + "\n" + "nd: " +
                        shop.hoursSunday + "\n")
                                           .flat(false).icon(BitmapDescriptorFactory
                                                                     .fromResource(R.mipmap.ic_launcher)))
        markerList.put(shop.id!!, marker)
        selectMarker(marker)
    }

    private fun selectMarker(clickedMarker: Marker) {
        map.animateCamera(CameraUpdateFactory
                                  .newLatLngZoom(clickedMarker.position, 16f))
        clickedMarker.showInfoWindow()
        naviOn.visibility = View.VISIBLE
        currentMarker = clickedMarker
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        val fragment = SupportMapFragment()
        fragmentManager.beginTransaction().add(R.id.map, fragment)
                .commit()
        naviOn = view.findViewById(R.id.naviOn) as Button
        naviOn.setOnClickListener { openNavigation() }
        fragment.getMapAsync(this)
        return view
    }

    private fun openNavigation() {
        if (currentMarker != null) {
            val intent = Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse(
                                        "http://maps.google.com/maps?daddr=" + currentMarker!!
                                                .position.latitude + "," + currentMarker!!
                                                .position.longitude))
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        setCurrentLocationOnMap()
        map.isMyLocationEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = true
        map.isTrafficEnabled = false
        map.uiSettings.isMapToolbarEnabled = false

        MapsInitializer.initialize(context)
        map.setInfoWindowAdapter(
                PopupAdapter(activity.layoutInflater))
        map.setOnMarkerClickListener(this)
        map.setOnMapClickListener(this)
        putMarkers()

        observable.onNext(map)
    }

    private fun setCurrentLocationOnMap() {
        val lastLocation = Di.locationService.getLastLocation()
        if (lastLocation != null)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    LatLng(lastLocation.latitude,
                           lastLocation.longitude), 15f))
        else if (mLastLocation != null) {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    LatLng(mLastLocation!!.latitude,
                           mLastLocation!!.longitude), 15f))
        }
    }

    private fun putMarkers() {
        map.clear()
        val shops = Database.listClosest
        shops.map {
            markerList.put(it.id!!, getMarkerForShop(it))
        }
    }

    private fun getMarkerForShop(it: Shop): Marker {
        return map.addMarker(MarkerOptions().position(
                LatLng(Double.parseDouble(it.latitude),
                       Double.parseDouble(it.longitude)))
                                     .title(it.name).snippet(
                it.street + " " +
                        it.streetNumber + "\n" + "pn-pt: " +
                        it.hours + "\n" + "so: " +
                        it.hoursSaturday + "\n" + "nd: " +
                        it.hoursSunday + "\n").flat(false)
                                     .icon(BitmapDescriptorFactory
                                                   .fromResource(R.mipmap.ic_launcher)))
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        currentMarker = marker
        naviOn.visibility = View.VISIBLE
        return false
    }

    override fun onMapClick(latLng: LatLng) {
        currentMarker = null
        naviOn.visibility = View.GONE
    }

    fun setCurrentShop(shopId: String?) {
        val clickedMarker = markerList[shopId]
        if (clickedMarker != null) {
            observable.subscribe {
                selectMarker(clickedMarker)
            }
        } else {
            val shopInfo = Database.getShopInfo(shopId!!).first()
            observable.subscribe {
                createAndSelectMarker(shopInfo)
            }
        }
    }

    fun onEvent(event: EventBusClasses.DatabaseUpdate) {
        putMarkers()
    }

    fun onEvent(event: EventBusClasses.ShopSelected) {
        val shop = event.shop
        val clickedMarker = markerList[shop.id]
        if (clickedMarker != null) {
            selectMarker(clickedMarker)
        } else {
            createAndSelectMarker(shop)
        }

    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    private var mLastLocation: Location? = null

    override fun onConnected(p0: Bundle?) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient)
        observable.subscribe {
            setCurrentLocationOnMap()
        }
    }

    override fun onConnectionSuspended(p0: Int) {
    }

    private var mGoogleApiClient: GoogleApiClient? = null

    fun setMyLocation() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build()
        }
    }
}
