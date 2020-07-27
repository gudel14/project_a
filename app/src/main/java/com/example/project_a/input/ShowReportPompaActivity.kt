package com.example.project_a.input

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

class ShowReportPompaActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var listView: ListView? = null
    private var JSON_STRING: String? = null
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_report_pompa)


        listView = findViewById(R.id.listviewshow) as ListView

        listView!!.setOnItemClickListener(this)
        get_JSON()
    }

    private fun tampil() {
        var jsonObject: JSONObject? = null
        val list = ArrayList<HashMap<String, String?>>()

        try {
            jsonObject = JSONObject(JSON_STRING)
            val result = jsonObject.getJSONArray(RetrofitClient.TAG_JSON_ARRAY)

            for (i in 0 until result.length()) {
                val jo = result.getJSONObject(i)
                val id = jo.getString(RetrofitClient.TAG_ID)
                val tanggal = jo.getString(RetrofitClient.TAG_TANGGAL)
                val wp = jo.getString(RetrofitClient.TAG_WP)
                val hm = jo.getString(RetrofitClient.TAG_HM)

                val show = HashMap<String, String?>()
                show[RetrofitClient.TAG_ID] = id
                show[RetrofitClient.TAG_TANGGAL] = tanggal
                show[RetrofitClient.TAG_WP] = wp
                show[RetrofitClient.TAG_HM] = hm

                list.add(show)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val adapter= SimpleAdapter(
            this@ShowReportPompaActivity, list,R.layout.list_item,
            arrayOf (RetrofitClient.TAG_TANGGAL, RetrofitClient.TAG_WP, RetrofitClient.TAG_HM),
            intArrayOf(R.id.tanggal,R.id.wp, R.id.hm)
        )
        listView?.setAdapter(adapter)
    }
    private fun get_JSON(){
        class GET_JSON : AsyncTask<Void, Void, String>(){

            lateinit var loading : ProgressDialog
            override fun onPreExecute(){
                super.onPreExecute()
                loading=
                    ProgressDialog.show(this@ShowReportPompaActivity , "Mengakses Database", "Sedang Mengakses", false, false)
            }
            override fun onPostExecute (s:String){
                super.onPostExecute(s)
                loading.dismiss()
                JSON_STRING = s
                tampil()
            }
            override fun doInBackground (vararg params : Void): String{
                val rh = RequestHandler()
                return rh.sendGetRequest(RetrofitClient.urlgetall,id)
            }
        }
        val gj = GET_JSON()
        gj.execute()
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent= Intent(this, HapusReportActivity::class.java)
        val map = parent?.getItemAtPosition(position) as HashMap <*,*>
        val wp_id = map[RetrofitClient.TAG_ID]!!.toString()
        intent.putExtra(RetrofitClient.WP_ID,wp_id)
        startActivity(intent)
    }
}