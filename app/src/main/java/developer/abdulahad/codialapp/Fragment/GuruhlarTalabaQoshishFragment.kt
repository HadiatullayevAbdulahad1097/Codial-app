package developer.abdulahad.codialapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulahad.codialapp.DB.MyDbHelper
import developer.abdulahad.codialapp.Models.Talaba
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.databinding.FragmentGuruhlarTalabaQoshishBinding

class GuruhlarTalabaQoshishFragment : Fragment() {
   lateinit var binding: FragmentGuruhlarTalabaQoshishBinding
   lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuruhlarTalabaQoshishBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        binding.saqlash.setOnClickListener {
            val familiya = binding.edtFamiliyasi.text.toString()
            val ism = binding.edtIsmi.text.toString()
            val number = binding.edtNumber.text.toString()
            val student = Talaba(
                familiya,
                ism,
                number
            )
            myDbHelper.addStudent(student)
            myDbHelper.getStudent().add(student)
            findNavController().popBackStack()
        }

        return binding.root
    }
}