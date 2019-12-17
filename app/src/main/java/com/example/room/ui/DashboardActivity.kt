package com.example.room

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.room.common.Const
import com.example.room.common.SharedPreferencesManager
import com.example.room.ui.notes.NotesFragment
import com.example.room.ui.users.UserFragment

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        findViews()

        /*val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_notes, R.id.navigation_favorites, R.id.navigation_users))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*/
        //bundle = intent.extras?.getBundle("BUNDLE")
        //navController.setGraph(R.navigation.mobile_navigation, bundle)
    }

    private fun findViews(){
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, NotesFragment.newInstance(Const.NOTE_LIST_ALL)).commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var f: Fragment? = null
        when (item.itemId) {
            R.id.navigation_notes -> f = NotesFragment.newInstance(Const.NOTE_LIST_ALL)
            R.id.navigation_favorites -> f = NotesFragment.newInstance(Const.NOTE_LIST_FAV)
            R.id.navigation_users -> f = UserFragment()
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, f!!)
            .commit()

        return@OnNavigationItemSelectedListener true
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("¿Deseas cerrar sesión?")
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Aceptar") { dialog, _ ->
                SharedPreferencesManager.setSomeLongValue(Const.ID_USER, Const.DEFAULT_VALUE_ID_USER)
                dialog.dismiss()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }.show()
    }

}
