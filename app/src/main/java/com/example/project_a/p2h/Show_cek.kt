package com.example.project_a.p2h

import android.app.ProgressDialog
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
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class Show_cek : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var listView: ListView? = null
    private var JSON_STRING: String? = null
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_cek)

        listView = findViewById(R.id.listviewcek) as ListView

//        listView!!.setOnItemClickListener(this)
        get_JSON()
    }


    private fun tampill() {
        var jsonObject: JSONObject? = null
        val list = ArrayList<HashMap<String, String?>>()

        try {
            jsonObject = JSONObject(JSON_STRING)
            val result = jsonObject.getJSONArray(RetrofitClient.TAG_JSON_ARRAY)

            for (i in 0 until result.length()) {
                val jo = result.getJSONObject(i)
                val id = jo.getString(RetrofitClient.TAG_CEK_ID)
                val tanggal = jo.getString(RetrofitClient.TAG_CEK_tanggal)
                val wp = jo.getString(RetrofitClient.TAG_CEK_pompa)
                val hm = jo.getString(RetrofitClient.TAG_CEK_hm)

                val show = HashMap<String, String?>()
                show[RetrofitClient.TAG_CEK_ID] = id
                show[RetrofitClient.TAG_CEK_tanggal] = tanggal
                show[RetrofitClient.TAG_CEK_pompa] = wp
                show[RetrofitClient.TAG_CEK_hm] = hm

                list.add(show)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val adapter = SimpleAdapter(
            this@Show_cek, list, R.layout.list_item_cek,
            arrayOf(
                RetrofitClient.TAG_CEK_tanggal,
                RetrofitClient.TAG_CEK_pompa,
                RetrofitClient.TAG_CEK_hm
            ),
            intArrayOf(R.id.tanggalcek, R.id.pompacek, R.id.hmcek)
        )
        listView?.setAdapter(adapter)
    }

    private fun get_JSON() {
        class GET_JSON : AsyncTask<Void, Void, String>(){

            lateinit var loading : ProgressDialog
            override fun onPreExecute(){
                super.onPreExecute()
                loading=
                    ProgressDialog.show(this@Show_cek, "Mengakses Database", "Sedang Mengakses", false, false)
            }
            override fun onPostExecute (s:String){
                super.onPostExecute(s)
                loading.dismiss()
                JSON_STRING = s
                tampill()
            }
            override fun doInBackground (vararg params : Void): String{
                val rh = RequestHandler()
                return rh.sendGetRequest(RetrofitClient.urlgetallcek,id)
            }
        }
        val gj = GET_JSON()
        gj.execute()
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        val intent= Intent(this, HapusReportActivity::class.java)
//        val map = parent?.getItemAtPosition(position) as HashMap <*,*>
//        val wp_id = map[RetrofitClient.TAG_CEK_ID]!!.toString()
//        intent.putExtra(RetrofitClient.WP_ID,wp_id)
//        startActivity(intent)
    }


    }

