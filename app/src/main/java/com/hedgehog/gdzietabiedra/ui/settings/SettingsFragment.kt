package com.hedgehog.gdzietabiedra.ui.settings

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import com.hedgehog.gdzietabiedra.BuildConfig
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.ui.views.TimePreference
import com.hedgehog.gdzietabiedra.ui.views.TimePreferenceDialog
import org.koin.androidx.viewmodel.compat.ViewModelCompat

class SettingsFragment : PreferenceFragmentCompat() {

    private val vm: SettingsViewModel by ViewModelCompat.viewModel(this, SettingsViewModel::class.java)

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        setHasOptionsMenu(true)

        val appVersion = Preference(context).apply {
            title = "App version"
            summary = "${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE})"
        }
        val aboutPrefCategory = findPreference<PreferenceCategory>("about")
        aboutPrefCategory?.addPreference(appVersion)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_info -> Toast.makeText(requireContext(), "some info!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private val DIALOG_FRAGMENT_TAG = "TimePreference"

    override fun onDisplayPreferenceDialog(preference: Preference) {
        if (parentFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) != null) {
            return
        }
        if (preference is TimePreference) {
            val f: DialogFragment = TimePreferenceDialog.newInstance(preference.key)
            f.setTargetFragment(this, 0)
            f.show(parentFragmentManager, DIALOG_FRAGMENT_TAG)
        } else {
            super.onDisplayPreferenceDialog(preference)
        }
    }

//
//    private fun setShareStarsButton() {
//        stars_button.setOnClickListener {
//            val appPackageName: String = requireContext().packageName
//
//            val goToMarket = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
//            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
//                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
//                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
//            try {
//                startActivity(goToMarket)
//            } catch (e: ActivityNotFoundException) {
//                startActivity(Intent(Intent.ACTION_VIEW,
//                        Uri.parse("http://play.google.com/store/apps/details?id=$appPackageName")))
//            }
//        }
//    }
//
//    private fun setEmailButton() {
//        email_button.setOnClickListener {
//            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//                    "mailto", "app.swiderski@gmail.com", null))
//            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "from GdzieTaBiedra")
//            emailIntent.putExtra(Intent.EXTRA_TEXT, "I love your app but,")
//            requireContext().startActivity(Intent.createChooser(emailIntent, "Send email..."))
//        }
//    }

}