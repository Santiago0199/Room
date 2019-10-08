package com.example.room.ui.user.new_user

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyboardShortcutGroup
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.room.R
import com.example.room.db.entities.UserEntity
import android.view.ViewTreeObserver
import android.graphics.Rect
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.example.room.Main
import com.example.room.NotesActivity


class NewUserActivity : AppCompatActivity(), TextWatcher{

    private lateinit var content: ConstraintLayout
    private lateinit var email: TextView
    private lateinit var password: TextView
    private lateinit var reg: Button
    private lateinit var book: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        init()
        keyboard()
    }

    fun init(){
        content = findViewById(R.id.content)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        reg = findViewById(R.id.reg)
        book = findViewById(R.id.imageBookNewUser)

        email.addTextChangedListener(this)
        password.addTextChangedListener(this)

        reg.setOnClickListener {
            val viewModelUser = ViewModelProviders.of(this).get(NewUserViewModel::class.java)
            viewModelUser.findUserByEmail(email = email.text.toString()).observe(this, Observer {
                user -> user.let {
                    if(user != null){
                        AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("El usuario ya se encuentra registrado")
                            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
                                dialog.dismiss()
                            })
                            .show()
                    }else{
                        val user = UserEntity(email = email.text.toString(), password = password.text.toString())
                        viewModelUser.insert(user)
                        Main.user = user
                        val intent = Intent(applicationContext, NotesActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }
            })
        }
    }

    fun keyboard(){
        content.getViewTreeObserver().addOnGlobalLayoutListener {
                val r = Rect()
                content.getWindowVisibleDisplayFrame(r)
                val screenHeight = content.getRootView().getHeight()
                val keypadHeight = screenHeight - r.bottom
                if (keypadHeight > screenHeight * 0.15) {
                    book.visibility = View.GONE
                } else {
                    book.visibility = View.VISIBLE
                }
            }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (!email.text.toString().equals("") && !password.text.toString().equals("")) {
            reg.setTextColor(Color.BLACK)
            reg.setBackgroundResource(R.drawable.style_buttons)
            reg.isEnabled = true
        } else if (reg.isEnabled) {
            reg.setTextColor(ContextCompat.getColor(this, R.color.gray_text_buttons_disable))
            reg.setBackgroundResource(R.drawable.style_button_disable)
            reg.isEnabled = false
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }
}
