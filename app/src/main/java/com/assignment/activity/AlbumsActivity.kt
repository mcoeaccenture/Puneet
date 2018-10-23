package com.assignment.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.assignment.adapter.AlbumAdapter
import com.assignment.R
import com.assignment.model.Album
import com.assignment.model.Result
import com.assignment.viewmodel.AlbumViewModel
import  kotlinx.android.synthetic.main.activity_albums.*
class AlbumsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        val id = intent?.extras?.getInt("id")
        val name = intent?.extras?.getString("name")

        setTitle("$name $title")

       val albumViewModel = ViewModelProviders.of(this).
               get(AlbumViewModel::class.java)


        albumViewModel.getAlbumsByUser(id!!)
        albumViewModel.listOfAlbums.observe(this,
                Observer<Result<ArrayList<Album>>>{
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


    private fun setUpRecycleView(listOfAlbums : ArrayList<Album>?){
        recycleViewAlbum.layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL,false);

        listOfAlbums?.sortBy { it.title }

        recycleViewAlbum.adapter = AlbumAdapter(listOfAlbums)
        recycleViewAlbum.addItemDecoration(DividerItemDecoration(baseContext,LinearLayoutManager.VERTICAL))
    }
}
