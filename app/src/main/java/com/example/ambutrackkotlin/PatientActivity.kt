package com.example.ambutrackkotlin

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.patient_login.*

class PatientActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_login)

        auth = Firebase.auth


        login_patient_signup.setOnClickListener {
            val intent = Intent(this, PatientActivitySignUp::class.java)
            startActivity(intent)
        }

        patient_sign_in_button.setOnClickListener { patientLoginIn() }
    }

    private fun patientLoginIn() {
//        val email = findViewById<EditText>(R.id.login_username_input_EditText)
//        val password = findViewById<EditText>(R.id.login_password_input)

        if (login_username_input_EditText.text.isEmpty() || login_password_input.text.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmail = login_username_input_EditText.text.toString()
        val inputPassword = login_password_input.text.toString()

        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    startActivity(Intent(this, PatientHomePage::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error occurred ${it.localizedMessage} ", Toast.LENGTH_SHORT)
                    .show()
            }

    }

}