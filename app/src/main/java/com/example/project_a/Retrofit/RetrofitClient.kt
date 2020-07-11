package com.example.project_a.Retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit:Retrofit? = null

    val instance:Retrofit
        get (){
            if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl("http://192.168.43.252/project_a/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit !!
        }
//    192.168.43.252 punya lonjong
    //192.168.43.146 hp samsung
    //192.168.43.130 hp google pixel

    val urlad ="http://192.168.43.252/project_a/input_wp.php"
    val urlgetall = "http://192.168.43.252/project_a/tampilsemuawp.php"
    val urlgetwp ="http://192.168.43.252/project_a/tampilwp.php?id="
    val urldeletewp ="http://192.168.43.252/project_a/hapuswp.php?id="
    val urlupdatewp ="http://192.168.43.252/project_a/updatewp.php?id="
    //yg ke 2 : karna kita select nya per ID di field tabel, jadi kita tambah url nya pakai id (yg di deklarasikan di ApiService)
    const val KEY_WP_ID = "id"
    val KEY_WP_WP = "water_pump"
    val KEY_WP_status = "status"
    val KEY_WP_shift = "shift"
    val KEY_WP_hm = "hm"
    val KEY_WP_tanggal = "tanggal"

    val TAG_JSON_ARRAY = "result"
    val TAG_ID = "id"
    val TAG_WP = "water_pump"
    val TAG_STATUS = "status"
    val TAG_SHIFT = "shift"
    val TAG_HM = "hm"
    val WP_ID = "wp_id"


}