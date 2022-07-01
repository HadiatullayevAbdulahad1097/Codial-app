package developer.abdulahad.codialapp.adpters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import developer.abdulahad.codialapp.Fragment.ViewPagerFragment
import developer.abdulahad.codialapp.Fragment.ViewPagerFragment2
import developer.abdulahad.codialapp.Models.Guruh

class MyPagerAdapter  (fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ViewPagerFragment()
            else -> ViewPagerFragment2()
        }
    }
}