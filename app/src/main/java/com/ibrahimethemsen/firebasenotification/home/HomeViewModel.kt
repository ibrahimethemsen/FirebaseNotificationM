package com.ibrahimethemsen.firebasenotification.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.ibrahimethemsen.firebasenotification.model.PostModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fbDatabase: FirebaseFirestore
) : ViewModel() {

    private var _postList = MutableLiveData<List<PostModel>>()
    val postList: LiveData<List<PostModel>>
        get() = _postList

    init {
        firebaseGetData()
    }

    private fun firebaseGetData() {
        fbDatabase.collection("post").addSnapshotListener { value, _ ->
            try {
                val list = mutableListOf<PostModel>()
                if (value != null && !value.isEmpty) {
                    val documents = value.documents
                    for (document in documents) {
                        val title = document.get("title") as String?
                        val message = document.get("message") as String?
                        val uuid = document.id
                        val post = PostModel(title, message, uuid)
                        list.add(post)
                    }
                    _postList.value = list
                }
            } catch (e: Exception) {
                println("Exception ${e.localizedMessage}")
            }
        }
    }
}