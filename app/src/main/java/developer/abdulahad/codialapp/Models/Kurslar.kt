package developer.abdulahad.codialapp.Models

class Kurslar {
    var id = 0
    var nomi = ""
    var haqida = ""

    constructor()
    constructor(id: Int, nomi: String, haqida: String) {
        this.id = id
        this.nomi = nomi
        this.haqida = haqida
    }

    constructor(nomi: String, haqida: String) {
        this.nomi = nomi
        this.haqida = haqida
    }

}