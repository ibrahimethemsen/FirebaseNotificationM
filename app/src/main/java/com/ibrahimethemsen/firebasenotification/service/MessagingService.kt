package com.ibrahimethemsen.firebasenotification.service

import android.app.NotificationManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ibrahimethemsen.firebasenotification.push.NotificationBuilder
import com.ibrahimethemsen.firebasenotification.push.NotificationHandler
import com.ibrahimethemsen.firebasenotification.push.Notifier
import com.ibrahimethemsen.firebasenotification.push.RemoteMessageResolver
import com.ibrahimethemsen.firebasenotification.push.SystemNotificationMaker

class MessagingService : FirebaseMessagingService(){
    private lateinit var handler : NotificationHandler

    override fun onCreate() {
        super.onCreate()
        val resolver = RemoteMessageResolver()
        val systemNotificationMaker = SystemNotificationMaker(this)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationBuilder(systemNotificationMaker)
        val notifier = Notifier(notificationManager)
        handler = NotificationHandler(resolver,builder,notifier,notificationManager)
    }
    override fun onMessageReceived(pNotification : RemoteMessage) {
        handler.let {
            handler.handle(pNotification.data)
        }
    }
}