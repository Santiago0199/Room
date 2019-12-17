package com.example.room.ui.users

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.db.entities.UserEntity
import com.example.room.db.data.UserViewModel

class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: UsersAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //profileViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_users, container, false)

        if(root is RecyclerView){
            var arrayList = ArrayList<UserEntity>()
            adapter = UsersAdapter(arrayList)
            recycler = root
            recycler.layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)

            adapter.onClickItem(View.OnClickListener { p0 ->
                AlertDialog.Builder(context)
                    .setTitle("¿Deseas eliminar el usuario?")
                    .setNegativeButton("Cancelar"){dialog, which -> dialog.dismiss() }
                    .setPositiveButton("Aceptar"){dialog, which ->
                        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
                        userViewModel.delete(adapter.items!!.get(recycler.getChildAdapterPosition(p0)))
                    }
                    .show()
            })

            recycler.adapter = adapter
        }

        launchViewModel()

        return root
    }

    fun launchViewModel(){
        userViewModel = ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
        userViewModel.allUsers.observe(activity!!, Observer { listUsers ->
            listUsers.let {
                adapter.setListUser(it)
            }
        })
    }
}