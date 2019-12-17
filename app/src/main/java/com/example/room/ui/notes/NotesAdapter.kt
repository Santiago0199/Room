package com.example.room.ui.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.room.R
import com.example.room.common.Const
import com.example.room.db.entities.NoteEntity

class NotesAdapter(ctx: Context, items: List<NoteEntity>): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var ctx: Context? = null
    var items: List<NoteEntity>? = null

    init {
        this.ctx = ctx
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.template_notes, null)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.tvTitle!!.text = item!!.title!!.capitalize()
        holder.tvContent!!.text = item.content!!.capitalize()
        if(item.favorite) {
            holder.ivFavorite!!.setImageResource(R.drawable.ic_star_black_24dp)
        }else{
            holder.ivFavorite!!.setImageResource(R.drawable.ic_star_border_black_24dp)
        }
    }

    fun setListNotes(notes: List<NoteEntity>){
        items = notes
        notifyDataSetChanged()
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view), View.OnClickListener{

        var tvTitle: TextView? = null
        var tvContent: TextView? = null
        var ivFavorite: ImageView? = null
        init{
            view.setOnClickListener(this)
            tvTitle = view.findViewById(R.id.textViewTitle)
            tvContent = view.findViewById(R.id.textViewContent)
            ivFavorite = view.findViewById(R.id.imageViewFavorite)
        }

        override fun onClick(v: View?) {
            val bundle = Bundle()
            bundle.putSerializable(Const.EDIT_NOTE, items!![adapterPosition])
            val dialogEditFragment = EditNoteFragment()
            dialogEditFragment.arguments = bundle
            dialogEditFragment.show((ctx!! as AppCompatActivity).supportFragmentManager, Const.EDIT_NOTE)
        }

        override fun toString(): String {
            return super.toString() + " '" + tvTitle!!.text + "'"
        }
    }
}