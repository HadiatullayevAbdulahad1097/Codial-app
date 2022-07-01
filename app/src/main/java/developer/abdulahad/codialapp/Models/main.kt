package developer.abdulahad.codialapp.Models

import java.util.*
import kotlin.collections.ArrayList


fun main() {
    val input = Scanner(System.`in`)
    var count = 0
    do {
        println()
        println("""    
            1 -> O'quvcholarni Random Tarzda ko'rish 
            2 -> Oquvchi qo'shish va Random Tarzda ko'rish
            0 -> Chiqish 
        """.trimIndent())
        when(input.nextInt()){
            1 ->{
                val array = ArrayList<String>()
                array.add("Muhammadziyo")
                array.add("Muhsinjon")
                array.add("Abdulhay")
                array.add("Zakariyo")
                array.add("Ramizbek")
                array.add("MuhammadIso")
                array.add("MuhammadZohid")
                array.add("Iskandar")
                array.add("Abdulahad")
                array.add("Bekzodjon")
                array.add("Abdusamid")
                array.add("Abdulaziz")
                array.add("Kamronbek")
                array.add("Jamshidbek")
                array.add("Ozodbek")

                val list = array.shuffled()
                for (i in list.indices){
                    println("$i : ${list[i]}")
                }
            }
            2 ->{
                val list = ArrayList<String>()
                println("Nechta odam qo'shmoqchisiz:")
                val personCount = input.nextInt()
                for (i in 1 .. personCount){
                    println("$i chi ismni kiriting")
                    val personName = input.next()
                    list.add(personName)
                }
                val random = list.shuffled()
                for (i in random.indices){
                    println("$i : ${random[i]}")
                }
            }
            0 -> count = -1
        }
    } while (count==0)
}