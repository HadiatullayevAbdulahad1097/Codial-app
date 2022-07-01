package developer.abdulahad.codialapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulahad.codialapp.DB.MyDbHelper
import developer.abdulahad.codialapp.Models.Kurslar
import developer.abdulahad.codialapp.Models.Mentor
import developer.abdulahad.codialapp.Models.MyObject
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.adpters.KurslarAdapter
import developer.abdulahad.codialapp.databinding.FragmentGurhlarBinding

class GurhlarFragment : Fragment() {
    lateinit var binding: FragmentGurhlarBinding
    lateinit var guruhlarAdapter : KurslarAdapter
    lateinit var myDbHelper : MyDbHelper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentGurhlarBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        guruhlarAdapter = KurslarAdapter(myDbHelper.getKurslar(),object : KurslarAdapter.RvClick{
            override fun kurslarRvClick(kurslar: Kurslar, position: Int) {
                MyObject.position = position
                findNavController().navigate(R.id.gurhlarMalumaotFragment)
            }
        })

        binding.rvGurhlar.adapter = guruhlarAdapter

        return binding.root
    }
}