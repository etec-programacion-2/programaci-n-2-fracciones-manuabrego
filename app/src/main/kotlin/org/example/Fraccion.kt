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
    
    // ===========================================
    // ETAPA 3: MULTIPLICACIÓN Y DIVISIÓN
    // ===========================================
    
    /**
     * Operador de multiplicación entre fracciones
     * Fórmula: (a/b) * (c/d) = (ac)/(bd)
     * @param otra la fracción a multiplicar
     * @return nueva fracción con el resultado simplificado
     */
    operator fun times(otra: Fraccion): Fraccion {
        val nuevoNumerador = this.numerador * otra.numerador
        val nuevoDenominador = this.denominador * otra.denominador
        val resultado = Fraccion(nuevoNumerador, nuevoDenominador)
        return resultado.simplificar()
    }
    
    /**
     * Operador de división entre fracciones
     * Fórmula: (a/b) / (c/d) = (ad)/(bc)
     * @param otra la fracción divisor
     * @return nueva fracción con el resultado simplificado
     * @throws IllegalArgumentException si el numerador de la fracción divisor es cero
     */
    operator fun div(otra: Fraccion): Fraccion {
        if (otra.numerador == 0) {
            throw IllegalArgumentException("No se puede dividir por cero (fracción con numerador cero)")
        }
        
        val nuevoNumerador = this.numerador * otra.denominador
        val nuevoDenominador = this.denominador * otra.numerador
        val resultado = Fraccion(nuevoNumerador, nuevoDenominador)
        return resultado.simplificar()
    }
    
    // ===========================================
    // ETAPA 4: VALIDACIONES Y MÉTODOS DE UTILIDAD
    // ===========================================
    
    /**
     * Operador de comparación entre fracciones
     * @param otra fracción a comparar
     * @return -1 si esta fracción es menor, 0 si son iguales, 1 si es mayor
     */
    operator fun compareTo(otra: Fraccion): Int {
        // Comparar usando productos cruzados: a/b vs c/d → ad vs bc
        val producto1 = this.numerador * otra.denominador
        val producto2 = otra.numerador * this.denominador
        return producto1.compareTo(producto2)
    }
    
    /**
     * Verifica si dos fracciones son iguales
     * @param other objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraccion) return false
        
        // Comparar usando productos cruzados
        val producto1 = this.numerador * other.denominador
        val producto2 = other.numerador * this.denominador
        return producto1 == producto2
    }
    
    /**
     * Verifica si esta fracción es mayor que otra
     * @param otra fracción a comparar
     * @return true si esta fracción es mayor
     */
    fun esMayor(otra: Fraccion): Boolean {
        return this.compareTo(otra) > 0
    }
    
    /**
     * Verifica si esta fracción es menor que otra
     * @param otra fracción a comparar
     * @return true si esta fracción es menor
     */
    fun esMenor(otra: Fraccion): Boolean {
        return this.compareTo(otra) < 0
    }
    
    /**
     * Convierte la fracción a su representación decimal
     * @return valor decimal de la fracción
     */
    fun aDecimal(): Double {
        return numerador.toDouble() / denominador.toDouble()
    }
    
    /**
     * Objeto companion para métodos estáticos
     */
    companion object {
        /**
         * Crea una fracción a partir de un número decimal
         * @param decimal número decimal a convertir
         * @return fracción equivalente al decimal
         */
        fun desdeDecimal(decimal: Double): Fraccion {
            val precision = 1000000 // Precisión para 6 decimales
            val numerador = (decimal * precision).toInt()
            val denominador = precision
            
            return Fraccion(numerador, denominador).simplificar()
        }
    }
}

// AQUÍ HACER PUSH: "Etapa 4: Validaciones y métodos de utilidad completados"
