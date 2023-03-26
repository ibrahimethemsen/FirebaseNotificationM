package com.ibrahimethemsen.firebasenotification.push


import android.app.NotificationManager
import com.ibrahimethemsen.firebasenotification.model.NotificationBuild
import com.ibrahimethemsen.firebasenotification.model.NotificationData
import java.util.Random

class NotificationBuilder(
    private val systemNotificationMaker: SystemNotificationMaker
) {
    fun build(notificationData : NotificationData, notificationManager: NotificationManager) : NotificationBuild {
        val systemNotification = systemNotificationMaker.make(notificationData,notificationManager)
        val notificationId = Random().nextInt()
        return NotificationBuild(notificationId,systemNotification)
    }
}