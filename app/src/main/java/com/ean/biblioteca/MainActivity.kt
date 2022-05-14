package com.ean.biblioteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize Firebase Auth
        auth = Firebase.auth

        //Este boton me lleva a la actividad de Registrar un usuario
        val boton_registrar_usuario=findViewById<Button>(R.id.bn_registra_am)
        boton_registrar_usuario.setOnClickListener {
            val intent= Intent(this,Registrar::class.java)
            startActivity(intent)
        }

        //Este boton me lleva a la actividad de login
        val boton_login=findViewById<Button>(R.id.bn_login_am)
        boton_login.setOnClickListener {
            val intent= Intent(this,login::class.java)
            startActivity(intent)
        }

        //solo puede entrar a ver los libros si esta logiado
        val boton_ver=findViewById<Button>(R.id.bn_libros_am)
        boton_ver.setOnClickListener {
            if(auth.currentUser!=null){
                val intent= Intent(this,lista_libros::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(baseContext,"Sumerce debe iniciar sesion para ver los libros",Toast.LENGTH_SHORT).show()
            }
        }
        //solo puede crear un libro si esta logueado
        val boton_crear_libro=findViewById<Button>(R.id.bn_crear_libro)
        boton_crear_libro.setOnClickListener {
            if(auth.currentUser!=null){
                val intent= Intent(this,Crear_libros::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(baseContext,"Debe iniciar sesion para crear un libro",Toast.LENGTH_SHORT).show()
            }
        }

        val boton_cerrar_sesion=findViewById<Button>(R.id.bn_cerrar_sesion_am)
        boton_cerrar_sesion.setOnClickListener {
            auth.signOut()//cierra sesion
            if(auth.currentUser==null){
                boton_cerrar_sesion.visibility=View.INVISIBLE
                boton_login.visibility=View.VISIBLE
                boton_registrar_usuario.visibility=View.VISIBLE
            }
        }

        if(auth.currentUser!=null){
            boton_cerrar_sesion.visibility=View.VISIBLE
            boton_login.visibility=View.GONE
            boton_registrar_usuario.visibility=View.GONE
        }
    }
}