package com.ibrahimethemsen.firebasenotification.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ibrahimethemsen.firebasenotification.databinding.FragmentHomeBinding
import com.ibrahimethemsen.firebasenotification.home.adapter.HomeAdapter
import com.ibrahimethemsen.firebasenotification.utility.Constants.REMEMBER
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private val homeAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setAdapter()
        observe()
    }
    private fun setListener(){
        binding.notificationBtn.setOnClickListener {toAddNotification()}
    }
    private fun toAddNotification(){
        val action = HomeFragmentDirections.actionHomeFragmentToAddNotificationFragment(eventType = REMEMBER)
        findNavController().navigate(action)
    }
    private fun setAdapter(){
        binding.recyclerView.adapter = homeAdapter
        homeAdapter.setClickPost(::toDetailFragment)
    }
    private fun toDetailFragment(id : String){
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }

    private fun observe(){
        viewModel.postList.observe(viewLifecycleOwner){ list->
            list?.let {
                homeAdapter.updateRecyclerList(it)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}