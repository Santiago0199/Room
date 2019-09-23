package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import com.example.room.ui.NewUserViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var editTextEmail: EditText = findViewById(R.id.editTextEmail)
        var editTextPassword: EditText = findViewById(R.id.editTextPassword)

        var buttonLogin: Button = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            var viewModelUser = ViewModelProviders.of(this).get(NewUserViewModel::class.java)
            viewModelUser.findUser(editTextEmail.text.toString(), editTextPassword.text.toString())
        }

        var buttonRegister: Button = findViewById(R.id.register)
        buttonRegister.setOnClickListener {
            startActivity(Intent(this, NewUserActivity::class.java))
        }
    }
}