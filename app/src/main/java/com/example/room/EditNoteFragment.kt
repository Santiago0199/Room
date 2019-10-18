package com.example.room


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.room.db.entities.NoteEntity
import com.example.room.view_model.NotesViewModel

/**
 * A simple [Fragment] subclass.
 */
class EditNoteFragment : DialogFragment() {

    private var etTitle: EditText? = null
    private var etContent: EditText? = null
    private var swNoteFavorite: Switch? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity!!)
        var note = arguments!!.getSerializable("NOTE") as NoteEntity

        builder.setTitle("Editar nota")
            .setPositiveButton("Guardar"){ dialog, id ->

                note.title = etTitle!!.text.toString()
                note.content = etContent!!.text.toString()
                note.favorite = swNoteFavorite!!.isChecked

                // Comunicar al ViewModel el nuevo dato.
                val mViewModel = ViewModelProviders.of(activity!!).get(NotesViewModel::class.java!!)
                mViewModel.update(note)
                dialog.dismiss()
            }
            .setNegativeButton("Eliminar") { dialog, id ->
                val mViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java!!)
                mViewModel.delete(note)
                dialog.dismiss()
            }

        var view = activity!!.layoutInflater.inflate(R.layout.fragment_edit_note, null)

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
