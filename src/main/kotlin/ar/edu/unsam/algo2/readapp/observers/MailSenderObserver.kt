package ar.edu.unsam.algo2.readapp.observers

import ar.edu.unsam.algo2.readapp.Mail
import ar.edu.unsam.algo2.readapp.MailSender
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.usuario.Usuario

class MailSenderObserver(val mailSender: MailSender) : AgregarLibroObserver {

    override fun ejecutar(libro: Libro, usuario: Usuario, recomendacion: Recomendacion) {
        if (!recomendacion.esCreador(usuario)) {
            mailSender.enviarMail(
                Mail(
                    from = "notificaciones@readapp.com.ar",
                    to = recomendacion.creador.direccionMail,
                    asunto = "Se agregó un Libro",
                    cuerpo = this.armarCuerpo(libro, usuario, recomendacion)
                )
            )
        }
    }

    private fun armarCuerpo(libro: Libro, usuario: Usuario, recomendacion: Recomendacion): String {
        return "El usuario: ${usuario.nombre} agrego el Libro ${libro.titulo}" +
                " a la recomendación que tenía estos Títulos:" +
                " ${recomendacion.librosRecomendados.subtract(mutableSetOf(libro))}"
    }
}