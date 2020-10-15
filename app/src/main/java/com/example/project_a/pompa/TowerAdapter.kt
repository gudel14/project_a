package com.example.project_a.pompa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_a.R
import com.example.project_a.input.PushActivity
import com.example.project_a.towerlamp.PushLamp
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.list_lamp.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import com.example.project_a.pompa.TowerAdapter.ViewHolder as ViewHolder1

class TowerAdapter(val context: Context, val item: ArrayList<Tower>)
    : RecyclerView.Adapter<TowerAdapter.ViewHolder>() {
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: Tower) {

            itemView.tlupdate_tl.text = item.lamp
            itemView.tlupdate_shift.text = item.shift
            itemView.tlupdate_status.text = item.status
            itemView.tlupdate_tanggal.text = item.tanggal
            itemView.tlupdate_hm.text = item.hm
            itemView.tlupdate_fuel.text = item.fuel

            itemView.tlupdate_btnedit.setOnClickListener {
                itemView.context.startActivity<TowerActivity>(
                    "oldlamp" to item.lamp,
                    "oldshift" to item.shift,
                    "oldstatus" to item.status,
                    "oldhm" to item.hm,
                    "oldfuel" to item.fuel,
                    "oldtanggal" to item.tanggal
                )

            }
            itemView.tlupdate_btnhapus.setOnClickListener {

                itemView.context.database.use {
                    delete(
                        Tower.Tabel_Tower, "${Tower.ID}={id}",
                        "id" to item.id.toString()
                    )

                }

                itemView.context.toast("Data Dihapus")
                return@setOnClickListener
            }

            itemView.tlupdate_btnpush.setOnClickListener {
                itemView.context.startActivity<PushLamp>(
                    "oldlamp" to item.lamp,
                    "oldshift" to item.shift,
                    "oldstatus" to item.status,
                    "oldhm" to item.hm,
                    "oldfuel" to item.fuel,
                    "oldtanggal" to item.tanggal
                )
                return@setOnClickListener
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_lamp, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, positio: Int) {
        holder.bindItem(item[positio])
    }
}