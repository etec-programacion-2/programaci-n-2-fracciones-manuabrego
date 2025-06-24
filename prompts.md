----------- ETAPA 1 -------------- PAPI CLAUDE -----------------
Prompt realizado: Hola Claude cómo estás? Hoy necesito ayuda con la programación. Estoy programando en Kotlin, programación orientada a objetos.                                                      El objetivo de este programa es que la clase fracción (con sus respectivos constructores y propiedades) pueda realizar operaciones básicas, como suma, resta, multiplicación y división. Debes recordar lo siguiente: Aplicar buenas prácticas de programación orientada a Kotlin, manejar casos especiales como fracciones con denominador cero o fracciones negativas, desarrollar una interfaz de línea de comandos (CLI) para probar la funcionalidad y utilizar los conceptos de POO.                   A continuación te pasaré cada ejercicio, no realices los push, en cambio haz un comentario identificando dónde hay que hacer cada uno. Debes mantenerlo simple, para un programador que está iniciando en Kotlin. Aquí está lo que debes hacer // y cada parte del read me, en este caso la de la etapa 1. 

Respuesta: ¡Listo! Aquí tienes la implementación de la Etapa 1. Te explico los puntos clave:

Características implementadas:

    Constructor principal: Recibe numerador y denominador como parámetros
    Propiedades con getters y setters:
        El numerador se puede modificar libremente
        El denominador valida que no sea cero antes de asignarse
    Validación en el constructor: Usa el bloque init para verificar que el denominador inicial no sea cero
    Método toString(): Devuelve la fracción en formato "numerador/denominador"
    Método mostrar(): Imprime la fracción en consola

Conceptos de Kotlin utilizados:

    Propiedades automáticas: Con get() y set()
    Bloque init: Se ejecuta cuando se crea la instancia
    Validaciones: Lanzamos IllegalArgumentException para casos inválidos
    Documentación: Comentarios KDoc para explicar la clase y métodos

¿Cómo probar esta clase? Podrías crear un archivo temporal para probar:

kotlin

fun main() {
    val f1 = Fraccion(3, 4)
    f1.mostrar()  // Imprime: 3/4
    
    val f2 = Fraccion(1, 2)
    println(f2)   // Imprime: 1/2
}

¿Te parece clara la implementación? ¿Tienes alguna pregunta sobre esta etapa antes de pasar a la siguiente? 

Y acá el código: 

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

// AQUÍ HACER PUSH: "Etapa 1: Definición básica de la clase Fraccion"