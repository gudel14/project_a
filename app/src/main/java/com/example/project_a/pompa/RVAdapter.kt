package com.example.project_a.pompa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_a.R
//import com.example.project_a.input.InputSementaraActivity
import com.example.project_a.input.PushActivity
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RVAdapter(val context: Context, val items: ArrayList<Pompa>)
    : RecyclerView.Adapter<RVAdapter.ViewHolder>(){


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(items: Pompa) {


            itemView.update_wp.text = items.pompa
            itemView.update_shift.text = items.shift
            itemView.update_status.text = items.status
            itemView.update_rpm.text = items.rpm
            itemView.update_hm.text = items.hm
            itemView.update_fuel.text = items.fuel
            itemView.update_engine.text = items.engine
            itemView.update_preasure.text = items.preasure
            itemView.update_debit.text = items.debit
            itemView.update_elevasi.text = items.elevasi
            itemView.update_tanggal.text = items.tanggal

            itemView.update_btnedit.setOnClickListener {
                itemView.context.startActivity<PompaActivity>(
                    "oldpompa" to items.pompa,
                    "oldshift" to items.shift,
                    "oldstatus" to items.status,
                    "oldrpm" to items.rpm,
                    "oldhm" to items.hm,
                    "oldfuel" to items.fuel,
                    "oldengine" to items.engine,
                    "oldpreasure" to items.preasure,
                    "olddebit" to items.debit,
                    "oldelevasi" to items.elevasi,
                    "oldtanggal" to items.tanggal
                )

            }

            itemView.update_btnhapus.setOnClickListener {


                itemView.context.database.use {
                    delete(
                        Pompa.Tabel_Pompa, "${Pompa.ID}={id}",
                        "id" to items.id.toString()
                    )

                }

                itemView.context.toast("data dihapus")
                return@setOnClickListener
            }



            itemView.update_btnpush.setOnClickListener {
                itemView.context.startActivity<PushActivity>(
                    "oldpompa" to items.pompa,
                    "oldshift" to items.shift,
                    "oldstatus" to items.status,
                    "oldrpm" to items.rpm,
                    "oldhm" to items.hm,
                    "oldfuel" to items.fuel,
                    "oldengine" to items.engine,
                    "oldpreasure" to items.preasure,
                    "olddebit" to items.debit,
                    "oldelevasi" to items.elevasi,
                    "oldtanggal" to items.tanggal
                )
                return@setOnClickListener
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
    //--------------------------------------------

}