package Mundo

import java.util.*

fun contraseñas_iguales(contraseña_uno:String,contraseña_dos: String):Boolean{
    return contraseña_uno==contraseña_dos
}

class Libro{
    private var codigo=0
    private var nombre=""
    private var autor=""
    private var fecha_publicacion=""
    constructor()
    constructor(codigo: Int, nombre: String, autor: String, fecha_publicacion: String) {
        this.codigo = codigo
        this.nombre = nombre
        this.autor = autor
        this.fecha_publicacion = fecha_publicacion
    }
    fun getcodigo()=codigo
    fun getnombre()=nombre
    fun getautor()=autor
    fun getfecha_autor()=fecha_publicacion


    override fun toString(): String {
        return "Libro(codigo=$codigo, nombre='$nombre', autor='$autor', fecha_publicacion='$fecha_publicacion')"
    }



}