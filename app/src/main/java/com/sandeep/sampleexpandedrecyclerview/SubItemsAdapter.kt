package com.sandeep.sampleexpandedrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.sampleexpandedrecyclerview.data.PlanDetail

class SubItemsAdapter(private val plans: List<PlanDetail>) :
    RecyclerView.Adapter<SubItemsAdapter.ViewHolder>() {

    // holder class to hold reference
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(category: PlanDetail) {
            itemView.findViewById<AppCompatTextView>(R.id.planName).text = category.planName
            itemView.findViewById<AppCompatTextView>(R.id.planAmount).text = category.planAmount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.sub_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(plans[position])
    }

    override fun getItemCount(): Int = plans.size
}