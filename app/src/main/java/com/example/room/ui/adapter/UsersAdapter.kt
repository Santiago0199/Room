package com.example.room.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.db.entities.UserEntity

class UsersAdapter(items: List<UserEntity>): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var items: List<UserEntity>

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.template_users, null)
        var holder = ViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items.get(position)
        holder.email?.text = item.email
    }

    fun setListUser(newItems: List<UserEntity>){
        items = newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var email: TextView? = null
        init {
            email = view.findViewById(R.id.emailUser)
        }
    }

}