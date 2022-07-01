package developer.abdulahad.codialapp.Models

class Guruh {
    var id : Int? = null
    var nomi : String? = null
    var mentor : Mentor? = null
    var vaqt : String? = null
    var kunlar : String? = null
    var openClose : Int? = null
    var mycourse = 0

    constructor()
    constructor(
        id: Int?,
        nomi: String?,
        mentor: Mentor?,
        vaqt: String?,
        kunlar: String?,
        openClose: Int?,
        mycourse: Int
    ) {
        this.id = id
        this.nomi = nomi
        this.mentor = mentor
        this.vaqt = vaqt
        this.kunlar = kunlar
        this.openClose = openClose
        this.mycourse = mycourse
    }

    constructor(
        nomi: String?,
        mentor: Mentor?,
        vaqt: String?,
        kunlar: String?,
        openClose: Int?,
        mycourse: Int
    ) {
        this.nomi = nomi
        this.mentor = mentor
        this.vaqt = vaqt
        this.kunlar = kunlar
        this.openClose = openClose
        this.mycourse = mycourse
    }
}