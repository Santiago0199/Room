package com.example.room.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.NotesAdapter
import com.example.room.R
import com.example.room.db.entities.NoteEntity

class FavoritesFragment : Fragment() {

    private lateinit var favoritesViewModel: FavoritesViewModel
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
            recycler.adapter = adapter
        }

        launchViewModel()

        return root
    }

    fun launchViewModel(){
        favoritesViewModel = ViewModelProviders.of(activity!!).get(FavoritesViewModel::class.java)
        favoritesViewModel.allFavorites.observe(activity!!, Observer {
                notesFavorites -> notesFavorites?.let {
                    adapter.setListNotes(it)
                }
        })
    }
}