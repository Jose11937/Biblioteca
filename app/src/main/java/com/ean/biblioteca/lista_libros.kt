package com.ean.biblioteca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class lista_libros : AppCompatActivity() {

    val storage=Firebase.storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_libros)
        val storageRef = storage.reference
        val libros_a = ArrayList<String>()
        val listRef = storage.reference.child("libros")
        listRef.listAll()
            .addOnSuccessListener {
                for (i in it.items) {
                    libros_a.add(i.name + "")
                }
                Log.d("Firebase", "files $libros_a")
            }
            .addOnFailureListener {
                Log.d("Firebase", "error $it")
            }
        val boton_refrescar=findViewById<Button>(R.id.bn_refrescar_l)
        boton_refrescar.setOnClickListener {
            val lista_libros=findViewById<ListView>(R.id.list_view_libros_l)
            val adaplibros=ArrayAdapter(this,android.R.layout.simple_list_item_1,libros_a)
            lista_libros.adapter=adaplibros
            lista_libros.setOnItemClickListener(){parent,view,position,id->
                val nombre_libro=parent.getItemAtPosition(position).toString()
                Toast.makeText(this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show()
                val intent= Intent(this,leer_libro::class.java)
                intent.putExtra("TITULO LIBRO",nombre_libro)//enviando nombre del libro a leer libro
                //enviar informacion extra  a leer_libro, nombre libro
                startActivity(intent)

            }
        }

    }
}