import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.abay.database.QaraSoz
import com.example.abay.databinding.CardItemBinding

class RecyclerAdapter(
    private val onItemClick: (QaraSoz) -> Unit
): ListAdapter<QaraSoz, RecyclerAdapter.ViewHolder>(QaraSozDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val qaraSoz = getItem(position)
        holder.bind(qaraSoz, onItemClick)
    }

    class ViewHolder(private val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: QaraSoz,
            onItemClick: (QaraSoz) -> Unit
        ) = with(binding) {
            itemTitle.text = item.title
            // todo image
            root.setOnClickListener {
                onItemClick(item)
            }
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
}