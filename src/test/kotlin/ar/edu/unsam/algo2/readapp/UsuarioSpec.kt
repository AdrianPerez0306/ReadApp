package ar.edu.unsam.algo2.readapp

import java.time.LocalDate
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.IsolationMode


class UsuarioSpec : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    //Arrange
    val usuario = Usuario("diego", "lentz", "ld", LocalDate.now().minusYears(31), 10)

    describe("dado un usuario") {

        //Assert
        it("se verifica el calculo de la edad") {
            usuario.edad() shouldBe 31
        }
    }

    describe("dado un libro desafiante") {
        //Arrange
        val libroDesafiante = Libro(5000, 700, 1, 0, false)

        //Assert
        it("se verifica que aumento el tiempo de lectura promedio") {

            usuario.velocidadLecturaPromedio(libroDesafiante) shouldBe 1000.0

        }
    }

    describe("dado un libro no desafiante") {
        //Arrange
        val libroNoDesafiante = Libro(400, 400, 1, 0, false)

        //Assert
        it("se verifica que mantiene el tiempo de lectura promedio") {

            usuario.velocidadLecturaPromedio(libroNoDesafiante) shouldBe 40.0

        }
    }

})
