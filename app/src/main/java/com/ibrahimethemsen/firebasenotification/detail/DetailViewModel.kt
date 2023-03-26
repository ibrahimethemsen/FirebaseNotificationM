package com.ibrahimethemsen.firebasenotification.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.ibrahimethemsen.firebasenotification.model.PostModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val fbDatabase: FirebaseFirestore
) : ViewModel() {
    private var _detail = MutableLiveData<PostModel>()
    val detail: LiveData<PostModel>
        get() = _detail

    fun firebaseDetailGetData(uuid: String) {
        try {
            fbDatabase.collection("post").document(uuid).addSnapshotListener { value, _ ->
                if (value != null) {
                    val title = value.get("title") as String?
                    val message = value.get("message") as String?
                    val postId = value.id
                    val pDetail = PostModel(title, message, postId)

                    _detail.value = pDetail
                }
            }
        } catch (e: Exception) {
            println("Exception ${e.localizedMessage}")
        }
    }
}