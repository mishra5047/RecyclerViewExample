package com.abhishek.recyclerviewexample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.recyclerviewexample.ui.activity.ItemClickListener
import com.abhishek.recyclerviewexample.data.UserItem
import com.abhishek.recyclerviewexample.databinding.ItemRecyclerViewBinding

class AdapterWithInterfaceClickListener(
    private val listOfUsers: List<UserItem>,
    private val getItemClickedListener : ItemClickListener
    ) :
    RecyclerView.Adapter<AdapterWithInterfaceClickListener.ViewHolder>() {

        class ViewHolder(private val binding: ItemRecyclerViewBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun setDataForUser(
                userItem: UserItem,
                getItemClickedListener: ItemClickListener
            ) {
                binding.apply {
                    textViewName.text = userItem.name
                    textViewAge.text = userItem.age

                    root.setOnClickListener {
                        getItemClickedListener.getItemClicked(userItem)
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val userItem = listOfUsers[position]
            holder.setDataForUser(userItem, getItemClickedListener)
        }

        override fun getItemCount() = listOfUsers.size
}
