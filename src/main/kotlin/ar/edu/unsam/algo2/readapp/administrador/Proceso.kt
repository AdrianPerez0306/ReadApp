package ar.edu.unsam.algo2.readapp.administrador
import ServiceLibros
import ar.edu.unsam.algo2.readapp.Mail
import ar.edu.unsam.algo2.readapp.MailSender
import ar.edu.unsam.algo2.readapp.centrosDeLectura.CentroDeLectura
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario

abstract class Proceso() {
    val emailDestinatario:String = "admin@readapp.com.ar"
    //abstract var mailSender: MailSender

    fun ejecutar(mailSender: MailSender){
        this.realizarAccion()
        this.enviarMail(mailSender)
    }

    abstract fun realizarAccion()

    fun enviarMail(mailSender:MailSender){
        mailSender.enviarMail(
            Mail(from = emailDestinatario,
                to = emailDestinatario,
                asunto = "Se realizó el proceso: ${this::class}",
                cuerpo = "Se realizó el proceso: ${this::class}"
            )
        )
    }
}

class BorrarUsuariosInactivos(
    val repositorioAsociado: Repositorio<Usuario>,
): Proceso() {

    override fun realizarAccion() {
        this.filtrarUsuariosInactivos().forEach{
            repositorioAsociado.delete(it)
        }
    }

    fun filtrarUsuariosInactivos(): List<Usuario>{
        val usuariosInactivos = repositorioAsociado.objetosEnMemoria.filter{
            this.noGeneroValoracion(it) && this.noGeneroRecomendacion(it) && this.noConsideranAmigo(it)
        }
        return usuariosInactivos
    }

    fun noGeneroRecomendacion(usuario: Usuario):Boolean = usuario.recomendaciones.isEmpty()

    fun noGeneroValoracion(usuario: Usuario):Boolean = usuario.recomendacionesValoradas.isEmpty()

    fun noConsideranAmigo(usuario: Usuario):Boolean = repositorioAsociado.objetosEnMemoria.all{
            usuarioX -> !usuarioX.esAmigoDe(usuario)
    }
}

class ProcesoActualizadorLibros(
    val servicio: ServiceLibros,
    val repositorio: Repositorio<Libro>,
) : Proceso() {
    override fun realizarAccion() {
        ActualizadorLibro.actualizar(servicio, repositorio)
    }
}

class ProcesoAgregarAutores(
    val autores: List<Autor>,
    val repositorio: Repositorio<Autor>,
) : Proceso() {
    override fun realizarAccion() {
        autores.forEach { autor: Autor -> repositorio.create(autor) }
    }
}

class ProcesoBorradoCentros(
    val repositorio: Repositorio<CentroDeLectura>,
) : Proceso() {
    override fun realizarAccion() {
        centrosExpirados().forEach { repositorio.delete(it) }
    }

    private fun centrosExpirados(): List<CentroDeLectura> {
        return repositorio.objetosEnMemoria.filter { it.estaExpirado() }
    }
}