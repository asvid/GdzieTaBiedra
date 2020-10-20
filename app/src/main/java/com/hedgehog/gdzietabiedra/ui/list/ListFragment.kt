package com.hedgehog.gdzietabiedra.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hedgehog.gdzietabiedra.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel

class ListFragment : Fragment() {

  private val vm: ListViewModel by viewModel(this, ListViewModel::class.java)

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_list, container, false)
    val textView: TextView = root.findViewById(R.id.text_list)
    vm.text.observe(viewLifecycleOwner, {
      textView.text = it
    })

    vm.loadData()
    vm.permissionRequest.observeForever {
      requestLocationPermission(it)
    }
    return root
  }

  private fun requestLocationPermission(permission: String) {
    Dexter.withContext(context)
        .withPermission(permission)
        .withListener(object : PermissionListener {
          override fun onPermissionGranted(response: PermissionGrantedResponse) {
            vm.permissionGranted()
          }

          override fun onPermissionDenied(response: PermissionDeniedResponse) {
          }

          override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?,
              token: PermissionToken?) { /* ... */
          }
        }).check()
  }
}