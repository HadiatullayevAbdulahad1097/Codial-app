package developer.abdulahad.codialapp.Models

class Mentor {
    var id = 0
    var familiya = ""
    var ismi = ""
    var number = ""
    var mycoure = 0

    constructor()
    constructor(id: Int, familiya: String, ismi: String, number: String, mycoure: Int) {
        this.id = id
        this.familiya = familiya
        this.ismi = ismi
        this.number = number
        this.mycoure = mycoure
    }

    constructor(familiya: String, ismi: String, number: String, mycoure: Int) {
        this.familiya = familiya
        this.ismi = ismi
        this.number = number
        this.mycoure = mycoure
    }
}