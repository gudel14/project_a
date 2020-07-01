package com.example.project_a.input

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

class TampilSemuaWpActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var listView : ListView? = null
    private var JSON_STRING: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampil_semua_wp)

        listView=findViewById(R.id.listview) as ListView

        listView!!.setOnItemClickListener(this)
        get_JSON()


    }

    private fun tampil (){
        var jsonObject:JSONObject? = null
        val list = ArrayList<HashMap<String,String?>>()

        try {
            jsonObject = JSONObject(JSON_STRING)
            val result = jsonObject.getJSONArray(RetrofitClient.TAG_JSON_ARRAY)

            for (i in 0 until result.length()){
                val jo = result.getJSONObject(i)
                val id = jo.getString(RetrofitClient.TAG_ID)
                val wp = jo.getString(RetrofitClient.TAG_WP)
                val show = HashMap<String,String?>()
                show [RetrofitClient.TAG_ID]=id
                show[RetrofitClient.TAG_WP]= wp

                list.add(show)
            }
        }catch (e : JSONException){
            e.printStackTrace()
    }
        val adapter= SimpleAdapter(
            this@TampilSemuaWpActivity, list,R.layout.list_item,
            arrayOf (RetrofitClient.TAG_ID, RetrofitClient.TAG_WP),
            intArrayOf(R.id.id,R.id.wp)
        )
        listView?.setAdapter(adapter)
    }

    private fun get_JSON(){
        class GET_JSON : AsyncTask<Void, Void, String>(){

            lateinit var loading : ProgressDialog
            override fun onPreExecute(){
                super.onPreExecute()
                loading=
                    ProgressDialog.show(this@TampilSemuaWpActivity , "mengambil data", "tunggu", false, false)
            }
            override fun onPostExecute (s:String){
                super.onPostExecute(s)
                loading.dismiss()
                JSON_STRING = s
                tampil()
            }
            override fun doInBackground (vararg v : Void): String{

                val rh = RequestHandler()
                return rh.sendGetRequest(RetrofitClient.urlgetall)
            }
        }
        val gj = GET_JSON()
        gj.execute()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent=Intent(this, TampilWpActivity::class.java)
        val map = parent?.getItemAtPosition(position) as HashMap <*,*>
        val wp_id = map[RetrofitClient.TAG_ID]!!.toString()
        intent.putExtra(RetrofitClient.WP_ID,wp_id)
        startActivity(intent)
    }

}