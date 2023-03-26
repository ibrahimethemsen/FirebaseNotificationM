package com.ibrahimethemsen.firebasenotification.addnotification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ibrahimethemsen.firebasenotification.R
import com.ibrahimethemsen.firebasenotification.databinding.FragmentAddNotificationBinding
import com.ibrahimethemsen.firebasenotification.model.NotificationData
import com.ibrahimethemsen.firebasenotification.model.PushNotification
import com.ibrahimethemsen.firebasenotification.utility.Constants.TOPIC
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNotificationFragment : BottomSheetDialogFragment() {
    private var _binding : FragmentAddNotificationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AddNotificationViewModel>()
    private val args : AddNotificationFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogStyle)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNotificationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }
    private fun setListener(){
        binding.notificationBtn.setOnClickListener { pushNotification() }
    }
    private fun pushNotification() {
        val title = binding.titleET.text.toString()
        val message = binding.messageET.text.toString()
        if (title.isNotBlank() && message.isNotBlank()){
            val data = NotificationData(title,message,args.uuid, args.eventType)
            val notification = PushNotification(data,TOPIC)
            viewModel.pushNotification(notification)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}