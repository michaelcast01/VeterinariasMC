fun main() {
    val sc = java.util.Scanner(System.`in`)
    val mascotas = mutableListOf<Mascota>()
    val consultas = mutableListOf<ConsultaMedica>()
    var opcion = 0

    while (opcion != 7) {
        println(
            """
            Menú:
            1. Registrar mascota
            2. Mostrar mascotas
            3. Registrar consulta médica
            4. Modificar datos de mascota
            5. Ver historial de consultas
            6. Calcular costo total de consultas
            7. Salir
            Elige opción:
            """.trimIndent()
        )
        opcion = sc.nextLine().toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                print("Nombre: ")
                val nombre = sc.nextLine()
                print("Especie: ")
                val especie = sc.nextLine()
                print("Edad: ")
                val edad = sc.nextLine().toIntOrNull() ?: 0
                print("Peso (kg): ")
                val peso = sc.nextLine().toDoubleOrNull() ?: 0.0
                mascotas.add(Mascota(nombre, especie, edad, peso))
                println("Mascota registrada: ${mascotas.last().describir()}")
            }
            2 -> {
                if (mascotas.isEmpty()) println("No hay mascotas registradas.")
                else {
                    println("Mascotas:")
                    mascotas.forEachIndexed { i, pet ->
                        println("${i + 1}. ${pet.describir()}")
                    }
                }
            }
            3 -> {
                print("Nombre de la mascota para la consulta: ")
                val nomBuscado = sc.nextLine()
                val pet = mascotas.find { it.nombre.equals(nomBuscado, ignoreCase = true) }
                if (pet == null) {
                    println("Mascota no encontrada.")
                } else {
                    print("Fecha (YYYY-MM-DD): ")
                    val fecha = sc.nextLine()
                    print("Diagnóstico: ")
                    val diagnostico = sc.nextLine()
                    print("¿Requiere medicación? (s/n): ")
                    val medicacion = sc.nextLine().lowercase() == "s"
                    print("Costo base: ")
                    val costo = sc.nextLine().toDoubleOrNull() ?: 0.0
                    consultas.add(
                        ConsultaMedica(pet.nombre, pet.especie, pet.edad, pet.peso, diagnostico, fecha, medicacion, costo)
                    )
                    println("Consulta registrada: ${consultas.last().detalleConsulta()}")
                }
            }
            4 -> {
                print("Nombre de la mascota a modificar: ")
                val nom = sc.nextLine()
                val pet = mascotas.find { it.nombre.equals(nom, ignoreCase = true) }
                if (pet == null) {
                    println("Mascota no encontrada.")
                } else {
                    println("1. Cambiar peso")
                    println("2. Aumentar edad")
                    print("Elige opción: ")
                    when (sc.nextLine()) {
                        "1" -> {
                            print("Nuevo peso: ")
                            val nuevoPeso = sc.nextLine().toDoubleOrNull() ?: pet.peso
                            pet.cambiarPeso(nuevoPeso)
                            println("Actualizado: ${pet.describir()}")
                        }
                        "2" -> {
                            pet.aumentarEdad()
                            println("Actualizado: ${pet.describir()}")
                        }
                        else -> println("Opción inválida.")
                    }
                }
            }
            5 -> {
                print("Historial de consultas para la mascota: ")
                val nomHistorial = sc.nextLine()
                val historial = consultas.filter { it.nombre.equals(nomHistorial, ignoreCase = true) }
                if (historial.isEmpty()) println("No hay consultas para $nomHistorial.")
                else {
                    println("Consultas para $nomHistorial:")
                    historial.forEachIndexed { i, cons ->
                        println("${i + 1}. ${cons.detalleConsulta()}")
                    }
                }
            }
            6 -> {
                print("Costo total para la mascota: ")
                val nomTotal = sc.nextLine()
                val total = consultas.filter { it.nombre.equals(nomTotal, ignoreCase = true) }
                    .sumOf { it.calcularCosto() }
                println("Total: $total")
            }
            7 -> println("Gracias.")
            else -> println("Opción inválida.")
        }
    }
    sc.close()
}
// .trimIndent() se aplica a cadenas multilínea (delimitadas por """)//
//.last()  retorna el último elemento de una lista //
// sumOf Es una función que suma los valores obtenidos a partir de una transformación aplicada a cada elemento
// forEachIndexed es una función que itera sobre cada elemento de una colección y, además, proporciona el índice del elemento en cada iteración.
//filter es una función de las colecciones en Kotlin que devuelve una nueva lista con aquellos elementos que cumplen una condición dada.
// ignoreCase indica que la comparación debe ignorar las mayúsculas y minúsculas.//
// if (requiereMed) { ... } es una estructura condicional que ejecuta el bloque de código dentro de las llaves solo si la condición es verdadera.