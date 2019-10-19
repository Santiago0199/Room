package com.example.room

import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.room.db.entities.NoteEntity
import com.example.room.db.entities.UserEntity
import com.example.room.ui.user.NewUserActivity
import com.example.room.view_model.NotesViewModel
import com.example.room.view_model.UserViewModel

class LoginActivity : AppCompatActivity(), TextWatcher, View.OnClickListener {

    private lateinit var content: ConstraintLayout
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var book: ImageView
    private lateinit var buttonLogin: Button
    private lateinit var faceboook: ImageView
    private lateinit var twitter: ImageView
    private lateinit var instagram: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()
        init()
        keyboard()
    }

    fun init(){
        content =  findViewById(R.id.contentLogin)
        book = findViewById(R.id.bookLogin)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        faceboook = findViewById(R.id.imageView2)
        instagram = findViewById(R.id.imageView3)
        twitter = findViewById(R.id.imageView4)
        editTextEmail.addTextChangedListener(this)
        editTextPassword.addTextChangedListener(this)

        buttonLogin.setOnClickListener {
            val viewModelUser = ViewModelProviders.of(this).get(UserViewModel::class.java)
            viewModelUser.findUserByEmailPassword(editTextEmail.text.toString(), editTextPassword.text.toString()).observe(this, Observer {
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

        val buttonRegister: Button = findViewById(R.id.register)
        buttonRegister.setOnClickListener {
            startActivity(Intent(this, NewUserActivity::class.java))
        }

        faceboook.setOnClickListener(this)
        instagram.setOnClickListener(this)
        twitter.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(this, "Estamos trabajando en ello.", Toast.LENGTH_SHORT).show()
    }

    fun keyboard(){
        content.getViewTreeObserver().addOnGlobalLayoutListener {
            val r = Rect()
            content.getWindowVisibleDisplayFrame(r)
            val screenHeight = content.getRootView().getHeight()
            val keypadHeight = screenHeight - r.bottom
            if (keypadHeight > screenHeight * 0.15) {
                book.visibility = View.GONE
                faceboook.visibility = View.GONE
                instagram.visibility = View.GONE
                twitter.visibility = View.GONE
            } else {
                book.visibility = View.VISIBLE
                faceboook.visibility = View.VISIBLE
                instagram.visibility = View.VISIBLE
                twitter.visibility = View.VISIBLE
            }
        }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (!editTextEmail.text.toString().equals("") && !editTextPassword.text.toString().equals("")) {
            buttonLogin.setTextColor(Color.BLACK)
            buttonLogin.setBackgroundResource(R.drawable.style_buttons)
            buttonLogin.isEnabled = true
        } else if (buttonLogin.isEnabled) {
            buttonLogin.setTextColor(ContextCompat.getColor(this, R.color.gray_text_buttons_disable))
            buttonLogin.setBackgroundResource(R.drawable.style_button_disable)
            buttonLogin.isEnabled = false
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }
}