package com.example.broadcastreceivers.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.provider.Settings
import android.widget.Toast
import com.example.broadcastreceivers.R

class AppBroadcastReceivers : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { receivedIntent ->
            context?.let { context ->
                // CHECK FOR AIRPLANE MODE
                if (receivedIntent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                    val isAirplaneOn = Settings.Global.getInt(
                        context.contentResolver,
                        Settings.Global.AIRPLANE_MODE_ON
                    )
                    val airPlaneMode =
                        if (isAirplaneOn > 0) context.getString(R.string.enabled) else context.getString(
                            R.string.disabled
                        )
                    Toast.makeText(
                        context,
                        context.getString(R.string.airplane_mode, airPlaneMode),
                        Toast.LENGTH_LONG
                    ).show()
                }

                // CHECK FOR INTERNET CONNECTIVITY
                val connectivityNoConnection =
                    receivedIntent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
                if (receivedIntent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
                    val isThereConnection =
                        if (connectivityNoConnection) context.getString(R.string.no_connnectivity) else context.getString(
                            R.string.back_online
                        )
                    Toast.makeText(
                        context,
                        isThereConnection,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
