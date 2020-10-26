package com.example.project_a.towerlamp

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
import com.example.project_a.input.TampilWpActivity
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

class LihatLampActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var listView: ListView? = null
    private var JSON_STRING: String? = null
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_lamp)


        listView = findViewById(R.id.listviewlamp) as ListView

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
                val id = jo.getString(RetrofitClient.TAG_TL_ID)
                val tanggal = jo.getString(RetrofitClient.TAG_TL_tanggal)
                val tl = jo.getString(RetrofitClient.TAG_TL_WP)
                val hm = jo.getString(RetrofitClient.TAG_TL_hm)

                val show = HashMap<String, String?>()
                show[RetrofitClient.TAG_TL_ID] = id
                show[RetrofitClient.TAG_TL_tanggal] = tanggal
                show[RetrofitClient.TAG_TL_WP] = tl
                show[RetrofitClient.TAG_TL_hm] = hm

                list.add(show)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val adapter = SimpleAdapter(
            this@LihatLampActivity, list, R.layout.list_item_lamp,
            arrayOf(RetrofitClient.TAG_TL_tanggal, RetrofitClient.TAG_TL_WP, RetrofitClient.TAG_TL_hm),
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
                    ProgressDialog.show(this@LihatLampActivity , "Mengakses Data", "Tunggu", false, false)
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


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        val intent= Intent(this, LihatLampActivity::class.java)
        val map = p0?.getItemAtPosition(position) as HashMap <*,*>
        val wp_id = map[RetrofitClient.TAG_TL_ID]!!.toString()
        intent.putExtra(RetrofitClient.WP_ID,wp_id)
        startActivity(intent)
    }
}