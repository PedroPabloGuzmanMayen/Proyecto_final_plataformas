package com.example.proyectofinal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Handle the alarm action here
        // You can show a notification, navigate to a specific screen, etc.
        val taskName = intent?.getStringExtra("taskName") ?: "Unknown Task"

        // For example, navigate to the TaskDetailScreen
        val pendingIntent = NavDeepLinkBuilder(context!!)
            .setArguments(bundleOf("taskName" to taskName))
            .createPendingIntent()

        pendingIntent.send()
    }
}