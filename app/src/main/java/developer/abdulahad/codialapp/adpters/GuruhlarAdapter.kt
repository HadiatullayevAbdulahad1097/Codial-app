package developer.abdulahad.codialapp.adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulahad.codialapp.DB.MyDbHelper
import developer.abdulahad.codialapp.Models.Guruh
import developer.abdulahad.codialapp.Models.MyObject
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.databinding.ItemGuruhlarMalumotBinding

class GuruhlarAdapter(var list: List<Guruh>,var rvClick: RvClick) : RecyclerView.Adapter<GuruhlarAdapter.Vh>() {
    inner class Vh(var itemRv: ItemGuruhlarMalumotBinding) : RecyclerView.ViewHolder(itemRv.root){
        fun onBind(guruh: Guruh, position: Int) {
            itemRv.txtGuruhName.text = guruh.nomi

            itemRv.korishParent.setOnClickListener {
                rvClick.showClick(guruh,position)
            }

            itemRv.editParent.setOnClickListener {
                rvClick.editClick(guruh,position)
            }

            itemRv.deleteParent.setOnClickListener {
                rvClick.deleteClick(guruh,position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemGuruhlarMalumotBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position] , position)
    }

    override fun getItemCount(): Int = list.size
}

interface RvClick{
    fun showClick(guruh: Guruh,position: Int)
    fun editClick(guruh: Guruh,position: Int)
    fun deleteClick(guruh: Guruh,position: Int)
}