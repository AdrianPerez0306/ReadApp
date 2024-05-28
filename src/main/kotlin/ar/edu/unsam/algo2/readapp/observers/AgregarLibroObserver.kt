package ar.edu.unsam.algo2.readapp.observers

import ar.edu.unsam.algo2.readapp.Mail
import ar.edu.unsam.algo2.readapp.MailSender
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.features.Valoracion
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.usuario.Usuario

interface AgregarLibroObserver {


    abstract fun ejecutar(libro: Libro, usuario: Usuario, recomendacion: Recomendacion)
}










