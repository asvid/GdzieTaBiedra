package com.hedgehog.gdzietabiedra.ui.settings

import android.app.TimePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.hedgehog.gdzietabiedra.BuildConfig
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.ui.styles.BiedraTheme
import com.hedgehog.gdzietabiedra.ui.styles.SettingsTitle
import org.koin.androidx.viewmodel.compat.ViewModelCompat
import java.time.LocalTime
import kotlin.math.round

class SettingsJetpack : Fragment() {

    private val vm: SettingsViewModel by ViewModelCompat.viewModel(
        this,
        SettingsViewModel::class.java
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return ComposeView(requireContext()).apply {
            setContent {
                BiedraTheme {
                    SettingsContent(
                    )
                }
            }
        }
    }

    @Composable
    fun SettingsContent() {
        // TODO: live data here?
        var notificationsOn by remember { mutableStateOf(vm.getNotificationOn()) }
        var daysSliderValue by remember { mutableStateOf(vm.getNotificationDays().toFloat()) }
        var notificationTime by remember { mutableStateOf(vm.getNotificationTime()) }

        val timePicker = TimePickerDialog(
            context,
            { _: TimePicker, hour: Int, minute: Int ->
                val newValue = LocalTime.of(hour, minute)
                vm.handleShoppingSundayNotificationTimeChange(newValue)
                notificationTime = newValue
            }, notificationTime.hour, notificationTime.minute, true
        )

        return Column {
            Row {
                Icon(Icons.Rounded.Notifications, contentDescription = "Notification icon", modifier = Modifier.padding(8.dp))
                SettingsTitle(getString(R.string.notifications))
            }
            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                Column {
                    Row {
                        Text(getString(R.string.shopping_sunday_notification))
                        Switch(
                            checked = notificationsOn,
                            onCheckedChange = {
                                notificationsOn = !notificationsOn
                                vm.handleShoppingSundayNotificatonChange(notificationsOn)
                            })
                    }

                    Text(
                        getString(R.string.shoping_sunday_seek_bar_label) + " : ${
                            round(
                                daysSliderValue
                            )
                        }"
                    )
                    Row {
                        Slider(
                            valueRange = 0f..10f,
                            steps = 9,
                            value = daysSliderValue,
                            onValueChange = {
                                daysSliderValue = it
                                vm.handleShoppingSundayNotificationDaysBeforeChange(it.toInt())
                            },
                            enabled = notificationsOn
                        )
                    }
                    Row(modifier = Modifier.clickable { timePicker.show() }) {
                        Text(getString(R.string.notification_time) + ": ")
                        Icon(Icons.Rounded.Alarm, contentDescription = "Notification time icon")
                        Text("$notificationTime")
                    }
                }
            }

            Divider(modifier = Modifier.padding(8.dp))

            Row {
                Row {
                    Icon(Icons.Rounded.Info, contentDescription = "Notification icon", modifier = Modifier.padding(8.dp))
                    SettingsTitle(getString(R.string.info))
                }
            }
            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                Column {
                    Text(getString(R.string.app_version) + ": " + BuildConfig.VERSION_NAME)
                }
            }
        }
    }

    @Preview
    @Composable
    fun SettingsContentPreview() {
        SettingsContent(

        )
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

    private fun goToAppInStore() {
        val appPackageName: String = requireContext().packageName

        val goToMarket =
            Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }

    private fun sendEmailFeedback() {
        val emailIntent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "app.swiderski@gmail.com", null
            )
        )
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "from GdzieTaBiedra")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "I love your app but,")
        requireContext().startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }
}