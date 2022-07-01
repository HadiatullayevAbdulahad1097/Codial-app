package developer.abdulahad.codialapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulahad.codialapp.DB.MyDbHelper
import developer.abdulahad.codialapp.Models.Guruh
import developer.abdulahad.codialapp.Models.MyObject
import developer.abdulahad.codialapp.Models.Talaba
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.adpters.StudentAdapter
import developer.abdulahad.codialapp.databinding.FragmentGuruhgaKirishBinding
import developer.abdulahad.codialapp.databinding.ItemDialogDeleteGuruhlarBinding

class GuruhgaKirishFragment : Fragment() {
    lateinit var binding: FragmentGuruhgaKirishBinding
    lateinit var studentAdapter: StudentAdapter
    lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuruhgaKirishBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)
        val list = ArrayList<Talaba>()
        list.addAll(myDbHelper.getStudent())
        val guruh = MyObject.group

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.imageAdd.setOnClickListener {
            findNavController().navigate(R.id.guruhlarTalabaQoshishFragment)
        }

        if (MyObject.close == 0) {
            binding.kursBoshlash.visibility = View.VISIBLE
            binding.kursBoshlash.setOnClickListener {
                binding.kursBoshlash.backgroundTintList = null
                binding.kursBoshlash.isClickable = false
                binding.kursBoshlash.text = "Kurs Boshlangan"
                MyObject.close = 1
                guruh.openClose = 1
                myDbHelper.updateGuruh(guruh)
            }
        } else {
            binding.kursBoshlash.backgroundTintList = null
            binding.kursBoshlash.isClickable = false
            binding.kursBoshlash.text = "Kurs Boshlangan"
        }

        binding.kursBoshlash.setOnClickListener {
            binding.kursBoshlash.backgroundTintList = null
            binding.kursBoshlash.isClickable = false
            binding.kursBoshlash.text = "Kurs Boshlangan"
            MyObject.close = 1
            guruh.openClose = 1
            myDbHelper.updateGuruh(guruh)
        }

        studentAdapter = StudentAdapter(list, object : StudentAdapter.RvCLick {
            override fun editClickListener(talaba: Talaba, position: Int) {
                MyObject.position = position
                findNavController().navigate(R.id.editOquvchiFragment)
            }

            override fun deleteClickListener(talaba: Talaba, position: Int) {
                val alertDialog = AlertDialog.Builder(binding.root.context).create()
                val item =
                    ItemDialogDeleteGuruhlarBinding.inflate(LayoutInflater.from(binding.root.context))

                item.edtQoshish.setOnClickListener {
                    val index = list.indexOf(talaba)
                    myDbHelper.deleteStudent(talaba)
                    list.remove(talaba)
                    studentAdapter.notifyItemRemoved(index)
                    alertDialog.cancel()
                }
                item.edtYopish.setOnClickListener {
                    alertDialog.cancel()
                }

                alertDialog.setView(item.root)
                alertDialog.show()
            }
        })

        binding.rvRoyhat.adapter = studentAdapter

        return binding.root
    }
}
