package org.example
// Archivo: src/main/kotlin/org/example/Fraccion.kt
// Etapa 1: Definición básica de la clase Fraccion

/**
 * Clase que representa una fracción matemática
 * @param numerador el numerador de la fracción
 * @param denominador el denominador de la fracción (no puede ser cero)
 */
class Fraccion(numerador: Int, denominador: Int) {
    
    // Propiedades con getters y setters automáticos
    var numerador: Int = numerador
        get() = field
        set(value) { 
            field = value 
        }
    
    var denominador: Int = denominador
        get() = field
        set(value) { 
            if (value == 0) {
                throw IllegalArgumentException("El denominador no puede ser cero")
            }
            field = value 
        }
    
    // Bloque init para validar el constructor
    init {
        if (denominador == 0) {
            throw IllegalArgumentException("El denominador no puede ser cero")
        }
    }
    
    /**
     * Devuelve la representación en texto de la fracción
     * @return String en formato "numerador/denominador"
     */
    override fun toString(): String {
        return "$numerador/$denominador"
    }
    
    /**
     * Imprime la fracción en consola
     */
    fun mostrar() {
        println(this.toString())
    }
}

// ===========================================
    // ETAPA 2: SUMA Y RESTA DE FRACCIONES
    // ===========================================
    
    /**
     * Operador de suma entre fracciones
     * Fórmula: (a/b) + (c/d) = (ad + bc)/(bd)
     * @param otra la fracción a sumar
     * @return nueva fracción con el resultado simplificado
     */
    operator fun plus(otra: Fraccion): Fraccion {
        val nuevoNumerador = this.numerador * otra.denominador + otra.numerador * this.denominador
        val nuevoDenominador = this.denominador * otra.denominador
        val resultado = Fraccion(nuevoNumerador, nuevoDenominador)
        return resultado.simplificar()
    }
    
    /**
     * Operador de resta entre fracciones
     * Fórmula: (a/b) - (c/d) = (ad - bc)/(bd)
     * @param otra la fracción a restar
     * @return nueva fracción con el resultado simplificado
     */
    operator fun minus(otra: Fraccion): Fraccion {
        val nuevoNumerador = this.numerador * otra.denominador - otra.numerador * this.denominador
        val nuevoDenominador = this.denominador * otra.denominador
        val resultado = Fraccion(nuevoNumerador, nuevoDenominador)
        return resultado.simplificar()
    }
    
    /**
     * Método privado para simplificar la fracción usando el MCD
     * @return nueva fracción en su forma más simple
     */
    private fun simplificar(): Fraccion {
        val mcd = calcularMCD(kotlin.math.abs(numerador), kotlin.math.abs(denominador))
        val numSimplificado = numerador / mcd
        val denSimplificado = denominador / mcd
        
        // Asegurar que el denominador sea positivo (mover el signo al numerador si es necesario)
        return if (denSimplificado < 0) {
            Fraccion(-numSimplificado, -denSimplificado)
        } else {
            Fraccion(numSimplificado, denSimplificado)
        }
    }
    
    /**
     * Calcula el Máximo Común Divisor usando el algoritmo de Euclides
     * @param a primer número
     * @param b segundo número
     * @return el MCD de a y b
     */
    private fun calcularMCD(a: Int, b: Int): Int {
        return if (b == 0) a else calcularMCD(b, a % b)
    }
}

