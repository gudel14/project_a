package com.example.project_a.Retrofit

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface API {
    // API register
    @POST("register.php")
    @FormUrlEncoded
    fun registeruser (@Field("nrp") nrp:String,
                      @Field("nama") nama:String,
                      @Field("pass") pass:String):Observable<String>


    // API login
    @POST ("login.php")
    @FormUrlEncoded
    fun loginUser (@Field("nrp") nrp:String,
                   @Field("pass") pass:String): Call<APIRespon>
}