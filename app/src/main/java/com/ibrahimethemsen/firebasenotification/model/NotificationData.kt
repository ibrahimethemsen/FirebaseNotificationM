package com.ibrahimethemsen.firebasenotification.model

import androidx.annotation.DrawableRes
import com.ibrahimethemsen.firebasenotification.R

data class NotificationData(
    val title : String,
    val message : String,
    val uuid : String,
    val eventType : String,
    @DrawableRes val notificationIcon : Int = R.drawable.ic_notification,
)
