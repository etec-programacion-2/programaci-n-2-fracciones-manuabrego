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