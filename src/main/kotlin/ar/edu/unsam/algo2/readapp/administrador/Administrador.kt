package ar.edu.unsam.algo2.readapp.administrador

import ar.edu.unsam.algo2.readapp.MailSender


class Administrador(val mailSender: MailSender){

    val listaProcesos: MutableList<Proceso> = mutableListOf()

    val procesosEjecutados: MutableList<Proceso> = mutableListOf()

    fun ejecutarListaProcesos(){
        listaProcesos.forEach{  it.ejecutar(mailSender)  }
    }

    fun agregarProceso(proceso:Proceso){
        listaProcesos.add(proceso)
    }

    fun agregarProcesos(procesos:List<Proceso>){
        listaProcesos.addAll(procesos)
    }
}