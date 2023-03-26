package com.ibrahimethemsen.firebasenotification.push


import com.ibrahimethemsen.firebasenotification.R
import com.ibrahimethemsen.firebasenotification.model.NotificationData
import com.ibrahimethemsen.firebasenotification.utility.Constants.DETAIL
import com.ibrahimethemsen.firebasenotification.utility.Constants.EVENT_TYPE
import com.ibrahimethemsen.firebasenotification.utility.Constants.REMEMBER

class RemoteMessageResolver {
    fun resolve(remoteMessage : Map<String,String>) : NotificationData {
        val title = remoteMessage["title"]
        val message = remoteMessage["message"]
        val uuid = remoteMessage["uuid"]
        return when(val eventType = remoteMessage[EVENT_TYPE]){
            REMEMBER -> NotificationData(title!!,message!!,uuid!!,eventType, R.drawable.ic_remember)
            DETAIL -> NotificationData(title!!,message!!,uuid!!,eventType, R.drawable.ic_notification)
            else -> {
                NotificationData("Yeniliklere Göz at","Yeniliklere göz atmak için uygulamayı ziyaret et","", REMEMBER,R.drawable.ic_light)
            }
        }
    }
}