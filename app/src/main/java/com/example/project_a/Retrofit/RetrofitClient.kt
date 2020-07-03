package com.example.project_a.Retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit:Retrofit? = null

    val instance:Retrofit
        get (){
            if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl("http://192.168.43.130/project_a/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit !!
        }
    val urlad ="http://192.168.43.130/project_a/input_wp.php"
    val urlgetall = "http://192.168.43.130/project_a/tampilsemuawp.php"
    val urlgetwp ="http://192.168.43.130/project_a/tampilwp.php"
    val urldeletewp ="http://192.168.43.130/project_a/hapuswp.php"
    val KEY_WP_ID = "id"
    val KEY_WP_WP = "water_pump"
    val KEY_WP_status = "status"
    val KEY_WP_shift = "shift"

    val TAG_JSON_ARRAY = "result"
    val TAG_ID = "id"
    val TAG_WP = "water_pump"
    val TAG_STATUS = "status"
    val TAG_SHIFT = "shift"
    val WP_ID = "wp_id"


}