package com.example.room.ui.notes


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.room.R
import com.example.room.common.Const
import com.example.room.db.entities.NoteEntity
import com.example.room.db.data.NotesViewModel

/**
 * A simple [Fragment] subclass.
 */
class EditNoteFragment : DialogFragment() {

    private var etTitle: EditText? = null
    private var etContent: EditText? = null
    private var swNoteFavorite: Switch? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        val view = activity!!.layoutInflater.inflate(R.layout.fragment_edit_note, null)
        val note = arguments!!.getSerializable(Const.EDIT_NOTE) as NoteEntity

        builder.setTitle("Editar nota")
            .setPositiveButton("Guardar"){ dialog, _ ->

                note.title = etTitle!!.text.toString()
                note.content = etContent!!.text.toString()
                note.favorite = swNoteFavorite!!.isChecked

                val mViewModel = ViewModelProviders.of(activity!!).get(NotesViewModel::class.java)
                mViewModel.update(note)
                dialog.dismiss()
            }
            .setNegativeButton("Eliminar") { dialog, _ ->
                val mViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
                mViewModel.delete(note)
                dialog.dismiss()
            }

        etTitle = view!!.findViewById(R.id.edTitleEdit)
        etContent = view.findViewById(R.id.edContentEdit)
        swNoteFavorite = view.findViewById(R.id.switchNoteFavoriteEdit)
        etTitle!!.setText(note.title)
        etContent!!.setText(note.content)
        swNoteFavorite!!.isChecked = note.favorite

        builder.setView(view)

        // Create the AlertDialog object and return it
        return builder.create()
    }

}
