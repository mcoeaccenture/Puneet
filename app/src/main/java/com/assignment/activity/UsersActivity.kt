package com.assignment.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.assignment.R
import com.assignment.adapter.AlbumAdapter
import com.assignment.adapter.UserAdapter
import com.assignment.model.Result
import com.assignment.model.User
import com.assignment.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        userViewModel.getAllUsers()
        userViewModel.listOfUsers.observe(this, Observer<Result<ArrayList<User>>> {
            when (it?.status){
                Result.Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    txtError.visibility = View.GONE
                }
                Result.Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    txtError.text = it.exception
                    txtError.visibility = View.VISIBLE
                }
                Result.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    txtError.visibility = View.GONE
                    setUpRecycleView(it.data)
                }
            }
        })
    }

     fun setUpRecycleView(listOfUsers: ArrayList<User>?) {
        recycleViewUsers.layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL,false);

        recycleViewUsers.
                addItemDecoration(DividerItemDecoration(baseContext, LinearLayoutManager.VERTICAL))

        val userAdapter = UserAdapter(listOfUsers)
        recycleViewUsers.adapter=userAdapter

        userAdapter.selectedUser?.observe(this, Observer<Int> {
            val intent = Intent(baseContext,AlbumsActivity::class.java)
            val id = it
            val user = listOfUsers?.find { it.id == id }

            intent.putExtra("id",id)
            intent.putExtra("name", user?.name)

            startActivity(intent)
        })

    }
}