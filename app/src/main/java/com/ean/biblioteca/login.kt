package com.ean.biblioteca

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Initialize Firebase Auth
        auth = Firebase.auth
        val boton_regresar=findViewById<Button>(R.id.bn_regresar_l)
        boton_regresar.setOnClickListener {
            val intent= Intent(this,Registrar::class.java)
            startActivity(intent)
        }
        val txt_correo=findViewById<EditText>(R.id.etext_correo_l)
        val txt_contrasena=findViewById<EditText>(R.id.enumber_contrasena_l)
        val boton_inicio_sesion=findViewById<Button>(R.id.bn_entrar_l)
        boton_inicio_sesion.setOnClickListener {
            try {
                val correo=txt_correo.text.toString().lowercase()
                val contraseña=txt_contrasena.text.toString()
                if(correo.isEmpty()||contraseña.isEmpty()){
                    throw Exception("Los campos no pueden estar vacios")
                }else{
                        auth.signInWithEmailAndPassword(correo,contraseña)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(baseContext, "Bienvenido", Toast.LENGTH_SHORT).show()
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithCustomToken:success")
                                    val intent= Intent(this,MainActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                                    Toast.makeText(baseContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                }
            catch (e:Exception){
                Toast.makeText(this, e.message,
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}