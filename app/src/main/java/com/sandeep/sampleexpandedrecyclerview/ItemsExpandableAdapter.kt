package com.sandeep.sampleexpandedrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.sampleexpandedrecyclerview.data.ItemsGroup
import com.sandeep.sampleexpandedrecyclerview.data.SubItem
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class ItemsExpandableAdapter(private val itemsGroup: ItemsGroup) :
    RecyclerView.Adapter<ItemsExpandableAdapter.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ITEM = 1
        private const val VIEW_TYPE_HEADER = 2

        private const val IC_EXPANDED_ROTATION_DEG = 0F
        private const val IC_COLLAPSED_ROTATION_DEG = 180F
    }

    var isExpanded: Boolean by Delegates.observable(true) { _: KProperty<*>, _: Boolean, newExpandedValue: Boolean ->
        if (newExpandedValue) {
            notifyItemRangeInserted(1, itemsGroup.items.size)
            //To update the header expand icon
            notifyItemChanged(0)
        } else {
            notifyItemRangeRemoved(1, itemsGroup.items.size)
            //To update the header expand icon
            notifyItemChanged(0)
        }
    }

    private val onHeaderClickListener = View.OnClickListener {
        isExpanded = !isExpanded
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int = if (isExpanded) itemsGroup.items.size + 1 else 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> ViewHolder.HeaderVH(
                inflater.inflate(
                    R.layout.item_header,
                    parent,
                    false
                )
            )
            else -> ViewHolder.ItemVH(
                inflater.inflate(
                    R.layout.item_content,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder.ItemVH -> holder.bind(itemsGroup.items[position - 1])
            is ViewHolder.HeaderVH -> {
                holder.bind(itemsGroup.title, isExpanded, onHeaderClickListener)
            }
        }
    }

    sealed class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        class ItemVH(itemView: View) : ViewHolder(itemView) {

            fun bind(content: SubItem) {
                itemView.apply {
                    itemView.findViewById<TextView>(R.id.tvItemContent).text = content.header
                    val subItemRv = itemView.findViewById<RecyclerView>(R.id.subItemsRv)
                    val subItemsAdapter = SubItemsAdapter(content.subList)
                    with(subItemRv) {
                        layoutManager = LinearLayoutManager(context)
                        adapter = subItemsAdapter
                    }
                }
            }
        }

        class HeaderVH(itemView: View) : ViewHolder(itemView) {
            private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            internal val icExpand = itemView.findViewById<ImageView>(R.id.icExpand)

            fun bind(content: String, expanded: Boolean, onClickListener: View.OnClickListener) {
                tvTitle.text = content
                icExpand.rotation =
                    if (expanded) IC_EXPANDED_ROTATION_DEG else IC_COLLAPSED_ROTATION_DEG
                itemView.setOnClickListener(onClickListener)
            }
        }
    }
}


