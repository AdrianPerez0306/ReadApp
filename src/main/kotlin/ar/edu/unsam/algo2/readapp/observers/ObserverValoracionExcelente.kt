package ar.edu.unsam.algo2.readapp.observers

import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.features.Valoracion
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.usuario.Usuario

class ObserverValoracionExcelente() : AgregarLibroObserver {
    val comentario: String = "Excelente 100% recomendable"
    override fun ejecutar(libro: Libro, usuario: Usuario, recomendacion: Recomendacion) {
        if (!recomendacion.esCreador(usuario) && !recomendacion.fueValoradaPor(usuario)) {
            recomendacion.valoraciones.add(Valoracion(Valoracion.VALOR_MAX, comentario, usuario))
        }
    }
}