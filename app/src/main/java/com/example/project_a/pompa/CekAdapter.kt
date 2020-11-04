package com.example.project_a.pompa

//import com.example.project_a.input.InputSementaraActivity
//import com.example.project_a.input.PushActivity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_a.R
import com.example.project_a.p2h.PushCek
import kotlinx.android.synthetic.main.list_cek.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class CekAdapter(val context: Context, val items: ArrayList<Cek>)
    : RecyclerView.Adapter<CekAdapter.ViewHolder>(){
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(items: Cek) {

            itemView.cekTanggal.text = items.tanggal
            itemView.cekPompa.text = items.pompa
            itemView.cekShift.text = items.shift
            itemView.cekNama.text = items.nama
            itemView.cekHm.text = items.hm




            itemView.cek_btnhapus.setOnClickListener {

                itemView.context.database.use {
                    delete(
                        Cek.Tabel_Cek, "${Cek.ID}={id}",
                        "id" to items.id.toString()
                    )

                }

                itemView.context.toast("data dihapus")
                return@setOnClickListener
            }



            itemView.cek_btnpush.setOnClickListener {
                itemView.context.startActivity<PushCek>(
                    "oldcekpompa" to items.pompa,
                    "oldcekshift" to items.shift,
                    "oldceknama" to items.nama,
                    "oldcekhm" to items.hm,
                    "oldcektanggal" to items.tanggal
                )
                return@setOnClickListener
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_cek, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    //--------------------------------------------


}