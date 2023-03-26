package com.ibrahimethemsen.firebasenotification.addnotification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimethemsen.firebasenotification.model.PushNotification
import com.ibrahimethemsen.firebasenotification.service.NotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNotificationViewModel @Inject constructor(
    private val notificationService : NotificationService
) : ViewModel() {
    fun pushNotification(pushNotification : PushNotification){
        viewModelScope.launch {
            try {
                val response = notificationService.postNotification(pushNotification)
                if (response.isSuccessful){
                    println("cevap başarılır $response")
                }else{
                    println("cevap başarısız ${response.errorBody()}")
                }
            }catch (e : Exception){
                println("hata ${e.printStackTrace()}")
            }
        }
    }
}