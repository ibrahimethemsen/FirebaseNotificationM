package com.ibrahimethemsen.firebasenotification.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import com.ibrahimethemsen.firebasenotification.databinding.FragmentDetailBinding
import com.ibrahimethemsen.firebasenotification.utility.Constants.DETAIL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.firebaseDetailGetData(args.uuid)
        setListener()
        observe()
    }
    private fun setListener(){
        binding.detailNotification.setOnClickListener { toAddNotification() }
    }
    private fun toAddNotification(){
        val action = DetailFragmentDirections.actionDetailFragmentToAddNotificationFragment(args.uuid,
            DETAIL)
        findNavController().navigate(action)
    }
    private fun observe(){
        viewModel.detail.observe(viewLifecycleOwner){detail ->
            detail?.let {
                binding.titleTV.text   = it.pTitle
                binding.messageTV.text = it.pMessage
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}