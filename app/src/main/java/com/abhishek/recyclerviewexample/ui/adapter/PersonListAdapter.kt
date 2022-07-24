package com.abhishek.recyclerviewexample.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.recyclerviewexample.data.UserItem
import com.abhishek.recyclerviewexample.databinding.ItemRecyclerViewBinding
import com.abhishek.recyclerviewexample.util.Util.TAG_FOR_CLICK

class PersonListAdapter(
    private val listOfUsers: List<UserItem>,
) :
    RecyclerView.Adapter<PersonListAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataForUser(
            userItem: UserItem,
        ) {
            binding.apply {
                textViewName.text = userItem.name
                textViewAge.text = userItem.age

                root.setOnClickListener {
                    Log.d(
                        TAG_FOR_CLICK,
                        "Clicked Inside Adapter User name " + userItem.name + " age " + userItem.age
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userItem = listOfUsers[position]
        holder.setDataForUser(userItem)
    }

    override fun getItemCount() = listOfUsers.size
}