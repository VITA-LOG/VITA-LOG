package com.daineey.vita_log.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.daineey.vita_log.R
import com.daineey.vita_log.database.Supplement

class SupplementAdapter(
    private val context: Context,
    private val supplements: List<Supplement>
) : RecyclerView.Adapter<SupplementAdapter.SupplementViewHolder>() {

    class SupplementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val supplementImage: ImageView = view.findViewById(R.id.supplement_image)
        val supplementRank: TextView = view.findViewById(R.id.supplement_rank)
        val supplementCompany: TextView = view.findViewById(R.id.supplement_company)
        val supplementName: TextView = view.findViewById(R.id.supplement_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_supplement, parent, false)
        return SupplementViewHolder(view)
    }

    override fun onBindViewHolder(holder: SupplementViewHolder, position: Int) {
        val supplement = supplements[position]
        holder.supplementRank.text = "${position + 1}위"
        holder.supplementCompany.text = supplement.company
        holder.supplementName.text = supplement.name
        val imageId = context.resources.getIdentifier(supplement.imageName, "drawable", context.packageName)
        holder.supplementImage.setImageResource(if (imageId != 0) imageId else R.drawable.sample_eng)

        holder.itemView.setOnClickListener {
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val supplementDetailFragment = SupplementDetail()
            val args = Bundle()
            args.putString("supplementName", supplement.name)
            supplementDetailFragment.arguments = args

            fragmentTransaction.replace(R.id.fragmentContainer, supplementDetailFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun getItemCount(): Int {
        return supplements.size
    }
}
