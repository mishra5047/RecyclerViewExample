package com.abhishek.recyclerviewexample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.recyclerviewexample.data.UserItem
import com.abhishek.recyclerviewexample.databinding.ItemRecyclerViewBinding

class AdapterWithLambdaClickListener(
    private val listOfUsers: List<UserItem>,
    private val itemClickListener: (UserItem) -> Unit,
    ) :
    RecyclerView.Adapter<AdapterWithLambdaClickListener.ViewHolder>() {

        class ViewHolder(private val binding: ItemRecyclerViewBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun setDataForUser(
                userItem: UserItem,
                position: Int,
                itemClickListener: (UserItem) -> Unit
            ) {
                binding.apply {
                    textViewName.text = userItem.name
                    textViewAge.text = userItem.age

                    root.setOnClickListener {
                        itemClickListener(userItem)
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
            holder.setDataForUser(userItem, position, itemClickListener)
        }

        override fun getItemCount() = listOfUsers.size
}