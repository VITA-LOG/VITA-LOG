    package com.daineey.vita_log.ui.detail

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ImageView
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.daineey.vita_log.R

    class KeywordAdapter(final val supplements: List<keywordRequiredData>, private val onItemClicked: (keywordRequiredData) -> Unit) :
        RecyclerView.Adapter<KeywordAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.supplement_image_src)
            val supplementCompanyTextView: TextView = itemView.findViewById(R.id.supplement_company)
            val supplementName: TextView = itemView.findViewById(R.id.supplement_name)

            init {
                itemView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClicked(supplements[position])
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_keyword, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val supplement = supplements[position]

            val imgResId = holder.imageView.context.resources.getIdentifier(
                supplement.imageSrc,
                "drawable",
                holder.imageView.context.packageName
            )
            if (imgResId != 0) {
                holder.imageView.setImageResource(imgResId)
            }

            holder.supplementCompanyTextView.text = supplement.companyName
            holder.supplementName.text = supplement.supplementName
        }

        override fun getItemCount() = supplements.size
    }
