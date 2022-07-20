package com.example.ambutrackkotlin

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.patient_login.*
import kotlinx.android.synthetic.main.patient_signup.*

class PatientActivitySignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_signup)

        // Initialize Firebase Auth
        auth = Firebase.auth

        patient_register_button.setOnClickListener { patientSignUp() }

    }

    private fun patientSignUp() {
        val email = findViewById<EditText>(R.id.patient_signup_email_EditText)
        val password = findViewById<EditText>(R.id.patient_signup_password_EditText)

        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("EmailAuth", "createUserWithEmail:success")

                    startActivity(Intent(this, PatientHomePage::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("EmailAuth", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error occurred ${it.localizedMessage} ", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}