package developer.abdulahad.codialapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.navigation.fragment.findNavController
import developer.abdulahad.codialapp.DB.MyDbHelper
import developer.abdulahad.codialapp.Models.Guruh
import developer.abdulahad.codialapp.Models.Mentor
import developer.abdulahad.codialapp.Models.MyObject
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.databinding.FragmentGuruhQoshishBinding

class GuruhQoshishFragment : Fragment() {
    lateinit var binding: FragmentGuruhQoshishBinding
    lateinit var listMentor: ArrayList<String>
    lateinit var myDbHelper: MyDbHelper
    lateinit var listvaqt: ArrayList<String>
    lateinit var listkunlar: ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuruhQoshishBinding.inflate(layoutInflater)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }


        myDbHelper = MyDbHelper(binding.root.context)

        var list = myDbHelper.getMentor()

        listMentor = ArrayList()
        listvaqt = ArrayList()
        listkunlar = ArrayList()

        for (i in list) {
            if (i.mycoure == MyObject.position) {
                listMentor.add("${i.ismi} ${i.familiya}")
            }
        }

        binding.spinnerMentor.adapter = ArrayAdapter(binding.root.context,android.R.layout.simple_list_item_1, listMentor)

        listvaqt.add("10 : 00 - 12 : 00")
        listvaqt.add("12 : 00 - 14 : 00")
        listvaqt.add("14 : 00 - 16 : 00")
        listvaqt.add("16 : 00 - 18 : 00")
        listvaqt.add("18 : 00 - 20 : 00")

        binding.spinnerKursVaqti.adapter = ArrayAdapter(binding.root.context,android.R.layout.simple_list_item_1,listvaqt)

        listkunlar.add("Dushanba/Chorshanba/Juma")
        listkunlar.add("Seshanba/Payshanba/Shanba")

        binding.spinnerKunlari.adapter = ArrayAdapter(binding.root.context,android.R.layout.simple_list_item_1,listkunlar)

        binding.saqlash.setOnClickListener {
            val guruhnomi = binding.edtGuruhNomi.text.toString()
            if (listMentor.isNotEmpty()) {
                val mentor = list[binding.spinnerMentor.selectedItemPosition]

            val vaqti = listvaqt[binding.spinnerKursVaqti.selectedItemPosition]
            val kunlari = listkunlar[binding.spinnerKunlari.selectedItemPosition]
            MyObject.guruhnomi = guruhnomi
            MyObject.mentorspin = binding.spinnerMentor.selectedItemPosition
            MyObject.mentor = list
            MyObject.vaqtlar = binding.spinnerKursVaqti.selectedItemPosition
            MyObject.kunlar = kunlari
            if (binding.edtGuruhNomi.text.isNotEmpty()) {
                var guruh = Guruh(
                    guruhnomi,
                    mentor,
                    vaqti,
                    kunlari,
                    0,
                    MyObject.position
                )
                myDbHelper.addGuruh(guruh)
                findNavController().popBackStack()
            }
            }else{
                Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}