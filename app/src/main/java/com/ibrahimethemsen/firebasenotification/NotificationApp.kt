package com.ibrahimethemsen.firebasenotification

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging
import com.ibrahimethemsen.firebasenotification.utility.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotificationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        subscribe()
    }
    private fun subscribe(){
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.TOPIC)
    }
}