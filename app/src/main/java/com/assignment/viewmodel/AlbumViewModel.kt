package com.assignment.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.assignment.model.Album
import com.assignment.model.Result
import com.assignment.network.RetrofitApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumViewModel : ViewModel(){
    var  listOfAlbums = MutableLiveData<Result<ArrayList<Album>>>()
    private val  retrofitApiInterface= RetrofitApiInterface.create();

    fun getAlbumsByUser(id : Int) {

        listOfAlbums.value = Result.loading(null)

        retrofitApiInterface.
                getAllAlbums(id).enqueue(object : Callback<ArrayList<Album>>{
           override fun onResponse(call: Call<ArrayList<Album>>?,
                                   response: Response<ArrayList<Album>>?) {
              if(response == null)
                  listOfAlbums.value =
                          Result.error("No response from server")
              else
               listOfAlbums.value = Result.success(response?.body())

           }
           override fun onFailure(call: Call<ArrayList<Album>>?, t: Throwable?) {
               listOfAlbums.value = Result.error(t?.localizedMessage)
           }
       })
    }
}