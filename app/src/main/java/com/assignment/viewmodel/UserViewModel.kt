package com.assignment.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.assignment.model.Album
import com.assignment.model.Result
import com.assignment.model.User
import com.assignment.network.RetrofitApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    var listOfUsers = MutableLiveData<Result<ArrayList<User>>>()
    private val retrofitApiInterface = RetrofitApiInterface.create();

    fun getAllUsers() {
        listOfUsers.value = Result.loading(null)

        retrofitApiInterface.getAllUsers().enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(call: Call<ArrayList<User>>?,
                                    response: Response<ArrayList<User>>?) {
                if (response == null)
                    listOfUsers.value =
                            Result.error("No response from server")
                else
                    listOfUsers.value = Result.success(response?.body())
            }

            override fun onFailure(call: Call<ArrayList<User>>?, t: Throwable?) {
                listOfUsers.value = Result.error(t?.localizedMessage)
            }
        })
    }
}