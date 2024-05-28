package ar.edu.unsam.algo2.readapp.observers

import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.usuario.Usuario

open class ObserverRegistro() : AgregarLibroObserver {
    val registroAportes: MutableMap<Usuario, Int> = mutableMapOf()

    override fun ejecutar(libro: Libro, usuario: Usuario, recomendacion: Recomendacion) {
        registroAportes[usuario] = this.aportesPorUsuario(usuario) + 1
    }

    fun aportesPorUsuario(usuario: Usuario): Int {
        return registroAportes[usuario] ?: 0
    }

}