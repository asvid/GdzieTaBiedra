package com.hedgehog.gdzietabiedra.ui.sundays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hedgehog.gdzietabiedra.R

class SundaysFragment : Fragment() {

  private lateinit var sundaysViewModel: SundaysViewModel

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    sundaysViewModel =
        ViewModelProvider(this).get(SundaysViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_sundays, container, false)
    val textView: TextView = root.findViewById(R.id.text_sundays)
    sundaysViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }
}