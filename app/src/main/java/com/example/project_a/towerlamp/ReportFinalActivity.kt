package com.example.project_a.towerlamp

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class ReportFinalActivity : AppCompatActivity() {

    private var listReport: ListView? = null
    private var JSON_STRING: String? = null
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_final)

        listReport = findViewById(R.id.listReportFinal) as ListView

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
                val wp = jo.getString(RetrofitClient.TAG_TL_lamp)
                val shift = jo.getString(RetrofitClient.TAG_TL_shift)
                val status = jo.getString(RetrofitClient.TAG_TL_status)
                val hm = jo.getString(RetrofitClient.TAG_TL_hm)
                val fuel = jo.getString(RetrofitClient.TAG_TL_fuel)
//                val elevasi = jo.getString(RetrofitClient.TAG_ELEVASI)
                val tanggal = jo.getString(RetrofitClient.TAG_TL_tanggal)
//                val keterangan = jo.getString(RetrofitClient.TAG_KETERANGAN)

                val show = HashMap<String, String?>()
                show[RetrofitClient.TAG_TL_ID] = id
                show[RetrofitClient.TAG_TL_lamp] = wp
                show[RetrofitClient.TAG_TL_shift] = shift
                show[RetrofitClient.TAG_TL_status] = status
                show[RetrofitClient.TAG_TL_hm] = hm
                show[RetrofitClient.TAG_TL_fuel] = fuel
//                show[RetrofitClient.TAG_ELEVASI] = elevasi
                show[RetrofitClient.TAG_TL_tanggal] = tanggal
//                show[RetrofitClient.TAG_KETERANGAN] = keterangan
                list.add(show)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val adapter = SimpleAdapter(
            this@ReportFinalActivity, list, R.layout.list_report_final,
            arrayOf(
//                RetrofitClient.TAG_ID,
                RetrofitClient.TAG_TL_lamp,
                RetrofitClient.TAG_TL_shift,
                RetrofitClient.TAG_TL_status,
                RetrofitClient.TAG_TL_hm,
                RetrofitClient.TAG_TL_fuel,
//                RetrofitClient.TAG_ELEVASI,
                RetrofitClient.TAG_TL_tanggal
//                RetrofitClient.TAG_KETERANGAN
            ),
            intArrayOf(
//                R.id.reportId,
                R.id.reportTlLamp,
                R.id.reportTlShift,
                R.id.reportTlStatus,
                R.id.reportTlHm,
                R.id.reportTlFuel,
//                R.id.reportTlLokasi,
                R.id.reportTlTanggal
//                R.id.reportTlKeterangan
            )
        )
        listReport?.setAdapter(adapter)
    }

    private fun get_JSON() {
        class GET_JSON : AsyncTask<Void, Void, String>() {

            lateinit var loading: ProgressDialog
            override fun onPreExecute() {
                super.onPreExecute()
                loading =
                    ProgressDialog.show(
                        this@ReportFinalActivity, "Menampilkan Data", "Tunggu", false, false
                    )
            }

            override fun onPostExecute(s: String) {
                super.onPostExecute(s)
                loading.dismiss()
                JSON_STRING = s
                tampil()
            }

            override fun doInBackground(vararg params: Void): String {
                val rh = RequestHandler()
                return rh.sendGetRequest(RetrofitClient.url_tl_report, id)
            }
        }

        val gj = GET_JSON()
        gj.execute()
    }

}