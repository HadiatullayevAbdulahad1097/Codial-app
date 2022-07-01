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
import developer.abdulahad.codialapp.Models.Guruh
import developer.abdulahad.codialapp.Models.Mentor
import developer.abdulahad.codialapp.Models.MyObject
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.adpters.MentorlarRoyhatiAdapter
import developer.abdulahad.codialapp.databinding.FragmentMentorlarRoyhatBinding
import developer.abdulahad.codialapp.databinding.ItemDialogEditMentorBinding

class MentorlarRoyhatFragment : Fragment() {
    lateinit var binding: FragmentMentorlarRoyhatBinding
    lateinit var myDbHelper: MyDbHelper
    lateinit var mentorlarRoyhatiAdapter: MentorlarRoyhatiAdapter
    lateinit var list: ArrayList<Mentor>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorlarRoyhatBinding.inflate(layoutInflater)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        myDbHelper = MyDbHelper(binding.root.context)

        list = ArrayList()

        for (i in myDbHelper.getMentor()) {
            if (i.mycoure == MyObject.position) {
                list.add(i)
            }
        }

        mentorlarRoyhatiAdapter =
            MentorlarRoyhatiAdapter(list, object : MentorlarRoyhatiAdapter.RvClick {
                override fun itemClickListener(mentor: Mentor,position:Int) {
                    objmentor.fam = mentor.familiya
                    objmentor.ism = mentor.ismi
                    objmentor.number = mentor.number
                    objmentor.position = position
                    findNavController().navigate(R.id.mentorRoyhatMalumotFragment)
                }

                override fun deleteClickListener(mentor: Mentor) {
                    myDbHelper.deleteMentor(mentor)
                    list.remove(mentor)
                    mentorlarRoyhatiAdapter.notifyDataSetChanged()
                }

                override fun editClickListener(mentor: Mentor) {
                    objmentor.position2 = list.indexOf(mentor)
                    findNavController().navigate(R.id.mentorQoshishFragment)
                }
            })

        binding.imageAdd.setOnClickListener {
            var alertDialog = AlertDialog.Builder(binding.root.context).create()
            var itemMentorDialog = ItemDialogEditMentorBinding.inflate(layoutInflater)
            itemMentorDialog.btnQoshish.setOnClickListener {
                val listMentor = myDbHelper.getMentor()
                if (itemMentorDialog.edtMentorFamiliyasi.text.isNotEmpty() && itemMentorDialog.edtMentorIsmi.text.isNotEmpty() && itemMentorDialog.edtKursNumber.text.isNotEmpty()) {
                    var mentor = Mentor()
                        mentor.familiya = itemMentorDialog.edtMentorFamiliyasi.text.toString()
                        mentor.ismi = itemMentorDialog.edtMentorIsmi.text.toString()
                        mentor.number = itemMentorDialog.edtKursNumber.text.toString()
                        mentor.mycoure = MyObject.position
                    myDbHelper.addMentor(mentor)
                    listMentor.add(mentor)
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.mentorlarRoyhatFragment)
//                    mentorlarRoyhatiAdapter.notifyItemChanged(myDbHelper.getMentor().indexOf(mentor))
                    alertDialog.cancel()
                } else {
                    Toast.makeText(context, "Epmty", Toast.LENGTH_SHORT).show()
                }
            }
            itemMentorDialog.btnYopish.setOnClickListener { alertDialog.cancel() }
            alertDialog.setView(itemMentorDialog.root)
            alertDialog.show()
        }


        binding.rvMentorlarRoyhat.adapter = mentorlarRoyhatiAdapter

        return binding.root
    }
}

object objmentor {
    var position = 0
    var position2 = 0
    var fam = ""
    var ism = ""
    var number = ""
}