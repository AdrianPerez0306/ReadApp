package ar.edu.unsam.algo2.readapp

import java.time.LocalDate
import java.time.Period


class Usuario(
    val nombre: String,
    val apellido: String,
    val alias: String,
    val fechaNacimiento: LocalDate,

    val palabrasPorMinutos: Int

) {
    //Utilizamos la funcion date para obtener la edad a traves
    //de su fecha de nacimiento y la fecha actual
    fun edad(): Int {
        val fechaActual = LocalDate.now()
        val periodo = Period.between(this.fechaNacimiento, fechaActual)
        return periodo.years
    }


    fun promedioLectura(libro: Libro): Double = ((libro.cantidadPalabras / this.palabrasPorMinutos).toDouble())
    fun velocidadLecturaPromedio(libro: Libro) = this.promedioLectura(libro) * this.esDesafiante(libro)
    fun esDesafiante(libro: Libro) = if (libro.esDesafiante()) 2 else 1


}