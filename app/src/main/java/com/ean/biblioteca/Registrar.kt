package com.ean.biblioteca

import Mundo.contraseñas_iguales
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

class Registrar : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        // Initialize Firebase Auth
        auth = Firebase.auth
        //boton regresa al menu principal
        val boton_regresar=findViewById<Button>(R.id.bn_regresar_r)
        boton_regresar.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val txt_correo=findViewById<EditText>(R.id.etext_correo_r)
        val txt_contraseña=findViewById<EditText>(R.id.editnumber_contrasena_r)
        val txt_confirmar=findViewById<EditText>(R.id.editnumer_confirmar_r)
        val boton_registrar=findViewById<Button>(R.id.bn_registrarr_r)
        boton_registrar.setOnClickListener {
            try {
                val correo_r = txt_correo.text.toString().lowercase()
                val contraseña_r = txt_contraseña.text.toString()
                val confirmar = txt_confirmar.text.toString()
                if (correo_r.isEmpty() || contraseña_r.isEmpty() || confirmar.isEmpty()) {
                    throw Exception("los campos no pueden estar vacios!")
                } else {
                    if (contraseñas_iguales(contraseña_r, confirmar)) {
                            auth.createUserWithEmailAndPassword(correo_r,contraseña_r)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(baseContext, "Usuario Creado",
                                            Toast.LENGTH_SHORT).show()
                                        Log.d(TAG, "signInWithCustomToken:success")// imprimir en la terminal
                                        //val user = auth.currentUser

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithCustomToken:failure", task.exception)//terminal
                                        Toast.makeText(baseContext, "No se pudo registrar el usuario",
                                            Toast.LENGTH_SHORT).show()//celular
                                    }
                                }

                    } else {
                        throw Exception("las contraseñas no son iguales!")
                    }

                }
            }
            catch(e: Exception){
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

        }
    }
}