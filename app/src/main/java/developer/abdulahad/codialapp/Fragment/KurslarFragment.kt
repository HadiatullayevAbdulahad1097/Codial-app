package developer.abdulahad.codialapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import developer.abdulahad.codialapp.DB.MyDbHelper
import developer.abdulahad.codialapp.Models.Kurslar
import developer.abdulahad.codialapp.Models.Mentor
import developer.abdulahad.codialapp.Models.MyObject
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.adpters.KurslarAdapter
import developer.abdulahad.codialapp.databinding.FragmentKurslarBinding
import developer.abdulahad.codialapp.databinding.ItemDialogBinding

class KurslarFragment : Fragment() {
    lateinit var myDbHelper: MyDbHelper
    lateinit var kurslarAdapter: KurslarAdapter
    lateinit var binding: FragmentKurslarBinding
    lateinit var kurslar: Kurslar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKurslarBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        val list = myDbHelper.getKurslar()

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.imageAdd.setOnClickListener {
            val customDialog = AlertDialog.Builder(binding.root.context).create()
            val itemDialogBinding = ItemDialogBinding.inflate(LayoutInflater.from(binding.root.context))
            customDialog.setView(itemDialogBinding.root)


            itemDialogBinding.edtYopish.setOnClickListener {
                customDialog.dismiss()
            }
            itemDialogBinding.edtQoshish.setOnClickListener {
                if (itemDialogBinding.edtKursNomi.text.isNotEmpty() && itemDialogBinding.edtKursHaqida.text.isNotEmpty()) {
                    kurslar = Kurslar(
                        itemDialogBinding.edtKursNomi.text.toString(),
                        itemDialogBinding.edtKursHaqida.text.toString()
                    )
                    list.add(kurslar)
                    myDbHelper.addKurslar(kurslar)
                    Toast.makeText(context, "Kurs saqlandi", Toast.LENGTH_SHORT).show()
                    customDialog.dismiss()
                } else {
                    Toast.makeText(context, "Malumot to'liq emas", Toast.LENGTH_SHORT).show()
                }
            }

            customDialog.setView(itemDialogBinding.root)

            customDialog.show()
        }

        codial.list = list
        kurslarAdapter = KurslarAdapter(list , object : KurslarAdapter.RvClick{
            override fun kurslarRvClick(kurslar: Kurslar,position : Int) {
                codial.position = position
                codial.kurslar = kurslar
                codial.list = list
                findNavController().navigate(R.id.kurslarMalumotFragment)
            }
        })

        binding.rvKurslar.adapter = kurslarAdapter

        return binding.root
    }
}