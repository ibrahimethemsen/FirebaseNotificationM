package com.ibrahimethemsen.firebasenotification.service

import com.ibrahimethemsen.firebasenotification.model.PushNotification
import com.ibrahimethemsen.firebasenotification.utility.Constants.CONTENT_TYPE
import com.ibrahimethemsen.firebasenotification.utility.Constants.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationService {
    @Headers("Authorization: key=$SERVER_KEY","Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification : PushNotification
    ) : Response<ResponseBody>
}