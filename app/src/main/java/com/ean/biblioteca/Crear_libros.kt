package com.ean.biblioteca

import Mundo.Libro
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Crear_libros : AppCompatActivity() {
    // Write a message to the database
    val database = Firebase.database
    val myRef = database.getReference("Libros")
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_libros)
        // Initialize Firebase Auth
        auth = Firebase.auth

        val boton_regresar=findViewById<Button>(R.id.bn_regresar_libro)
        boton_regresar.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val txt_codigo=findViewById<EditText>(R.id.text_numero_libro)
        val txt_nombre=findViewById<EditText>(R.id.text_nombre_libro)
        val txt_autor=findViewById<EditText>(R.id.text_autor_libro)
        val txt_fecha=findViewById<EditText>(R.id.text_fecha_publicacion_libro)


        val boton_registrar=findViewById<Button>(R.id.bn_registrar_libro)
        boton_registrar.setOnClickListener {
            try {
                val codigo=txt_codigo.text.toString().toInt()
                val nombre=txt_nombre.text.toString().lowercase()
                val autor=txt_autor.text.toString().lowercase()
                val fecha=txt_fecha.text.toString()
                if(nombre.isEmpty()||autor.isEmpty()||fecha.isEmpty()){
                    throw Exception("Los campos no pueden estar vacios!")
                }
                else{
                    if(auth.currentUser!=null){
                        var librito=Libro(codigo,nombre,autor,fecha)
                        val usuario=myRef.child(auth.uid!!).child(librito.getcodigo().toString())
                        usuario.child("Codigo").setValue(librito.getcodigo())
                        usuario.child("Nombre").setValue(librito.getnombre())
                        usuario.child("autor").setValue(librito.getautor())
                        usuario.child("fecha").setValue(librito.getfecha_autor())

                    }

                }
            }
            catch(e: Exception){
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

        }
    }
}