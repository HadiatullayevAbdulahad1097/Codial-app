package developer.abdulahad.codialapp.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import developer.abdulahad.codialapp.Models.Guruh
import developer.abdulahad.codialapp.Models.Kurslar
import developer.abdulahad.codialapp.Models.Mentor
import developer.abdulahad.codialapp.Models.Talaba

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSIONS),
    MyDbInterface {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE $TABLE_KURSLAR ($KURSLAR_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME_KURSLAR Text NOT NULL,$HAQIDA_KURSLAR text NOT NULL)"
        val query2 =
            "CREATE TABLE $TABLE_GURHLAR ($GURHLAR_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME_GURUHLAR TEXT NOT NULL, $MENTOR_GURUHLAR_ID INTEGER NOT NULL, $VAQT_GURUHLAR TEXT NOT NULL, $KUNLAR_GURUHLAR TEXT NOT NULL,$OPEN_CLOSE INTEGER NOT NULL, $GURUH_MY_COURSE INTEGER NOT NULL, FOREIGN KEY ($MENTOR_GURUHLAR_ID) REFERENCES $TABLE_MENTOR($MENTOR_ID))"
        val query3 =
            "CREATE TABLE $TABLE_MENTOR ($MENTOR_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $FAMILIYA_MENTOR TEXT NOT NULL, $NAME_MENTOR TEXT NOT NULL, $NUMBER_MENTOR Text Not null,$MENTOR_COURSE INTEGER not null)"
        val query4 =
            "CREATE TABLE $TABLE_STUDENT ($STUDENT_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $FAMILIYA_STUDENT TEXT NOT NULL, $NAME_STUDENT TEXT NOT NULL, $NUMBER_STUDENT Text Not null)"
        db?.execSQL(query)
        db?.execSQL(query2)
        db?.execSQL(query3)
        db?.execSQL(query4)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    override fun addKurslar(kurslar: Kurslar) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME_KURSLAR, kurslar.nomi)
        contentValues.put(HAQIDA_KURSLAR, kurslar.haqida)
        database.insert(TABLE_KURSLAR, null, contentValues)
        database.close()
    }

    override fun getKurslar(): ArrayList<Kurslar> {
        val list = ArrayList<Kurslar>()
        val query = "select * from $TABLE_KURSLAR"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val kurslar = Kurslar(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                )
                list.add(kurslar)

            } while (cursor.moveToNext())

        }
        return list
    }

    override fun updateKusrlar(kurslar: Kurslar): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KURSLAR_ID, kurslar.id)
        contentValues.put(NAME_KURSLAR, kurslar.nomi)
        contentValues.put(HAQIDA_KURSLAR, kurslar.haqida)
        return database.update(
            TABLE_KURSLAR,
            contentValues,
            "$KURSLAR_ID = ?",
            arrayOf(kurslar.id.toString())
        )
    }

    override fun deleteKurslar(kurslar: Kurslar) {
        val database = this.writableDatabase
        database.delete(TABLE_KURSLAR, "$KURSLAR_ID = ?", arrayOf(kurslar.id.toString()))
        database.close()
    }

    override fun addGuruh(guruh: Guruh) {
       val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME_GURUHLAR,guruh.nomi)
        contentValues.put(MENTOR_GURUHLAR_ID,guruh.mentor!!.id)
        contentValues.put(VAQT_GURUHLAR,guruh.vaqt)
        contentValues.put(KUNLAR_GURUHLAR,guruh.kunlar)
        contentValues.put(OPEN_CLOSE,guruh.openClose)
        contentValues.put(GURUH_MY_COURSE, guruh.mycourse)
        database.insert(TABLE_GURHLAR,null,contentValues)
        database.close()
    }

    override fun getGuruh(): ArrayList<Guruh> {
        val list = ArrayList<Guruh>()
        val query = "select * from $TABLE_GURHLAR"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {

                val guruhlar = Guruh(
                    cursor.getInt(0),
                    cursor.getString(1),
                    getMentorById(cursor.getInt(2)),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
                )

                list.add(guruhlar)

            } while (cursor.moveToNext())
        }
        return list
    }

    override fun updateGuruh(guruh: Guruh): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(GURHLAR_ID, guruh.id)
        contentValues.put(NAME_GURUHLAR, guruh.nomi)
        contentValues.put(MENTOR_GURUHLAR_ID, guruh.mentor!!.id)
        contentValues.put(VAQT_GURUHLAR, guruh.vaqt)
        contentValues.put(KUNLAR_GURUHLAR, guruh.kunlar)
        contentValues.put(OPEN_CLOSE,guruh.openClose)
        contentValues.put(GURUH_MY_COURSE,guruh.mycourse)
        return database.update(
            TABLE_GURHLAR,
            contentValues,
            "$GURHLAR_ID = ?",
            arrayOf(guruh.id.toString())
        )
    }

    override fun deleteGuruh(guruh: Guruh) {
        val database = this.writableDatabase
        database.delete(TABLE_GURHLAR, "$GURHLAR_ID = ?", arrayOf(guruh.id.toString()))
        database.close()
    }

    override fun getMentorById(id: Int): Mentor {
        val database = this.writableDatabase
        val cursor = database.query(
            TABLE_MENTOR, arrayOf(
                MENTOR_ID,
                FAMILIYA_MENTOR, NAME_MENTOR, NUMBER_MENTOR, MENTOR_COURSE
            ), "$MENTOR_ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor.moveToFirst()
        return Mentor(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getInt(4)
        )
    }

    override fun addMentor(mentor: Mentor) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FAMILIYA_MENTOR,mentor.familiya)
        contentValues.put(NAME_MENTOR,mentor.ismi)
        contentValues.put(NUMBER_MENTOR,mentor.number)
        contentValues.put(MENTOR_COURSE,mentor.mycoure)
        database.insert(TABLE_MENTOR,null,contentValues)
        database.close()
    }

    override fun getMentor(): ArrayList<Mentor> {
        val list = ArrayList<Mentor>()
        val query = "select * from $TABLE_MENTOR"
        val database = this.readableDatabase
        val customer = database.rawQuery(query,null)

        if (customer.moveToFirst()){
            do {

                val mentor = Mentor(
                    customer.getInt(0),
                    customer.getString(1),
                    customer.getString(2),
                    customer.getString(3),
                    customer.getInt(4)
                )

                list.add(mentor)

            }while (customer.moveToNext())
        }

        return list
    }

    override fun updateMentor(mentor: Mentor): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MENTOR_ID,mentor.id)
        contentValues.put(FAMILIYA_MENTOR,mentor.familiya)
        contentValues.put(NAME_MENTOR,mentor.ismi)
        contentValues.put(NUMBER_MENTOR,mentor.number)
        contentValues.put(MENTOR_COURSE,mentor.mycoure)
        return database.update(TABLE_MENTOR,contentValues,"$MENTOR_ID = ?", arrayOf(mentor.id.toString()))
    }

    override fun deleteMentor(mentor: Mentor) {
        val database = this.writableDatabase
        database.delete(TABLE_MENTOR,"$MENTOR_ID = ?", arrayOf(mentor.id.toString()))
        database.close()
    }

    override fun addStudent(talaba: Talaba) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME_STUDENT,talaba.nomi)
        contentValues.put(FAMILIYA_STUDENT,talaba.familiyasi)
        contentValues.put(NUMBER_STUDENT,talaba.number)
        database.insert(TABLE_STUDENT,null,contentValues)
        database.close()
    }

    override fun getStudent(): ArrayList<Talaba> {
        val list = ArrayList<Talaba>()
        val query = "select * from $TABLE_STUDENT"
        val database = this.readableDatabase
        val customer = database.rawQuery(query,null)

        if (customer.moveToFirst()){
            do {

                val talaba = Talaba(
                    customer.getInt(0),
                    customer.getString(1),
                    customer.getString(2),
                    customer.getString(3)
                )

                list.add(talaba)

            } while (customer.moveToNext())
        }
        return list
    }

    override fun updateStudent(talaba: Talaba): Int {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(STUDENT_ID,talaba.id)
        contentValues.put(NAME_STUDENT,talaba.nomi)
        contentValues.put(FAMILIYA_STUDENT,talaba.familiyasi)
        contentValues.put(NUMBER_STUDENT,talaba.number)
        return database.update(TABLE_STUDENT,contentValues,"$STUDENT_ID = ?", arrayOf(talaba.id.toString()))
    }

    override fun deleteStudent(talaba: Talaba) {
        val database = this.writableDatabase
        database.delete(TABLE_STUDENT,"$STUDENT_ID = ?", arrayOf(talaba.id.toString()))
        close()
    }

    companion object {
        const val DB_NAME = "Codial app"

        const val DB_VERSIONS = 1

        //        kurslar
        const val TABLE_KURSLAR = "Kurstable"

        const val KURSLAR_ID = "Kurslar_id"

        const val NAME_KURSLAR = "Kurslarnomi"

        const val HAQIDA_KURSLAR = "Haqidakurslar"

        //        guruhlar
        const val TABLE_GURHLAR = "Guruhlartable"

        const val GURHLAR_ID = "Gurihlar_id"

        const val NAME_GURUHLAR = "Guruhlarnomi"

        const val MENTOR_GURUHLAR_ID = "Mentorguruhlarid"

        const val VAQT_GURUHLAR = "Gurhlarvaqt"

        const val KUNLAR_GURUHLAR = "Gurhlarkunlar"

        const val OPEN_CLOSE = "OpenClose"

        const val GURUH_MY_COURSE = "GuruhMyCourse"
        //        mentorlar
        const val TABLE_MENTOR = "Mentortable"

        const val MENTOR_ID = "Mentorid"

        const val FAMILIYA_MENTOR = "Familiyamentor"

        const val NAME_MENTOR = "Kurslarnomi"

        const val NUMBER_MENTOR = "Mentornumber"

        const val MENTOR_COURSE = "mentorcourse"

        //        studentlar
        const val TABLE_STUDENT = "Studenttable"

        const val STUDENT_ID = "Student_id"

        const val NAME_STUDENT = "Sudentnomi"

        const val FAMILIYA_STUDENT = "familiyastudent"

        const val NUMBER_STUDENT = "numberstudent"
    }
}