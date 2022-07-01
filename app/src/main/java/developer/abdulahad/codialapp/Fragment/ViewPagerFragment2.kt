package developer.abdulahad.codialapp.Fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import developer.abdulahad.codialapp.DB.MyDbHelper
import developer.abdulahad.codialapp.Models.Guruh
import developer.abdulahad.codialapp.Models.MyObject
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.adpters.GuruhlarAdapter
import developer.abdulahad.codialapp.adpters.RvClick
import developer.abdulahad.codialapp.databinding.FragmentViewPager2Binding
import developer.abdulahad.codialapp.databinding.FragmentViewPagerBinding
import developer.abdulahad.codialapp.databinding.ItemDialogDeleteGuruhlarBinding
import developer.abdulahad.codialapp.databinding.ItemGuruhlarDialogBinding

class ViewPagerFragment2 : Fragment() {
    lateinit var binding: FragmentViewPager2Binding
    lateinit var myDbHelper: MyDbHelper
    lateinit var guruhList: ArrayList<Guruh>
    lateinit var guruhlarAdapter: GuruhlarAdapter
    lateinit var listMentor : ArrayList<String>
    lateinit var listVaqt: ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPager2Binding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        guruhList = ArrayList()

        listVaqt = ArrayList()
        listMentor = ArrayList()

        for (i in myDbHelper.getGuruh()){
            if (i.openClose == 0 && i.mycourse == MyObject.position){
                guruhList.add(i)
            }
        }

        guruhlarAdapter = GuruhlarAdapter(guruhList,object : RvClick{
            override fun showClick(guruh: Guruh, position: Int) {
                MyObject.position = position
                MyObject.close = 0
                MyObject.group = guruh
                findNavController().navigate(R.id.guruhgaKirishFragment)
            }

            override fun editClick(guruh: Guruh, position: Int) {
                for (i in myDbHelper.getMentor()) {
                    if (i.mycoure == MyObject.position) {
                        listMentor.add("${i.ismi} ${i.familiya}")
                    }
                }

                var alertDialog = AlertDialog.Builder(binding.root.context).create()
                var item = ItemGuruhlarDialogBinding.inflate(LayoutInflater.from(binding.root.context))

                item.edtMentor.adapter = ArrayAdapter(binding.root.context,android.R.layout.simple_list_item_1,listMentor)
                listVaqt.add("10 : 00 - 12 : 00")
                listVaqt.add("12 : 00 - 14 : 00")
                listVaqt.add("14 : 00 - 16 : 00")
                listVaqt.add("16 : 00 - 18 : 00")
                listVaqt.add("18 : 00 - 20 : 00")
                item.edtKursVaqti.adapter = ArrayAdapter(binding.root.context,android.R.layout.simple_list_item_1,listVaqt)
                item.edtMentor.setSelection(MyObject.mentorspin)
                item.edtKursNomi.setText(guruh.nomi.toString())
                item.edtKursVaqti.setSelection(MyObject.vaqtlar)

                item.edtQoshish.setOnClickListener {
                    guruh.nomi = item.edtKursNomi.text.toString()
                    guruh.mentor = myDbHelper.getMentor()[item.edtMentor.selectedItemPosition]
                    guruh.vaqt = listVaqt[item.edtKursVaqti.selectedItemPosition]
                    guruh.kunlar = guruh.kunlar
                    var guruhlar = Guruh(
                        guruh.nomi,
                        guruh.mentor,
                        guruh.vaqt,
                        guruh.kunlar,
                        0,
                        MyObject.position
                    )
                    myDbHelper.updateGuruh(guruhlar)
                    guruhlarAdapter.notifyDataSetChanged()
                    alertDialog.cancel()
                }

                item.edtYopish.setOnClickListener {
                    alertDialog.cancel()
                }

                alertDialog.setView(item.root)

                alertDialog.show()
            }

            override fun deleteClick(guruh: Guruh, position: Int) {
                var alertDialog = AlertDialog.Builder(binding.root.context).create()
                var itemDaialoog = ItemDialogDeleteGuruhlarBinding.inflate(LayoutInflater.from(binding.root.context))
                itemDaialoog.edtQoshish.setOnClickListener {
                    myDbHelper.deleteGuruh(guruh)
                    guruhList.remove(guruh)
                    guruhlarAdapter.notifyItemRemoved(position)
                    alertDialog.cancel()
                }
                itemDaialoog.edtYopish.setOnClickListener {
                    alertDialog.cancel()
                }

                alertDialog.setView(itemDaialoog.root)

                alertDialog.show()
            }
        })

        binding.rv.adapter = guruhlarAdapter

        return binding.root
    }
}