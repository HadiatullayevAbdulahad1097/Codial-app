package developer.abdulahad.codialapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulahad.codialapp.DB.MyDbHelper
import developer.abdulahad.codialapp.Models.MyObject
import developer.abdulahad.codialapp.Models.Talaba
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.databinding.FragmentEditOquvchiBinding
import developer.abdulahad.codialapp.databinding.ItemRvMentorsBinding


class EditOquvchiFragment : Fragment() {
    lateinit var binding: FragmentEditOquvchiBinding
    lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditOquvchiBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        var list = myDbHelper.getStudent()

        binding.edtFamiliyasi.setText(list[MyObject.position].familiyasi)
        binding.edtIsmi.setText(list[MyObject.position].nomi)
        binding.edtNumber.setText(list[MyObject.position].number)

        binding.btnOk.setOnClickListener {
            var familiya = binding.edtFamiliyasi.text.toString()
            var ismi = binding.edtIsmi.text.toString()
            var number = binding.edtNumber.text.toString()
            var talaba = list[MyObject.position]
            talaba.familiyasi = familiya
            talaba.nomi = ismi
            talaba.number = number
            myDbHelper.updateStudent(talaba)
            findNavController().popBackStack()
        }

        return binding.root
    }
}