package com.example.simpletodolist

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class NotificationReceiver : BroadcastReceiver() {
    private val id = "SimpleToDo"
    private val name = "SimpleToDo"

    lateinit var notificationManager : NotificationManager
    lateinit var notification : NotificationCompat.Builder

    override fun onReceive(p0: Context?, p1: Intent?) {

    }
}