package com.example.project_a.input

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Filter
import android.widget.Filterable
import android.widget.ListView
import android.widget.SimpleAdapter
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

class ReportPompaActivity : AppCompatActivity(), Filterable {

    private var listReport : ListView? = null
    private var JSON_STRING: String? = null
    private var id:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_pompa)


        listReport=findViewById(R.id.listReport) as ListView

        get_JSON()

    }

    private fun tampil () {
        var jsonObject: JSONObject? = null
        val list = ArrayList<HashMap<String, String?>>()

        try {
            jsonObject = JSONObject(JSON_STRING)
            val result = jsonObject.getJSONArray(RetrofitClient.TAG_JSON_ARRAY)

            for (i in 0 until result.length()) {
                val jo = result.getJSONObject(i)
                val id = jo.getString(RetrofitClient.TAG_ID)
                val wp = jo.getString(RetrofitClient.TAG_WP)
                val shift = jo.getString(RetrofitClient.TAG_SHIFT)
                val status = jo.getString(RetrofitClient.TAG_STATUS)
                val rpm = jo.getString(RetrofitClient.TAG_RPM)
                val hm = jo.getString(RetrofitClient.TAG_HM)
                val fuel = jo.getString(RetrofitClient.TAG_FUEL)
                val engine = jo.getString(RetrofitClient.TAG_ENGINE)
                val preasure = jo.getString(RetrofitClient.TAG_PREASURE)
                val debit = jo.getString(RetrofitClient.TAG_DEBIT)
                val elevasi = jo.getString(RetrofitClient.TAG_ELEVASI)
                val tanggal = jo.getString(RetrofitClient.TAG_TANGGAL)

                val show = HashMap<String, String?>()
//                show[RetrofitClient.TAG_ID] = id
                show[RetrofitClient.TAG_WP] = wp
                show[RetrofitClient.TAG_SHIFT] = shift
                show[RetrofitClient.TAG_STATUS] = status
                show[RetrofitClient.TAG_RPM] = rpm
                show[RetrofitClient.TAG_HM] = hm
                show[RetrofitClient.TAG_FUEL] = fuel
                show[RetrofitClient.TAG_ENGINE] = engine
                show[RetrofitClient.TAG_PREASURE] = preasure
                show[RetrofitClient.TAG_DEBIT] = debit
                show[RetrofitClient.TAG_ELEVASI] = elevasi
                show[RetrofitClient.TAG_TANGGAL] = tanggal
                list.add(show)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val adapter= SimpleAdapter(
            this@ReportPompaActivity, list,R.layout.list_report,
            arrayOf (
//                RetrofitClient.TAG_ID,
                RetrofitClient.TAG_WP,
                RetrofitClient.TAG_SHIFT,
                RetrofitClient.TAG_STATUS,
                RetrofitClient.TAG_RPM,
                RetrofitClient.TAG_HM,
                RetrofitClient.TAG_FUEL,
                RetrofitClient.TAG_ENGINE,
                RetrofitClient.TAG_PREASURE,
                RetrofitClient.TAG_DEBIT,
                RetrofitClient.TAG_ELEVASI,
                RetrofitClient.TAG_TANGGAL
            ),
            intArrayOf(
//                R.id.reportId,
                R.id.reportWp,
                R.id.reportShift,
                R.id.reportStatus,
                R.id.reportRpm,
                R.id.reportHm,
                R.id.reportFuel,
                R.id.reportEngine,
                R.id.reportPreasure,
                R.id.reportDebit,
                R.id.reportElevasi,
                R.id.reportTanggal
            )
        )
        listReport?.setAdapter(adapter)
    }
    private fun get_JSON(){
        class GET_JSON : AsyncTask<Void, Void, String>(){

            lateinit var loading : ProgressDialog
            override fun onPreExecute(){
                super.onPreExecute()
                loading=
                    ProgressDialog.show(this@ReportPompaActivity , "Menampilkan Data", "Tunggu", false, false)
            }
            override fun onPostExecute (s:String){
                super.onPostExecute(s)
                loading.dismiss()
                JSON_STRING = s
                tampil()
            }
            override fun doInBackground (vararg params : Void): String{
                val rh = RequestHandler()
                return rh.sendGetRequest(RetrofitClient.urlgetreport,id)
            }
        }
        val gj = GET_JSON()
        gj.execute()
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}
