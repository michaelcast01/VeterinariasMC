open class Mascota(
    val nombre: String,
    val especie: String,
    var edad: Int,
    var peso: Double
) {
    fun cambiarPeso(nuevoPeso: Double) {
        peso = nuevoPeso
    }

    fun aumentarEdad() {
        edad++
    }

    fun describir(): String {
        return "Nombre: $nombre, Especie: $especie, Edad: $edad, Peso: $peso kg"
    }
}