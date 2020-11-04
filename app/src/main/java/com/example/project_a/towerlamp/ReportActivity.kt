package com.example.project_a.towerlamp

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_report.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class ReportActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var listView: ListView? = null
    private var JSON_STRING: String? = null
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)


        listView = findViewById(R.id.listviewlampreport) as ListView

        listView!!.setOnItemClickListener(this)
        get_JSON()

        report_Tl.setOnClickListener{
            startActivity(Intent(this, ReportFinalActivity::class.java))
        }

    }
    private fun tampil() {
        var jsonObject: JSONObject? = null
        val list = ArrayList<HashMap<String, String?>>()

        try {
            jsonObject = JSONObject(JSON_STRING)
            val result = jsonObject.getJSONArray(RetrofitClient.TAG_JSON_ARRAY)

            for (i in 0 until result.length()) {
                val jo = result.getJSONObject(i)
                val id = jo.getString(RetrofitClient.TAG_TL_ID)
                val tanggal = jo.getString(RetrofitClient.TAG_TL_tanggal)
                val tl = jo.getString(RetrofitClient.TAG_TL_lamp)
                val hm = jo.getString(RetrofitClient.TAG_TL_hm)

                val show = HashMap<String, String?>()
                show[RetrofitClient.TAG_TL_ID] = id
                show[RetrofitClient.TAG_TL_tanggal] = tanggal
                show[RetrofitClient.TAG_TL_lamp] = tl
                show[RetrofitClient.TAG_TL_hm] = hm

                list.add(show)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val adapter = SimpleAdapter(
            this@ReportActivity, list, R.layout.list_item_lamp,
            arrayOf(RetrofitClient.TAG_TL_tanggal, RetrofitClient.TAG_TL_lamp, RetrofitClient.TAG_TL_hm),
            intArrayOf(R.id.tanggalTl, R.id.tl, R.id.hmTl)
        )
        listView?.setAdapter(adapter)
    }
    private fun get_JSON(){
        class GET_JSON : AsyncTask<Void, Void, String>(){

            lateinit var loading : ProgressDialog
            override fun onPreExecute(){
                super.onPreExecute()
                loading=
                    ProgressDialog.show(this@ReportActivity , "Mengakses Data", "Tunggu", false, false)
            }
            override fun onPostExecute (s:String){
                super.onPostExecute(s)
                loading.dismiss()
                JSON_STRING = s
                tampil()
            }
            override fun doInBackground (vararg params : Void): String{
                val rh = RequestHandler()
                return rh.sendGetRequest(RetrofitClient.url_tl_tampil_semua,id)
            }
        }
        val gj = GET_JSON()
        gj.execute()
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent= Intent(this, ReportViewActivity::class.java)
        val map = parent?.getItemAtPosition(position) as HashMap <*,*>
        val wp_id = map[RetrofitClient.TAG_TL_ID]!!.toString()
        intent.putExtra(RetrofitClient.WP_ID,wp_id)
        startActivity(intent)
    }
}

