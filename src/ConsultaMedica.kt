class ConsultaMedica(
    nombre: String,
    especie: String,
    edad: Int,
    peso : Double,
    val diagnostico: String,
    val fecha: String,
    val requiereMed: Boolean,
    var costoBase: Double
) : Mascota(nombre, especie, edad, peso) {

    fun calcularCosto(): Double {
        return if (requiereMed) costoBase * 1.15 else costoBase
    }

    fun detalleConsulta(): String {
        return "${describir()}, Fecha: $fecha, Diagnóstico: $diagnostico, " +
                "Requiere Medicación: ${if (requiereMed) "Sí" else "No"}, " +
                "Costo Base: $costoBase, Costo Final: ${calcularCosto()}"
    }
}