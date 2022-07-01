package developer.abdulahad.codialapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulahad.codialapp.Models.Kurslar
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.kurslar.setOnClickListener {
            findNavController().navigate(R.id.kurslarFragment)
        }

        binding.guruhlar.setOnClickListener {
            findNavController().navigate(R.id.gurhlarFragment)
        }

        binding.mentorlar.setOnClickListener {
            findNavController().navigate(R.id.mentorlarFragment)
        }

        return binding.root
    }
}

object codial{
    var position = 0
    var kurslar = Kurslar()
    var delete = false
    var list = ArrayList<Kurslar>()
    var posKurs = 0
}