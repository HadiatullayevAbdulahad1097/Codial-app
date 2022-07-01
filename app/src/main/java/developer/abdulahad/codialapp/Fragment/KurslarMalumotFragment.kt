package developer.abdulahad.codialapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import developer.abdulahad.codialapp.DB.MyDbHelper
import developer.abdulahad.codialapp.Models.Kurslar
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.databinding.FragmentKurslarMalumotBinding
import developer.abdulahad.codialapp.databinding.ItemDialogDeleteGuruhlarBinding
import developer.abdulahad.codialapp.databinding.ItemDilogOchirishBinding

class KurslarMalumotFragment : Fragment() {
    lateinit var myDbHelper: MyDbHelper
    lateinit var binding: FragmentKurslarMalumotBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKurslarMalumotBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        val list = myDbHelper.getKurslar()

        binding.tvMalumot.text = list[codial.position].haqida

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.delete.setOnClickListener {
            val alertDialog = AlertDialog.Builder(binding.root.context).create()
            val itemdialog = ItemDilogOchirishBinding.inflate(LayoutInflater.from(context))
            itemdialog.edtQoshish.setOnClickListener {

                for (i in myDbHelper.getMentor()) {
                    if (i.mycoure == codial.position) myDbHelper.deleteMentor(i)
                }

                list.remove(codial.kurslar)
                myDbHelper.deleteKurslar(list[codial.position])
                alertDialog.dismiss()
                findNavController().popBackStack()
            }
            itemdialog.edtYopish.setOnClickListener {
                alertDialog.dismiss()
            }
            alertDialog.setView(itemdialog.root)

            alertDialog.show()
        }
        return binding.root
    }
}
