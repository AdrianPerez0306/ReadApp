package ar.edu.unsam.algo2.readapp.observers

import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.usuario.Usuario

class ObserverVetador(val maximo: Int) : ObserverRegistro() {

    override fun ejecutar(libro: Libro, usuario: Usuario, recomendacion: Recomendacion) {
        if (registroAportes.getOrDefault(usuario, 0) >= maximo) {
            recomendacion.creador.vetarAmigo(usuario)
        }
        registroAportes[usuario] = (registroAportes[usuario] ?: 0) + 1
    }
}