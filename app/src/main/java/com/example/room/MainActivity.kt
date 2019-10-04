package com.example.room

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.room.ui.user.new_user.NewUserViewModel
import com.example.room.ui.user.new_user.NewUserActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var editTextEmail: EditText = findViewById(R.id.editTextEmail)
        var editTextPassword: EditText = findViewById(R.id.editTextPassword)

        var buttonLogin: Button = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener { v ->
            var viewModelUser = ViewModelProviders.of(this).get(NewUserViewModel::class.java)
            viewModelUser.findUser(editTextEmail.text.toString(), editTextPassword.text.toString()).observe(this, Observer {
                user -> user.let{
                    if(user != null){
                        Main.user = user
                        startActivity(Intent(this, NotesActivity::class.java))
                        finish()
                    }else{
                        AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("Usuario No Encontrado")
                            .setPositiveButton("Aceptar") { dialog, which ->
                                dialog.dismiss()
                            }.show()
                    }
                }
            })
        }

        var buttonRegister: Button = findViewById(R.id.register)
        buttonRegister.setOnClickListener {
            startActivity(Intent(this, NewUserActivity::class.java))
        }
    }
}