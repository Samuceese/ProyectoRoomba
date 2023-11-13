package Roomba


const val ROOMBA = 9
const val BLOQUE = 8
const val SIZE = 5
const val NUM_BLOQUES = 5
const val PAUSE_TIME = 1000L
const val MAX_TIME = 30
const val MAX_BASURA = 5

typealias SALA = Array<IntArray>

fun generarBloques(sala: SALA) {
    var contador = 0
    val filas = sala.size
    val columnas = sala[0].size

    while (contador < NUM_BLOQUES) {
        val fila = (0 until filas).random()
        val columna = (0 until columnas).random()

        if (sala[fila][columna] != BLOQUE) {
            sala[fila][columna] = BLOQUE
            contador++
        }
    }
}


fun mostrarSala(sala: SALA) {
    for (i in sala.indices) {
        for (j in sala[i].indices) {
            print(sala[i][j])
            when (sala[i][j]) {
                ROOMBA -> print("[🤖]")
                BLOQUE -> print("[🧱]")
                else -> print("[🦴]")

            }
        }
        println()
    }
}

fun generarRoomba(sala: SALA){

    val fila = (0 until SIZE).random()
    val columna = (0 until SIZE).random()

    if (sala[fila][columna] != BLOQUE) {
        sala[fila][columna] = ROOMBA
    }
}


fun basuraAleat(sala: SALA) {
    for (i in sala.indices) {
        for (j in sala[i].indices) {
            if (sala[i][j] != BLOQUE && sala[i][j] != ROOMBA) {
                sala[i][j] = (1..MAX_BASURA).random()
            }
        }
    }
}


fun direccionAleatRoomba(): IntArray {
    var nuevaDireccionFila = 0
    var nuevaDireccionColumna = 0
    do {
        nuevaDireccionFila = intArrayOf(-1, 0, 1).random()
        nuevaDireccionColumna = (-1..1).random()
    } while (nuevaDireccionFila == 0 && nuevaDireccionColumna == 0)

    return intArrayOf(nuevaDireccionFila, nuevaDireccionColumna)
}

fun validarMovimientoRoomba(sala: SALA, fila: Int, columna: Int): Boolean {
    val filas = sala.size
    val columnas = sala[0].size

    return fila in 0 until filas && columna in 0 until columnas && sala[fila][columna] != BLOQUE
}

fun movimientoRoomba(sala: SALA) {
    val posicionActual = encontrarRoomba(sala)
    val direccion = direccionAleatRoomba()
    val nuevaFila = posicionActual[0] + direccion[0]
    val nuevaColumna = posicionActual[1] + direccion[1]

    if (validarMovimientoRoomba(sala, nuevaFila, nuevaColumna)) {
        // Limpiar la posición actual de la Roomba
        sala[posicionActual[0]][posicionActual[1]] = 0
        // Mover la Roomba a la nueva posición
        sala[nuevaFila][nuevaColumna] = ROOMBA
    }
}

fun encontrarRoomba(sala: SALA): IntArray {
    for (i in sala.indices) {
        for (j in sala[i].indices) {
            if (sala[i][j] == ROOMBA) {
                return intArrayOf(i, j)
            }
        }
    }

    return intArrayOf(-1, -1)
}




