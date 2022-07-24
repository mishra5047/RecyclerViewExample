package com.abhishek.recyclerviewexample.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.recyclerviewexample.data.UserItem
import com.abhishek.recyclerviewexample.databinding.ActivityMainBinding
import com.abhishek.recyclerviewexample.ui.adapter.AdapterWithInterfaceClickListener
import com.abhishek.recyclerviewexample.ui.adapter.AdapterWithLambdaClickListener
import com.abhishek.recyclerviewexample.ui.adapter.PersonListAdapter
import com.abhishek.recyclerviewexample.util.Util.TAG_FOR_CLICK

class MainActivity : AppCompatActivity(), ItemClickListener {
    // list of demo data for the recyclerview
    lateinit var listOfDemoData: List<UserItem>

    // the layout manager variable used for the recyclerview
    lateinit var linearLayoutManager: LinearLayoutManager

    // this activity uses view binding to render the layout
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDemoListData()

        // method 1 -> click listener inside the adapter
        setRecyclerViewAdapterInsideAdapter()
        // method 2 -> lambda click listener
//        setRecyclerViewAdapterUsingLambdaClickListener()
        // 3-> Interface click listener
//        setRecyclerViewAdapterUsingInterface()
    }

    /**
     * This function sets the recycler view adapter with clickListener
     * handled inside the adapter class itself
     * @param Nothing
     * @return Nothing
     */
    private fun setRecyclerViewAdapterInsideAdapter() {
        val adapter = PersonListAdapter(listOfDemoData)
        binding.userRecyclerView.adapter = adapter
        binding.userRecyclerView.layoutManager = linearLayoutManager
    }

    /**
     * This function sets the recycler view adapter with clickListener which is implemented
     * using Lambda click listener
     * @param Nothing
     * @return Nothing
     */
    private fun setRecyclerViewAdapterUsingLambdaClickListener() {
        val adapter = AdapterWithLambdaClickListener(listOfDemoData) { clickedUserItem: UserItem ->
            Log.d(
                TAG_FOR_CLICK,
                "Clicked Using lambda click listener -> User name " +
                        clickedUserItem.name + " age " + clickedUserItem.age
            )
        }
        binding.userRecyclerView.adapter = adapter
        binding.userRecyclerView.layoutManager = linearLayoutManager
    }

    /**
     * This function sets the recycler view adapter in which clickListener is
     * implemented with the help of interface
     * The interface is defined at the end of this file
     * @param Nothing
     * @return Nothing
     */
    private fun setRecyclerViewAdapterUsingInterface() {
        val adapter = AdapterWithInterfaceClickListener(listOfDemoData, this)
        binding.userRecyclerView.adapter = adapter
        binding.userRecyclerView.layoutManager = linearLayoutManager
    }

    /**
     * This function sets the global list variable with demo data
     * and the linearLayoutManager
     * @param Nothing
     * @return Nothing
     */
    private fun setDemoListData() {
        listOfDemoData = listOf(
            UserItem("user 1", "age 1"),
            UserItem("user 2", "age 2"),
            UserItem("user 3", "age 3"),
            UserItem("user 4", "age 4"),
        )
        linearLayoutManager = LinearLayoutManager(this)
    }

    /**
     * This is the implementation for the
     * click method in the interface
     *
     * Handle the action that needs to be done when item is clicked
     */
    override fun getItemClicked(userItem: UserItem) {
        // replace this log by your use case
        Log.d(
            TAG_FOR_CLICK,
            "Clicked using interface -> user name " + userItem.name + " age " + userItem.age
        )
    }
}

/**
 * This interface contains the function which is called when
 * an item is clicked in the recyclerview
 * @since Only works in method 3
 */
interface ItemClickListener {
    fun getItemClicked(userItem: UserItem)
}
