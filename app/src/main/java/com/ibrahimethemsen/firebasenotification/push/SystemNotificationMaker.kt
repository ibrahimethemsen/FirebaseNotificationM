package com.ibrahimethemsen.firebasenotification.push


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.ibrahimethemsen.firebasenotification.R
import com.ibrahimethemsen.firebasenotification.detail.DetailFragmentArgs
import com.ibrahimethemsen.firebasenotification.model.NotificationData
import com.ibrahimethemsen.firebasenotification.utility.Constants.DETAIL
import com.ibrahimethemsen.firebasenotification.utility.Constants.REMEMBER

class SystemNotificationMaker(
    private val context: Context
) {
    private val chanelId = "firebase_push"
    private lateinit var pendingIntent: PendingIntent

    fun make(
        notificationData: NotificationData,
        notificationManager: NotificationManager
    ): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChanel(notificationManager)
        }
        pendingIntent(notificationData.eventType,notificationData.uuid)
        return NotificationCompat.Builder(context, chanelId)
            .setContentTitle(notificationData.title)
            .setContentText(notificationData.message)
            .setSmallIcon(notificationData.notificationIcon)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun pendingIntent(eventType: String,uuid : String) {
        pendingIntent = when (eventType) {
            DETAIL -> {
                val args = Bundle()
                args.putString("uuid",uuid)
                DetailFragmentArgs.fromBundle(args)
                providerPendingIntent(R.id.detailFragment,args)
            }
            REMEMBER -> {
                providerPendingIntent(R.id.homeFragment)
            }
            else -> {
                providerPendingIntent(R.id.homeFragment)
            }
        }
    }

    private fun providerPendingIntent(setDestination: Int,args : Bundle? = null): PendingIntent =
        NavDeepLinkBuilder(context)
            .setArguments(args)
            .setGraph(R.navigation.nav_graph)
            .setDestination(setDestination)
            .createPendingIntent()


    @RequiresApi(Build.VERSION_CODES.O)
    fun notificationChanel(notificationManager: NotificationManager) {
        val chanelName = "firebasePush"
        val channel =
            NotificationChannel(chanelId, chanelName, NotificationManager.IMPORTANCE_HIGH).apply {
                description = "firebase_push"
                enableLights(true)
                lightColor = Color.MAGENTA
            }
        notificationManager.createNotificationChannel(channel)
    }
}