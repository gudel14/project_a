package com.example.project_a.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_a.R
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.toast

class RVAdapter(val context: Context, val items:ArrayList<Pompa>)
: RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: Pompa) {
            itemView.update_wp.text = item.pompa
            itemView.update_shift.text = item.shift
            itemView.update_status.text = item.status

            itemView.update_btnedit.setOnClickListener {
                itemView.context.toast("data diedit")
            }
            itemView.update_btnhapus.setOnClickListener {
                itemView.context.toast("Data Dihapus")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}