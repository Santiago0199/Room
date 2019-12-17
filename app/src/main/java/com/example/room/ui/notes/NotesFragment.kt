package com.example.room.ui.notes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.common.Const
import com.example.room.common.SharedPreferencesManager
import com.example.room.db.entities.NoteEntity
import com.example.room.db.data.NotesViewModel

class NotesFragment : Fragment() {

    private lateinit var notesViewModel: NotesViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: NotesAdapter
    private lateinit var listNotes: ArrayList<NoteEntity>
    private lateinit var typeList:String

    companion object{
        fun newInstance(noteListType: String): NotesFragment{
            val fragment = NotesFragment()
            val bundle = Bundle()
            bundle.putString(Const.NOTE_LIST_TYPE, noteListType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        setHasOptionsMenu(true)
        initComponents(view)
        getData()
        return view
    }

    private fun initComponents(view: View){
        typeList = arguments!!.getString(Const.NOTE_LIST_TYPE)!!
        if (view is RecyclerView) {
            recycler = view
            listNotes = ArrayList()
            recycler.layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
            adapter = NotesAdapter(activity!!, listNotes)
            recycler.adapter = adapter
        }
    }

    private fun getData(){
        if(typeList == Const.NOTE_LIST_ALL){
            notesViewModel.getByUser(SharedPreferencesManager.getSomeLongValue(Const.ID_USER)).observe(activity!!, Observer {
                adapter.setListNotes(it)

            })
        }else if(typeList == Const.NOTE_LIST_FAV){
            notesViewModel.getFavoritesByUser(SharedPreferencesManager.getSomeLongValue(Const.ID_USER)).observe(activity!!, Observer {
                adapter.setListNotes(it)
            })
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        notesViewModel = ViewModelProviders.of(activity!!).get(NotesViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.add_note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.iconAddNote -> {
                val dialogNewNote = NewNoteFragment()
                dialogNewNote.show(activity!!.supportFragmentManager, Const.NEW_NOTE)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}