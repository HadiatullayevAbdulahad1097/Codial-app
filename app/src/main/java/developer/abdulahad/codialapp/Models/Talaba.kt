package developer.abdulahad.codialapp.Models

import android.icu.util.LocaleData
import java.time.LocalDate

class Talaba {
    var id = 0
    var nomi = ""
    var familiyasi = ""
    var number = ""

    constructor(id: Int, nomi: String, familiyasi: String, number: String) {
        this.id = id
        this.nomi = nomi
        this.familiyasi = familiyasi
        this.number = number
    }

    constructor()
    constructor(nomi: String, familiyasi: String, number: String) {
        this.nomi = nomi
        this.familiyasi = familiyasi
        this.number = number
    }
}