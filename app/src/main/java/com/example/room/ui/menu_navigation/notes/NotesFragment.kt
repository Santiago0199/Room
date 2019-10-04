package com.example.room.ui.menu_navigation.notes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.Main
import com.example.room.ui.adapter.NotesAdapter
import com.example.room.R
import com.example.room.db.entities.NoteEntity
import com.example.room.db.entities.UserEntity
import com.example.room.ui.menu_navigation.new_note.NewNoteFragment

class NotesFragment : Fragment() {

    private lateinit var notesViewModel: NotesViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: NotesAdapter
    private lateinit var listNotes: ArrayList<NoteEntity>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_notes, container, false)
        setHasOptionsMenu(true)
        if (root is RecyclerView) {
            recycler = root
            listNotes = ArrayList()
            recycler.layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
            adapter = NotesAdapter(listNotes)
            recycler.adapter = adapter
        }

        launchViewModel()
        return root
    }

    private fun launchViewModel() {
        notesViewModel = ViewModelProviders.of(activity!!).get(NotesViewModel::class.java)
        notesViewModel.getByUser(Main.user!!.id).observe(activity!!, Observer { listNotes ->
            listNotes?.let {
                adapter.setListNotes(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.add_note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.iconAddNote -> {
                val dialogNuevaNota = NewNoteFragment()
                dialogNuevaNota.show(activity!!.supportFragmentManager, "NewNote")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}