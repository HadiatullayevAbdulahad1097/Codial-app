package developer.abdulahad.codialapp.DB

import developer.abdulahad.codialapp.Models.Guruh
import developer.abdulahad.codialapp.Models.Kurslar
import developer.abdulahad.codialapp.Models.Mentor
import developer.abdulahad.codialapp.Models.Talaba

interface MyDbInterface {

    fun addKurslar(kurslar: Kurslar)
    fun getKurslar(): ArrayList<Kurslar>
    fun updateKusrlar(kurslar: Kurslar): Int
    fun deleteKurslar(kurslar: Kurslar)

    fun addGuruh(guruh: Guruh)
    fun getGuruh(): ArrayList<Guruh>
    fun updateGuruh(guruh: Guruh): Int
    fun deleteGuruh(guruh: Guruh)
    fun getMentorById(id: Int): Mentor

    fun addMentor(mentor: Mentor)
    fun getMentor() : ArrayList<Mentor>
    fun updateMentor(mentor: Mentor) : Int
    fun deleteMentor(mentor: Mentor)

    fun addStudent(talaba: Talaba)
    fun getStudent() : ArrayList<Talaba>
    fun updateStudent(talaba: Talaba) : Int
    fun deleteStudent(talaba: Talaba)
}