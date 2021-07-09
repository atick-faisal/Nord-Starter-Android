package ai.andromeda.nordstarter.ui.home.adapter

import ai.andromeda.nordstarter.databinding.ItemDummyBinding
import ai.andromeda.nordstarter.storage.room.entity.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemViewHolder(
    private val binding: ItemDummyBinding,
    private inline val onItemClick: (Long) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup, onItemClick: (Long) -> Unit): ItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemDummyBinding.inflate(layoutInflater)
            return ItemViewHolder(binding, onItemClick)
        }
    }

    fun bind(item: Item) {
        binding.itemName.text = item.name
        binding.itemDescription.text = item.description
        Glide.with(binding.root)
            .load(item.logo)
            .into(binding.itemLogo)
        binding.root.setOnClickListener { onItemClick(item.id) }
    }
}