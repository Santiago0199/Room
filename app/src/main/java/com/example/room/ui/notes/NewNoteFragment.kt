package com.example.room.ui.notes

import android.app.Dialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.widget.Switch
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.room.R
import com.example.room.common.Const
import com.example.room.common.SharedPreferencesManager
import com.example.room.db.entities.NoteEntity
import com.example.room.db.data.NotesViewModel

class NewNoteFragment : DialogFragment() {

    private var etTitle: EditText? = null
    private var etContent:EditText? = null
    private var swNoteFavorite: Switch? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        val view = activity!!.layoutInflater.inflate(R.layout.fragment_new_note, null)

        builder.setTitle("Nueva nota")
            .setMessage("Introduzca los datos de la nueva nota")
            .setPositiveButton("Guardar"){ dialog, _ ->

                val title = etTitle!!.text.toString()
                val content = etContent!!.text.toString()
                val isFavorite = swNoteFavorite!!.isChecked

                val mViewModel = ViewModelProviders.of(activity!!).get(NotesViewModel::class.java)
                mViewModel.insert(NoteEntity(title, content, isFavorite, SharedPreferencesManager.getSomeLongValue(Const.ID_USER)))
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        etTitle = view!!.findViewById(R.id.edTitle)
        etContent = view.findViewById(R.id.edContent)
        swNoteFavorite = view.findViewById(R.id.switchNoteFavorite)

        builder.setView(view)

        return builder.create()
    }
}
