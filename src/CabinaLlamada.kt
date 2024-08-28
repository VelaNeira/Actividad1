class CabinaLlamada(val id:Int) {

    private var numLlamadas = 0  // Número de llamadas realizadas en esta cabina
    private var totalMinutos = 0  // Duración total de las llamadas en minutos en esta cabina
    private var costoTotal = 0  // Costo total de las llamadas en esta cabina

    // Método para registrar una llamada en esta cabina
    fun registrarLlamada(minutos: Int, plan: PlanLlamada) {
        numLlamadas += 1  // Incrementar el número de llamadas en esta cabina
        totalMinutos += minutos  // Sumar los minutos de la llamada a la duración total
        costoTotal += plan.calcularCosto(minutos)  // Sumar el costo de la llamada al costo total
    }

    // Métodos para obtener la información de esta cabina
    fun obtenerNumLlamadas() = numLlamadas
    fun obtenerTotalMinutos() = totalMinutos
    fun obtenerCostoTotal() = costoTotal

    // Método para reiniciar la cabina (resetea sus valores a cero)
    fun reiniciar() {
        numLlamadas = 0
        totalMinutos = 0
        costoTotal = 0
    }
}