package com.example.project_a.pompa

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import com.example.project_a.input.Input_Activity
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RVAdapter(val context: Context, val items: ArrayList<Pompa>)
    : RecyclerView.Adapter<RVAdapter.ViewHolder>(){



    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        fun bindItem(items:Pompa){
            itemView.update_wp.text = items.pompa
            itemView.update_shift.text = items.shift
            itemView.update_status.text = items.status

            itemView.update_btnedit.setOnClickListener {
                itemView.context.startActivity<PompaActivity>(
                    "oldpompa" to items.pompa,
                    "oldshift" to items.shift,
                    "oldstatus" to items.status
                )

            }


            itemView.update_btnhapus.setOnClickListener {
                itemView.context.database.use {
                    delete(Pompa.Tabel_Pompa, "${Pompa.ID}={id}",
                    "id" to items.id.toString())
                }

                itemView.context.toast("data dihapus")
                return@setOnClickListener

            }

            itemView.update_btnpush.setOnClickListener{
                itemView.context.database.use {

                }
            }

        }
    }

    private fun push(){
//        addWp()
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