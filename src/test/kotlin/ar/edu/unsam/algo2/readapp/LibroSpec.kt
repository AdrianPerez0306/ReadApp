package ar.edu.unsam.algo2.readapp

import excepciones.BookException
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

//Acá van los tests de Libro

class LibroSpec : DescribeSpec({
    describe("Correcta Inicializacion del libro") {

        it("No puede instancias con determinadas variables vacias") {
            shouldThrow<BookException> {
                Libro(cantidadPalabras = 0, cantidadPaginas = 0)
            }
        }
        it("Inicializacion por default NO hay error") {
            shouldNotThrow<BookException> { Libro() }
        }
    }
    describe("Libro desafiante") {
        describe("Depende de ser LARGO OR COMPLEJO") {
            val libroNoDesafiante = Libro()
            val libroSoloComplejo = Libro(esComplejo = true)
            val libroSoloLargo = Libro(cantidadPaginas = 601)
            describe("LARGO debe SUPERAR una cantidad de paginas") {
                it("SUPERA, es LARGO") {
                    //
                    libroSoloLargo.cantidadPaginas shouldBeGreaterThan Libro.PAGINAS_LIBRO_LARGO_MIN
                    libroSoloLargo.esLargo() shouldBe (true)
                }
                it("NO SUPERA, NO es LARGO") {
                    //
                    libroSoloComplejo.cantidadPaginas shouldBeLessThanOrEqual Libro.PAGINAS_LIBRO_LARGO_MIN
                    libroSoloComplejo.esLargo() shouldBe (false)
                    //
                    libroNoDesafiante.cantidadPaginas shouldBeLessThanOrEqual Libro.PAGINAS_LIBRO_LARGO_MIN
                    libroNoDesafiante.esLargo() shouldBe (false)
                }
            }
            describe("COMPLEJO es parte del constructor") {
                describe("Libros COMPLEJOS") {
                    it("libroSoloComplejo es COMPLEJO") {
                        //
                        libroSoloComplejo.esComplejo shouldBe (true)
                    }
                }
                describe("Libros NO COMPLEJOS") {
                    it("libroSoloLargo NO es COMPLEJO") {
                        //
                        libroSoloLargo.esComplejo shouldBe (false)
                    }
                    it("LibroNoDesafiante NO es COMPLEJO ") {
                        //
                        libroNoDesafiante.esComplejo shouldBe (false)
                    }
                }
            }
            it("COMPLEJO OR LARGO es DESAFIANTE") {
                libroSoloComplejo.esDesafiante() shouldBe (true)
                libroSoloLargo.esDesafiante() shouldBe (true)
            }

        }
    }

    describe("Traducciones de un libro") {
        //Arrange
        val libroMultilingue = Libro(
            cantidadPalabras = 1, cantidadPaginas = 1,
            lenguajes = mutableSetOf(
                Lenguaje.RUSO,
                Lenguaje.ARABE,
                Lenguaje.ESPANIOL,
                Lenguaje.ALEMAN,
                Lenguaje.HINDI
            )
        )
        val libroBilingue = Libro(
            cantidadPalabras = 1, cantidadPaginas = 1,
            lenguajes = mutableSetOf(
                Lenguaje.RUSO, Lenguaje.ARABE, Lenguaje.ESPANIOL,
                Lenguaje.ALEMAN,
            )
        )

        it("Libro tiene varias traducciones") {
            libroMultilingue.variasTraducciones().shouldBe(true)
        }

        it("Libro NO tiene varias traducciones") {
            libroBilingue.variasTraducciones().shouldBe(false)
        }
    }

    describe("Libro BEST SELLER") {
        describe("Debe superar X ventas semanales AND ser POPULAR") {
            val libroBestSeller = Libro(
                ventasSemanales = 10000, ediciones = 3,
                lenguajes = mutableSetOf(
                    Lenguaje.RUSO, Lenguaje.ARABE, Lenguaje.ESPANIOL,
                    Lenguaje.ALEMAN, Lenguaje.HINDI
                )
            )
            val libroVentasSemanales = Libro(ventasSemanales = 10000)
            val libroPopular = Libro(ediciones = 3, lenguajes = mutableSetOf(Lenguaje.ESPANIOL))
            describe("Libro POPULAR") {
                describe("Depende de: superar nro traducciones OR superar nro ediciones") {
                    val libroVariasEdiciones = Libro(ediciones = 3, lenguajes = mutableSetOf(Lenguaje.ESPANIOL))
                    val libroVariasTraducciones = Libro(
                        ediciones = 2,
                        lenguajes = mutableSetOf(
                            Lenguaje.RUSO, Lenguaje.ARABE, Lenguaje.ESPANIOL,
                            Lenguaje.ALEMAN, Lenguaje.HINDI
                        )
                    )
                    describe("Varias TRADUCCIONES debe superar nro de traducciones") {
                        it("SUPERA, cumple variasTraducciones") {
                            //
                            libroVariasTraducciones.lenguajes.size shouldBeGreaterThanOrEqual Libro.TRADUCCIONES_MIN
                            libroVariasTraducciones.variasTraducciones() shouldBe (true)
                        }
                        it("NO SUPERA, NO cumple variasTraducciones") {
                            //
                            libroVariasEdiciones.lenguajes.size shouldBeLessThan Libro.TRADUCCIONES_MIN
                            libroVariasEdiciones.variasTraducciones() shouldBe (false)
                        }
                    }
                    describe("Varias EDICIONES debe superar nro de ediciones") {
                        it("SUPERA, cumple variasEdiciones") {
                            //
                            libroVariasEdiciones.ediciones shouldBeGreaterThan Libro.EDICIONES_MIN
                            libroVariasEdiciones.variasEdiciones() shouldBe (true)
                        }
                        it("NO SUPERA, NO cumple variasEdiciones") {
                            //
                            libroVariasTraducciones.ediciones shouldBeLessThanOrEqual Libro.EDICIONES_MIN
                            libroVariasTraducciones.variasEdiciones() shouldBe (false)
                        }
                    }

                    it("variasTraducciones OR variasEdiciones es POPULAR") {
                        libroVariasTraducciones.esPopular() shouldBe (true)
                        libroVariasEdiciones.esPopular() shouldBe (true)
                    }
                }
            }
            describe("valor de ventasSemanales") {
                it("SUPERA") {
                    libroVentasSemanales.ventasSemanales shouldBeGreaterThanOrEqual Libro.VENTA_SEMANAL_MIN
                    libroVentasSemanales.superaVentaSemanal() shouldBe (true)

                    libroBestSeller.ventasSemanales shouldBeGreaterThanOrEqual Libro.VENTA_SEMANAL_MIN
                    libroBestSeller.superaVentaSemanal() shouldBe (true)
                }
                it("NO SUPERA") {
                    libroPopular.ventasSemanales shouldBeLessThan Libro.VENTA_SEMANAL_MIN
                    libroPopular.superaVentaSemanal() shouldBe (false)
                }
            }
            describe("Es best seller. Cumple todo") {
                libroBestSeller.esPopular() shouldBe (true)
                libroBestSeller.superaVentaSemanal() shouldBe (true)
                libroBestSeller.esBestSeller() shouldBe true
            }
            describe("NO es best seller. Cumple 1 sola condiciones") {
                it("SOLO cumple ventas semanales") {
                    libroVentasSemanales.superaVentaSemanal() shouldBe (true)
                    libroVentasSemanales.esPopular() shouldBe (false)
                    libroVentasSemanales.esBestSeller() shouldBe (false)
                }
                it("SOLO cumple ser popular") {
                    libroPopular.superaVentaSemanal() shouldBe (false)
                    libroPopular.esPopular() shouldBe (true)
                    libroPopular.esBestSeller() shouldBe (false)
                }
            }

        }
    }
})