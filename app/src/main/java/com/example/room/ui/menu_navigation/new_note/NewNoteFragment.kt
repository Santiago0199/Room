package com.example.room.ui.menu_navigation.new_note

import android.app.Dialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.room.R
import android.widget.Switch
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.room.Main
import com.example.room.db.entities.NoteEntity
import com.example.room.db.entities.UserEntity

class NewNoteFragment : DialogFragment() {

    private var etTitle: EditText? = null
    private var etContent:EditText? = null
    private var swNoteFavorite: Switch? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity!!)

        builder.setTitle("Nueva nota")
            .setMessage("Introduzca los datos de la nueva nota")
            .setPositiveButton("Guardar"){ dialog, id ->

                val title = etTitle!!.text.toString()
                val content = etContent!!.getText().toString()
                val isFavorite = swNoteFavorite!!.isChecked

                // Comunicar al ViewModel el nuevo dato.
                val mViewModel = ViewModelProviders.of(activity!!).get(NewNoteViewModel::class.java!!)
                mViewModel.insert(NoteEntity(title, content, isFavorite, Main.user!!.id))
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, id ->
                dialog.dismiss()
            }

        var view = activity!!.layoutInflater.inflate(R.layout.new_note_fragment, null)

        etTitle = view!!.findViewById(R.id.edTitle)
        etContent = view.findViewById(R.id.edContent)
        swNoteFavorite = view.findViewById(R.id.switchNoteFavorite)

        builder.setView(view)

        // Create the AlertDialog object and return it
        return builder.create()
    }
}
