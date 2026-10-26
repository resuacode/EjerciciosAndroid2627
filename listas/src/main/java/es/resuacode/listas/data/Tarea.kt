package es.resuacode.listas.data

data class FullName(val nombre: String, val apellido: String)

val nombres = listOf<FullName>(
    FullName("Iago", "Aspas"),
    FullName("Vicente", "Guaita"),
    FullName("Tasos", "Douvikas"),
    FullName("Hugo","Álvarez"),
    FullName("Williot","Swedberg"),
    FullName("Hugo","Sotelo"),
    FullName("Fran","Beltrán"),
    FullName("Borja","Iglesias"),
    FullName("Pablo","Durán"),
    FullName("Carlos","Domínguez"),
    FullName("Óscar","Mingueza"),
    FullName("Alexander","Mostovoi"),
    FullName("Vlado","Gudelj"),
    FullName("Valery","Karpin"),
    FullName("Jose Manuel", "Pinto"),
)