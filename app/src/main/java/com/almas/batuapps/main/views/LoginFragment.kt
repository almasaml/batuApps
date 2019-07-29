package com.almas.batuapps.main.views

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.almas.batuapps.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_layout.view.*

class LoginFragment : Fragment(){

    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var mAuth : FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = view.findViewById(R.id.userName)
        password = view.findViewById(R.id.password)

        mAuth = FirebaseAuth.getInstance()

        view.button_first.setOnClickListener {

            val email = username.text.toString().trim()
            val pass = password.text.toString().trim()

            if (TextUtils.isEmpty(email)){
                username.error = "Enter Email"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(pass)){
                password.error = "Enter Password"
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener{
                    if (!it.isSuccessful){ return@addOnCompleteListener
                        view.findNavController().navigate(R.id.action_to_menu_main)
                    } else
                    {
                        view.findNavController().navigate(R.id.action_to_menu_main)
                    }
                }
                .addOnFailureListener {
                    Log.d("main", "Failed Login: ${it.message}")
                }
        }

        view.registrasi.setOnClickListener {
            it.findNavController().navigate(R.id.action_to_second_fragment)
        }
    }

}