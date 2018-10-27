package com.hedgehog.gdzietabiedra.appservice.map

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.hedgehog.gdzietabiedra.R

class BiedraInfoAdapter(context: Context) : GoogleMap.InfoWindowAdapter {

  private val inflater = LayoutInflater.from(context)

  override fun getInfoContents(p0: Marker?): View? = null

  override fun getInfoWindow(marker: Marker): View {
    val view = inflater.inflate(R.layout.infoview_map, null)
    view.findViewById<TextView>(R.id.infoView_shopName).text = marker.title
    view.findViewById<TextView>(R.id.infoView_openHours).text = marker.snippet
    return view
  }
}