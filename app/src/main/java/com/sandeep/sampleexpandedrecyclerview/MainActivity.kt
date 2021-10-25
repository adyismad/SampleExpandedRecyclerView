package com.sandeep.sampleexpandedrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.sampleexpandedrecyclerview.data.DataProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemsGroupList = DataProvider.getRandomItemsGroupList(5)
        val adapters: List<ItemsExpandableAdapter> = itemsGroupList.map { itemsGroup ->
            ItemsExpandableAdapter(itemsGroup)
        }
        val concatAdapterConfig = ConcatAdapter.Config.Builder()
            .setIsolateViewTypes(false)
            .build()
        val concatAdapter = ConcatAdapter(concatAdapterConfig, adapters)

        val rvItems = findViewById<RecyclerView>(R.id.rvItems)

        with(rvItems) {
            layoutManager = LinearLayoutManager(context)
            itemAnimator =
                ExpandableItemAnimator()
            adapter = concatAdapter
            isNestedScrollingEnabled = false
        }

    }
}