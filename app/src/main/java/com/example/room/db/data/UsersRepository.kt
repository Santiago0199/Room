package com.example.room.db.data

import android.content.Intent
import androidx.lifecycle.LiveData
import com.example.room.db.dao.UserDao
import com.example.room.db.entities.UserEntity
import android.os.AsyncTask
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.room.DashboardActivity
import com.example.room.common.Const
import com.example.room.common.MyApp
import com.example.room.common.SharedPreferencesManager
import com.example.room.db.NotesRoomDatabase

class UsersRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<UserEntity>> = userDao.getAll()

    fun findUserById(id: Long): UserEntity{
        return userDao.findUserById(id)
    }

    fun findUserByEmailPassword(email: String, password: String) {
        findUserByEmailPasswordAsyncTask(email, password)
    }

    fun findUserByEmail(email:String){
        findByEmailAsyncTask(email)
    }

    suspend fun insert(user: UserEntity){
        userDao.insert(user)
    }

    suspend fun delete(user:UserEntity){
        userDao.delete(user)
    }

    suspend fun deleteAll(){
        userDao.deleteAll()
    }

    private class findByEmailAsyncTask(email: String) : AsyncTask<Void, Void, UserEntity>() {
        val emailUser: String = email

        override fun doInBackground(vararg params: Void): UserEntity? {
            val userDao = NotesRoomDatabase.getDatabase(MyApp.getContext()).userDao()
            return userDao.findUserByEmail(emailUser)
        }
        override fun onPostExecute(user: UserEntity?) {
            /*if (user != null) {
                Toast.makeText(MyApp.getContext(), "El usuario ya se encuentra registrado", Toast.LENGTH_SHORT).show()
                /*AlertDialog.Builder(this).setMessage("El usuario ya se encuentra registrado").setPositiveButton("Aceptar") { dialog, _ ->
                    dialog.dismiss() }.show()*/
            } else {
                val newUser = UserEntity(emailNewUser, passwordNewUser)
                viewModelUser.insert(newUser)
                viewModelUser.findUserByEmailPassword(emailNewUser, passwordNewUser).let { us ->
                    if (us != null) {
                        SharedPreferencesManager.setSomeLongValue(Const.ID_USER, us.id)
                        val intent = Intent(applicationContext, DashboardActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }
            }*/
        }
    }

    private class findUserByEmailPasswordAsyncTask(email: String, password: String) : AsyncTask<Void, Void, UserEntity>() {
        val emailUser: String = email
        val paswordUser: String = password

        override fun doInBackground(vararg params: Void): UserEntity? {
            val userDao = NotesRoomDatabase.getDatabase(MyApp.getContext()).userDao()
            return userDao.findUserByEmailPassword(emailUser, paswordUser)
        }
        override fun onPostExecute(user: UserEntity?) {
            if(user != null){
                SharedPreferencesManager.setSomeLongValue(Const.ID_USER, user.id)
                val intent = Intent(MyApp.getContext(), DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                MyApp.getContext().startActivity(intent)
            }else{
                Toast.makeText(MyApp.getContext(), "Usuario No Encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}