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
    val KEY_WP_ID = "id"
    val KEY_WP_WP = "water_pump"
    val KEY_WP_status = "status"
    val KEY_WP_shift = "shift"


}