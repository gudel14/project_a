package com.example.project_a.towerlamp

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import org.json.JSONException
import org.json.JSONObject

class ReportViewActivity : AppCompatActivity() {

    private var lamp : EditText?=null
    private var shift : EditText?=null
    private var status : EditText?=null
    private var hm : EditText?=null
    private var fuel : EditText?=null
    private var tanggal : TextView?=null
    private var keterangan : TextView?=null

    private var id:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_view)

        val intent=intent

        id=intent.getStringExtra(RetrofitClient.TL_ID)

//        id_id=findViewById(R.id.tampil_id)as EditText
        lamp=findViewById(R.id.textLampReport)as EditText
        shift=findViewById(R.id.textShiftReport)as EditText
        status=findViewById(R.id.textStatusReport)as EditText
        hm=findViewById(R.id.textHmReport)as EditText
        fuel=findViewById(R.id.textFuelReport)as EditText
        tanggal=findViewById(R.id.reportTextTanggal) as TextView
//        keterangan=findViewById(R.id.ReportTextKeterangan)as TextView

        getTampilLamp()

    }

    fun getTampilLamp (){

        class getTampil: AsyncTask<Void, Void, String>(){
            lateinit var loading: ProgressDialog
            override fun onPreExecute() {
                super.onPreExecute()
                loading=
                    ProgressDialog.show(this@ReportViewActivity, "Memuat Data", "Tunggu", false,false)
            }

            override fun onPostExecute(re: String) {
                super.onPostExecute(re)
                loading.dismiss()
                showwp(re)
////               Toast.makeText(this@TampilWpActivity, Toast.LENGTH_SHORT).show()
            }

            override fun doInBackground(vararg params: Void): String {
                val rh = RequestHandler()
                return rh.sendGetRequestParam(RetrofitClient.urltampiltl,id)
            }
        }
        val get = getTampil()
        get.execute()

    }
    private fun showwp (json:String){
        try {
//            jsonObject = JSONObject(JSON_STRING)
            val jsonObj= JSONObject(json)
            val result = jsonObj.getJSONArray(RetrofitClient.TAG_JSON_ARRAY)
            val jo = result.getJSONObject(0)
////            val idd = jo.getString(RetrofitClient.TAG_ID)
            val lampp = jo.getString(RetrofitClient.TAG_TL_lamp)
            val shiftt = jo.getString(RetrofitClient.TAG_TL_shift)
            val statuss = jo.getString(RetrofitClient.TAG_TL_status)
            val hmm = jo.getString(RetrofitClient.TAG_TL_hm)
            val fuell = jo.getString(RetrofitClient.TAG_TL_fuel)
            val tanggall = jo.getString(RetrofitClient.TAG_TL_tanggal)
//            val keterangann = jo.getString(RetrofitClient.TAG_KETERANGAN)

////           id_id?.setText(id)
            lamp?.setText(lampp)
            shift?.setText(shiftt)
            status?.setText(statuss)
            hm?.setText(hmm)
            fuel?.setText(fuell)
            tanggal?.setText(tanggall)
//            keterangan?.setText(keterangann)

        }catch (e : JSONException){
            e.printStackTrace()
        }
    }
}