package com.ibrahimethemsen.firebasenotification.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimethemsen.firebasenotification.databinding.AdapterItemBinding
import com.ibrahimethemsen.firebasenotification.model.PostModel

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>()  {

    private val recyclerList = mutableListOf<PostModel>()
    private lateinit var clickPost : (String) -> Unit

    fun setClickPost(clickPost : (String) -> Unit){
        this.clickPost = clickPost
    }

    fun updateRecyclerList(newList : List<PostModel>){
        recyclerList.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

    class HomeViewHolder(private val binding : AdapterItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(postModel : PostModel,clickPost: (String) -> Unit){
            binding.apply {
                title.text = postModel.pTitle
                root.setOnClickListener {
                    clickPost.invoke(postModel.uuid)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(AdapterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(recyclerList[position],clickPost)

    }

    override fun getItemCount(): Int = recyclerList.size
}