package ar.edu.unsam.algo2.readapp

import excepciones.BookException

//Clase libro
class Libro(
    //Estas variables =>> cantidadPalabras y cantidadPaginas
    // Deben insultar en caso que se inicializen con valor 0 o menor
    val cantidadPalabras: Int = 1,
    val cantidadPaginas: Int = 1, //Al querer usar Short en lugar de Int, molesta en el test de inicializacion
    var ediciones: Int = 0,
    val ventasSemanales: Int = 0, //En teoria arrancan siempre en 0
    val esComplejo: Boolean = false,
    var lenguajes: MutableSet<Lenguaje> = mutableSetOf<Lenguaje>()
) {
    //INIT para correcta inicializacion
    init {
        if (cantidadPalabras <= 0) {
            throw BookException("El valor de cantidadPalabras no puede ser menor o igual 0")
        }
        if (cantidadPaginas <= 0) {
            throw BookException("El valor de cantidadPalabras no puede ser menor o igual 0")
        }
    }

    companion object {
        val PAGINAS_LIBRO_LARGO_MIN: Int = 600
        val TRADUCCIONES_MIN: Int = 5   //
        val EDICIONES_MIN: Int = 2      //
        val VENTA_SEMANAL_MIN: Int = 10000    //
    }

    fun esLargo(): Boolean = this.cantidadPaginas > PAGINAS_LIBRO_LARGO_MIN
    fun esDesafiante(): Boolean = this.esComplejo || this.esLargo()
    fun variasTraducciones(): Boolean = this.lenguajes.size >= TRADUCCIONES_MIN
    fun variasEdiciones(): Boolean = this.ediciones > EDICIONES_MIN
    fun esPopular(): Boolean = this.variasEdiciones() || this.variasTraducciones()
    fun superaVentaSemanal() = this.ventasSemanales >= VENTA_SEMANAL_MIN
    fun esBestSeller(): Boolean = this.superaVentaSemanal() && (this.esPopular())
}

enum class Lenguaje() {
    INGLES, ESPANIOL, ALEMAN, ITALIANO, PORTUGUES,
    MANDARIN, ARABE, RUSO, HINDI, FRANCES,
    BENGALI, JAPONES
}