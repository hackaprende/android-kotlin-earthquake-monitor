package com.hackaprende.earthquakemonitor.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hackaprende.earthquakemonitor.Earthquake
import com.hackaprende.earthquakemonitor.R
import com.hackaprende.earthquakemonitor.databinding.EqListItemBinding

class EqAdapter(val context: Context) : ListAdapter<Earthquake, EqAdapter.ViewHolder>(
    DiffCallback
) {

    companion object DiffCallback : DiffUtil.ItemCallback<Earthquake>() {
        override fun areItemsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private lateinit var onItemClickListener: ((earthquake: Earthquake) -> Unit)

    fun setOnItemClickListener(onItemClickListener: (earthquake: Earthquake) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EqListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val earthquake = getItem(position)
        holder.bind(earthquake)
    }

    inner class ViewHolder(private val binding: EqListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(earthquake: Earthquake) {
            binding.eqListItemMagnitude.text = context.getString(R.string.magnitude_format,
                earthquake.magnitude)
            binding.eqListItemTitle.text = earthquake.place

            binding.root.setOnClickListener {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(earthquake)
                }
            }
        }
    }
}