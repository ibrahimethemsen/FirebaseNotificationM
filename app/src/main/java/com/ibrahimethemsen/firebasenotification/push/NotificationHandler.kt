package com.ibrahimethemsen.firebasenotification.push


import android.app.NotificationManager

class NotificationHandler(
    private val remoteMessageResolver : RemoteMessageResolver,
    private val notificationBuilder : NotificationBuilder,
    private val notifier : Notifier,
    private val notificationManager: NotificationManager
) {
    fun handle(remoteMessage : Map<String,String>){
        val notificationItem = remoteMessageResolver.resolve(remoteMessage)
        val notificationData = notificationBuilder.build(notificationItem,notificationManager)
        notifier.notify(notificationData)
    }
}