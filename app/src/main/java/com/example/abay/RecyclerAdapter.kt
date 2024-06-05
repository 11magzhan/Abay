import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.abay.database.QaraSoz
import com.example.abay.databinding.CardItemBinding
import com.example.abay.databinding.HeaderItemBinding

class RecyclerAdapter(
    private val onItemClick: (QaraSoz) -> Unit
): ListAdapter<QaraSoz, RecyclerView.ViewHolder>(QaraSozDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = HeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            TYPE_ITEM -> {
                val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val qaraSoz = getItem(position - 1)
            holder.bind(qaraSoz, onItemClick)
        } else if (holder is HeaderViewHolder) {
            holder.bind(itemCount - 1)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    class ItemViewHolder(private val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: QaraSoz,
            onItemClick: (QaraSoz) -> Unit
        ) = with(binding) {
            itemTitle.text = item.title
            //todo image
            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    class HeaderViewHolder(private val binding: HeaderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemCount: Int) {
            binding.headerTitle.text = "Total items: $itemCount"
        }
    }

    class QaraSozDiffCallback : DiffUtil.ItemCallback<QaraSoz>() {
        override fun areItemsTheSame(oldItem: QaraSoz, newItem: QaraSoz): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QaraSoz, newItem: QaraSoz): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }
}