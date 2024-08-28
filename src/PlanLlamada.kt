// Clase que representa los diferentes tipos de planes de llamadas
class PlanLlamada(val tipo: TipoPlan) {
    enum class TipoPlan {
        LOCAL, LARGA_DISTANCIA, CELULAR
    }

    // Método para calcular el costo basado en el tipo de plan y la duración de la llamada
    fun calcularCosto(minutos: Int): Int {
        // Usamos if en lugar de when para determinar el costo según el tipo de plan
        if (tipo == TipoPlan.LOCAL) {
            return minutos * 50  // Costo por minuto para llamadas locales
        } else if (tipo == TipoPlan.LARGA_DISTANCIA) {
            return minutos * 350  // Costo por minuto para llamadas de larga distancia
        } else if (tipo == TipoPlan.CELULAR) {
            return minutos * 150  // Costo por minuto para llamadas a celulares
        } else {
            return 0  // Valor por defecto (no debería ocurrir)
        }
    }

    // Sobrescribe el método toString() para proporcionar una representación textual del tipo de plan
    override fun toString(): String {
        // Usamos if en lugar de when para retornar una cadena según el tipo de plan
        if (tipo == TipoPlan.LOCAL) {
            return "Llamada Local"  // Representación textual para el tipo de plan LOCAL
        } else if (tipo == TipoPlan.LARGA_DISTANCIA) {
            return "Llamada de Larga Distancia"  // Representación textual para el tipo de plan LARGA_DISTANCIA
        } else if (tipo == TipoPlan.CELULAR) {
            return "Llamada a Celular"  // Representación textual para el tipo de plan CELULAR
        } else {
            return "Plan desconocido"  // Valor por defecto (no debería ocurrir)
        }
    }
}