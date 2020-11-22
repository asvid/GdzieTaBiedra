package com.hedgehog.gdzietabiedra.ui.info

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hedgehog.gdzietabiedra.BuildConfig
import com.hedgehog.gdzietabiedra.R
import kotlinx.android.synthetic.main.info_fragment.*
import org.koin.androidx.viewmodel.compat.ViewModelCompat


class InfoFragment : Fragment() {

    private val vm: InfoViewModel by ViewModelCompat.viewModel(this, InfoViewModel::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        setEmailButton()
        setShareStarsButton()
        setAppVersionText()
    }

    private fun setAppVersionText() {
        app_version_text.text = "${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE})"
    }

    private fun setShareStarsButton() {
        stars_button.setOnClickListener {
            val appPackageName: String = requireContext().packageName

            val goToMarket = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            try {
                startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
    }

    private fun setEmailButton() {
        email_button.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "app.swiderski@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "from GdzieTaBiedra")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "I love your app but,")
            requireContext().startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
    }
}