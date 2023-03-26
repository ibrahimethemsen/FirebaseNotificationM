package com.ibrahimethemsen.firebasenotification.push


import android.app.NotificationManager
import com.ibrahimethemsen.firebasenotification.model.NotificationBuild

class Notifier(
    private val notificationManager : NotificationManager
) {
    fun notify(notificationData : NotificationBuild){
        with(notificationData){
            notificationManager.notify(notificationId,systemNotification)
        }
    }
}

