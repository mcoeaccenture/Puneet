package com.assignment.network

import com.assignment.model.Album
import com.assignment.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val BASE_URL= "https://jsonplaceholder.typicode.com/"

interface RetrofitApiInterface{

    @GET("/albums")
    fun getAllAlbums(@Query("userId") id: Int) : Call<ArrayList<Album>>

    @GET("/users")
    fun getAllUsers() : Call<ArrayList<User>>

    companion object  {
        fun create() : RetrofitApiInterface {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(RetrofitApiInterface::class.java)
        }
    }
}