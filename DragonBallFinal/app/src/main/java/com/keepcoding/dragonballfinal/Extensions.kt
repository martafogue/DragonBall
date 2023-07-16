package com.keepcoding.dragonballfinal

//función de extensión: accedemos a todos los métodos de un string en este caso
//esta funcion lo que hace es meterse dentro de un string. Como una scope
fun String.contarVocales() : Int {
    var contadorVocales = 0
    forEach {
        if (it == 'a')
            contadorVocales++
    }
    return  contadorVocales
}
//tmb podemos usar la función de extensión de Strings sobre una lista
// En este caso estamos itereando sobre uan lista de string y sumamos el resutlado de contarVocales del string
fun List<String>.contartVocales() : Int {
    var contadorVocales = 0
    forEach { contadorVocales = it.contarVocales() }
    return  contadorVocales
}