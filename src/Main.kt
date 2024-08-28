/*Se usan estas librerias para contar el tiempo
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.system.exitProcess
import kotlin.time.measureTime
import kotlin.time.TimeSource
 */
// Ejercicio 1.
import kotlin.random.Random
fun main() {
    //Se declaran las variables.
    var numCabinas = 0  // Número inicial de cabinas
    val cabinas = mutableListOf<CabinaLlamada>()  // Lista para almacenar las cabinas

    var opcion: Int  // Variable para almacenar la opción seleccionada en el menú
    //val mark = TimeSource.Monotonic Se da un punto inicial de tiempo para calcular mas adelante.

    // Crea una nueva cabina
    numCabinas += 1  // Incrementar el número total de cabinas
    val nuevaCabina = CabinaLlamada(id = numCabinas)  // Crear una nueva cabina con ID único
    cabinas.add(nuevaCabina)  // Añadir la nueva cabina a la lista
    println("Cabina creada con ID: ${nuevaCabina.id}")  // Confirmar creación de cabina

    //Se le da bienvenida al usuario.
    println("******************************************************************************")
    println("Bienvenido al control de llamadas.")
    do{
        // Mostrar el menú principal
        println("*------------------------------------------*")
        println("*                 Menú                     *")
        println(" Número de cabinas actuales: $numCabinas  ")
        println("* 1. Registrar una llamada.                *")
        println("* 2. Mostrar información de una cabina.    *")
        println("* 3. Mostrar consolidado total.            *")
        println("* 4. Reiniciar cabina.                     *")
        println("* 5. Crear Cabina.                         *")
        println("* 6. Salir.                                *")
        println("*------------------------------------------*")
        print("Seleccione una opción: ")
        opcion = readln().toInt()

        if (opcion == 1) {
            // Opción para registrar una llamada
            print("Seleccione la cabina: ")
            val idCabina = readln().toInt()
            val cabina = cabinas.find { it.id == idCabina }  // Buscar la cabina con el ID proporcionado

            if (cabina != null) {
                // Selección del plan de llamada
                print("\nSeleccione el tipo de plan:\n1. Local\n2. Larga Distancia\n3. Celular\nOpción: ")
                val opcionPlan = readln().toInt()

                val planSeleccionado = if (opcionPlan == 1) {
                    PlanLlamada(PlanLlamada.TipoPlan.LOCAL)
                } else if (opcionPlan == 2) {
                    PlanLlamada(PlanLlamada.TipoPlan.LARGA_DISTANCIA)
                } else if (opcionPlan == 3) {
                    PlanLlamada(PlanLlamada.TipoPlan.CELULAR)
                } else {
                    println("Opción inválida, seleccionando plan Local por defecto.")
                    PlanLlamada(PlanLlamada.TipoPlan.LOCAL)
                }

                // Genera un número aleatorio entre 1 y 60.
                val minutos = Random.nextInt(1, 61)
                // Registro del tiempo de la llamada
                print("Duración de la llamada en minutos: $minutos")
                cabina.registrarLlamada(minutos, planSeleccionado)
                println("\nLlamada registrada en la cabina $idCabina.")
            } else {
                println("Cabina no encontrada.")
            }

        } else if (opcion == 2) {
            // Opción para mostrar información de una cabina específica
            print("Seleccione la cabina: ")
            val idCabina = readln().toInt()
            val cabina = cabinas.find { it.id == idCabina }  // Buscar la cabina con el ID proporcionado

            if (cabina != null) {
                println("Información de la cabina $idCabina:")
                println("Número de llamadas: ${cabina.obtenerNumLlamadas()}")
                println("Duración total de llamadas: ${cabina.obtenerTotalMinutos()} minutos")
                println("Costo total de llamadas: $${cabina.obtenerCostoTotal()} pesos")
            } else {
                println("Cabina no encontrada.")
            }
        }else if (opcion == 3) {
            // Opción para mostrar un consolidado total de todas las cabinas
            var numTotalLlamadas = 0  // Contador total de llamadas de todas las cabinas
            var totalMinutos = 0  // Contador total de minutos de todas las cabinas
            var costoTotal = 0  // Contador total de costos de todas las cabinas

            // Recorrer todas las cabinas y sumar sus valores al total
            cabinas.forEach {
                numTotalLlamadas += it.obtenerNumLlamadas()
                totalMinutos += it.obtenerTotalMinutos()
                costoTotal += it.obtenerCostoTotal()
            }

            // Mostrar el consolidado total
            println("Consolidado total:")
            println("Número total de llamadas: $numTotalLlamadas")
            println("Duración total de llamadas: $totalMinutos minutos")
            println("Costo total: $$costoTotal pesos")

            // Calcular y mostrar el costo promedio por minuto
            val costoPromedioPorMinuto = if (totalMinutos > 0) costoTotal / totalMinutos else 0
            println("Costo promedio por minuto: $$costoPromedioPorMinuto pesos")
        } else if (opcion == 4) {
            // Opción para reiniciar una cabina
            print("Seleccione la cabina a reiniciar: ")
            val idCabina = readln().toInt()
            val cabina = cabinas.find { it.id == idCabina }  // Buscar la cabina con el ID proporcionado

            if (cabina != null) {
                cabina.reiniciar()  // Reiniciar los datos de la cabina
                println("Cabina $idCabina reiniciada.")
            } else {
                println("Cabina no encontrada.")
            }
        } else if (opcion == 5) {
            // Opción para crear una nueva cabina
            numCabinas += 1  // Incrementar el número total de cabinas
            val nuevaCabina = CabinaLlamada(id = numCabinas)  // Crear una nueva cabina con ID único
            cabinas.add(nuevaCabina)  // Añadir la nueva cabina a la lista
            println("Cabina ${nuevaCabina.id} creada")  // Confirmar creación de cabina

        } else if (opcion == 6) {
            println("Saliendo del programa.")  // Opción para salir del programa

        } else {
            println("Opción inválida. Intente de nuevo.")  // Manejo de opciones no válidas
        }
    } while (opcion != 6)  // Continuar mostrando el menú hasta que se seleccione la opción de salir
}
    /*Codigo para tomar el tiempo.
startTime = mark.markNow()
print("\nPresione 2 para cancelar la llamada: ")
} else if (llamada == 2) {
Si el usuario presiona 2, registramos el tiempo de finalización.
val endTime = mark.markNow()
Calculamos la duración entre el tiempo de finalización y el de inicio.
val duration: Duration = endTime - startTime
Convertimos la duración a minutos completos y se cuenta los minutos para un total.
minutos = (minutos + duration.inWholeMinutes).toInt()
Si la duración es menor a 1 minuto, se registra como 1 minuto
if (minutos < 1) {
  minutos = 1
}
Mostramos la duración de la llamada en minutos.
print("\nLlamada terminada. \nDuración de la llamada: $minutos minutos.")
Se aumenta el número de llamadas realizadas.
numllamada += 1*/