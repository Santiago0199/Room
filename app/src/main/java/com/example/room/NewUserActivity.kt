package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.room.db.entities.UserEntity
import com.example.room.ui.NewUserViewModel

class NewUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        var email = findViewById<TextView>(R.id.email)
        var password = findViewById<TextView>(R.id.password)
        var reg = findViewById<Button>(R.id.reg)

        reg.setOnClickListener {
            val viewModelUser = ViewModelProviders.of(this).get(NewUserViewModel::class.java)
            viewModelUser.insert(UserEntity(email = email.text.toString(), pasword = password.text.toString()))
        }
    }
}
