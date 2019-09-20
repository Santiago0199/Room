package com.example.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.room.db.entities.NoteEntity

class NotesAdapter(items: List<NoteEntity>): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var items: List<NoteEntity>? = null

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.template_notes, null)
        var holder = ViewHolder(v)
        return holder
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items?.get(position)
        holder.tvTitle.text = item!!.title!!.capitalize()
        holder.tvContent.text = item!!.content!!.capitalize()
        if(item?.favorite!!) {
            holder.ivFavorite.setImageResource(R.drawable.ic_star_black_24dp);
        }
    }

    fun setListNotes(notes: List<NoteEntity>){
        items = notes
        notifyDataSetChanged()
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        var tvTitle: TextView
        var tvContent: TextView
        var ivFavorite: ImageView
        init{
            tvTitle = view.findViewById(R.id.textViewTitle)
            tvContent = view.findViewById(R.id.textViewContent)
            ivFavorite = view.findViewById(R.id.imageViewFavorite)
        }
        override fun toString(): String {
            return super.toString() + " '" + tvTitle.getText() + "'"
        }
    }
}