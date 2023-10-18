package com.daineey.vita_log.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daineey.vita_log.database.Supplement
import com.daineey.vita_log.databinding.SearchResultItemBinding

class SearchAdapter(
    private var supplements: List<Supplement>,
    private val onItemClicked: (Supplement) -> Unit
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(val binding: SearchResultItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SearchResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val supplement = supplements[position]
        holder.binding.apply {

            val imgResId = supplementImageSrc.context.resources.getIdentifier(
                supplement.imageName, // imageSrc 필드는 Supplement 모델에 있어야 합니다.
                "drawable",
                supplementImageSrc.context.packageName
            )
            if (imgResId != 0) {
                supplementImageSrc.setImageResource(imgResId)
            }

            resultTitleTextView.text = supplement.name
            resultDescriptionTextView.text = supplement.company
        }

        holder.itemView.setOnClickListener {
            onItemClicked(supplements[position])
        }
    }

    override fun getItemCount() = supplements.size

    fun updateData(newSupplements: List<Supplement>) {
        this.supplements = newSupplements
        notifyDataSetChanged()
    }
}
