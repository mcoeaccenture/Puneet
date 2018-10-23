package com.assignment.adapter

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.assignment.R
import com.assignment.model.User


class UserAdapter( val listUsers : ArrayList<User>?) :
        RecyclerView.Adapter<UserAdapter.UserVH>() {

     var selectedUser = MutableLiveData<Int>()

    fun ViewGroup.inflate(layout: Int) : View{
        return LayoutInflater.from(context).inflate(layout,this, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        return  UserVH(parent.inflate(R.layout.item_user))
    }

    override fun getItemCount(): Int {
            return listUsers?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserVH, position: Int) {
      holder.userName.text = listUsers?.get(position)?.name?.capitalize()
        holder.email.text =   listUsers?.get(position)?.email
       holder.phone.text = listUsers?.get(position)?.phone

        holder.parent.setOnClickListener{
            selectedUser.value = listUsers?.get(position)?.id
        }
    }


    inner class UserVH : RecyclerView.ViewHolder{
        val parent : View
        val  userName : TextView
        val  email : TextView
        val  phone : TextView

        constructor(itemView : View) : super(itemView) {
            parent =itemView
            userName = itemView.findViewById(R.id.txtViewName)
            email = itemView.findViewById(R.id.txtEmail)
            phone = itemView.findViewById(R.id.txtPhone)
        }
    }
}