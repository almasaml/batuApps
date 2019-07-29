package com.almas.batuapps.main.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.almas.batuapps.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.signup_layout.view.*

class SignUpFragment : Fragment(){

    lateinit var username: EditText
    lateinit var password: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.signup_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = view.findViewById(R.id.email)
        password = view.findViewById(R.id.pass)

        view.button_second.setOnClickListener {
            val email = username.text.toString()
            val password = password.text.toString()

            Log.d("SignUp", "Email is" + email)
            Log.d("SignUp", "Password:$password")

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        return@addOnCompleteListener
                        Log.d("SignUp", "Gagal Sign Up")
                    } else {
                        Log.d("Main", "Succesfully created user")
                        view.findNavController().navigate(R.id.action_to_first_fragment)
                    }
                }
                .addOnFailureListener {
                    Log.d("Main", "Failed to create Account: ${it.message}")
                }
        }
    }
}