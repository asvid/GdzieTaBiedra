package com.hedgehog.gdzietabiedra.ui.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.preference.*
import com.hedgehog.gdzietabiedra.BuildConfig
import com.hedgehog.gdzietabiedra.R
import org.koin.androidx.viewmodel.compat.ViewModelCompat
import java.time.LocalTime

class SettingsFragment : PreferenceFragmentCompat() {

    private val vm: SettingsViewModel by ViewModelCompat.viewModel(this, SettingsViewModel::class.java)

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        setHasOptionsMenu(true)

        val appVersion = Preference(context).apply {
            title = getString(R.string.app_version)
            summary = "${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE})"
        }
        val aboutPrefCategory = findPreference<PreferenceCategory>("about")
        aboutPrefCategory?.addPreference(appVersion)

        findPreference<SwitchPreferenceCompat>("shopping_sunday_notification")?.setOnPreferenceChangeListener { preference, newValue -> vm.handleShoppingSundayNotificatonChange(newValue) }
        findPreference<SeekBarPreference>("shopping_sunday_notification_days_before")?.setOnPreferenceChangeListener { preference, newValue -> vm.handleShoppingSundayNotificationDaysBeforeChange(newValue as Int) }
        findPreference<TimePreference>("shopping_sunday_notification_time")?.setOnPreferenceChangeListener { preference, newValue -> vm.handleShoppingSundayNotificationTimeChange(newValue as LocalTime) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_info -> showAppInfo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAppInfo() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.info)
            setMessage(R.string.info_text)
            setPositiveButton(R.string.share_some_stars) { _, _ -> goToAppInStore() }
            setNegativeButton(R.string.email_me) { _, _ -> sendEmailFeedback() }
        }.show()
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

    private fun goToAppInStore() {
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

    private fun sendEmailFeedback() {
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "app.swiderski@gmail.com", null))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "from GdzieTaBiedra")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "I love your app but,")
        requireContext().startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }

}