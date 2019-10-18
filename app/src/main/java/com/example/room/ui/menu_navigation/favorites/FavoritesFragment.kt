package com.example.room.ui.menu_navigation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.EditNoteFragment
import com.example.room.ui.adapter.NotesAdapter
import com.example.room.R
import com.example.room.db.entities.NoteEntity
import com.example.room.view_model.NotesViewModel

class FavoritesFragment : Fragment() {

    private lateinit var notesViewModel: NotesViewModel
    private lateinit var listFavorites: List<NoteEntity>
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)

        if (root is RecyclerView) {
            recycler = root
            recycler.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            listFavorites = ArrayList()
            adapter = NotesAdapter(listFavorites)
            adapter.onClickItem(View.OnClickListener { p0 ->
                val bundle = Bundle()
                bundle.putSerializable("NOTE", adapter.items!!.get(recycler.getChildAdapterPosition(p0)))
                val dialogEditFragment = EditNoteFragment()
                dialogEditFragment.arguments = bundle
                dialogEditFragment.show(activity!!.supportFragmentManager, "EditNote")
            })
            recycler.adapter = adapter
        }

        launchViewModel()

        return root
    }

    fun launchViewModel(){
        notesViewModel = ViewModelProviders.of(activity!!).get(NotesViewModel::class.java)
        notesViewModel.allFavorites.observe(activity!!, Observer {
                notesFavorites -> notesFavorites?.let {
                    adapter.setListNotes(it)
                }
        })
    }
}