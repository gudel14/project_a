package com.example.project_a.Retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit:Retrofit? = null

    val instance:Retrofit
        get (){
            if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl("https://gudelabok.000webhostapp.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit !!
        }
    //192.168.43.252 punya lonjong
    //192.168.43.146 hp samsung
    //192.168.43.130 hp google pixel

//    val urlad ="https://gudelabok.000webhostapp.com/input_wp.php"
//    val urlgetall = "https://gudelabok.000webhostapp.com/tampilsemuawp.php"
//    val urlgetreport = "https://gudelabok.000webhostapp.com/tampilreport.php"
//    val urlgetwp ="https://gudelabok.000webhostapp.com/tampilwp.php?id="
//    val urldeletewp ="https://gudelabok.000webhostapp.com/hapuswp.php?id="
//    val urlupdatewp ="https://gudelabok.000webhostapp.com/updatewp.php?id="

    val urlad ="http://192.168.43.130/project_a/input_wp.php"
    val urlgetall = "http://192.168.43.130/project_a/tampilsemuawp.php"
    val urlgetreport = "http://192.168.43.130/project_a/tampilreport.php"
    val urlgetwp ="http://192.168.43.130/project_a/tampilwp.php?id="
    val urldeletewp ="http://192.168.43.130/project_a/hapuswp.php?id="
    val urlupdatewp ="http://192.168.43.130/project_a/updatewp.php?id="


    //yg ke 2 : karna kita select nya per ID di field tabel, jadi kita tambah url nya pakai id (yg di deklarasikan di ApiService)
    const val KEY_WP_ID = "id"
    val KEY_WP_WP = "water_pump"
    val KEY_WP_status = "status"
    val KEY_WP_shift = "shift"
    val KEY_WP_rpm = "rpm"
    val KEY_WP_hm = "hm"
    val KEY_WP_fuel = "fuel_rate"
    val KEY_WP_engine = "engine_load"
    val KEY_WP_preasure = "preasure"
    val KEY_WP_debit = "debit"
    val KEY_WP_elevasi = "elevasi"
    val KEY_WP_tanggal = "tanggal"

    val TAG_JSON_ARRAY = "result"
    val TAG_ID = "id"
    val TAG_WP = "water_pump"
    val TAG_STATUS = "status"
    val TAG_SHIFT = "shift"
    val TAG_RPM = "rpm"
    val TAG_HM = "hm"
    val TAG_FUEL = "fuel_rate"
    val TAG_ENGINE = "engine_load"
    val TAG_PREASURE = "preasure"
    val TAG_DEBIT = "debit"
    val TAG_ELEVASI = "elevasi"
    val TAG_TANGGAL = "tanggal"
    val WP_ID = "wp_id"


}