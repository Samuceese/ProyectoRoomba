import Roomba.*

fun main(){
    var sala = Array(SIZE){IntArray(SIZE)}

    var basurilla=0
    var tiempo=1
    println("--------🧹🧹🧹Observando a la ROOMBA aspirar🧹🧹🧹--------")
    generarBloques(sala)
    basuraAleat(sala)
    generarRoomba(sala)

    do {
        println()
        mostrarSala(sala)
        println("Cantidad total de basurilla recogida $basurilla")
        println("Tiempo transcurrido: $tiempo")
        movimientoRoomba(sala)

        Thread.sleep(PAUSE_TIME)
        tiempo++
    }while (tiempo!= MAX_TIME +1)

}