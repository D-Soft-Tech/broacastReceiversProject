package com.example.broadcastreceivers.ui.activities

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.broadcastreceivers.R
import com.example.broadcastreceivers.receivers.AppBroadcastReceivers

class MainActivity : AppCompatActivity() {
    private lateinit var receivers: AppBroadcastReceivers
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receivers = AppBroadcastReceivers()
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receivers, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receivers)
    }
}
