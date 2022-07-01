package developer.abdulahad.codialapp.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulahad.codialapp.Models.Talaba
import developer.abdulahad.codialapp.R
import developer.abdulahad.codialapp.databinding.ItemRvOquvchilarBinding

class StudentAdapter(var list: List<Talaba>,var rvCLick: RvCLick) : RecyclerView.Adapter<StudentAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvOquvchilarBinding) : RecyclerView.ViewHolder(itemRv.root){
        fun onBind(talaba: Talaba,position: Int) {
            itemRv.talabaName.text = "${talaba.familiyasi}\n${talaba.nomi}"

            itemRv.editTalaba.setOnClickListener {
                rvCLick.editClickListener(talaba,position)
            }
            itemRv.deleteTalaba.setOnClickListener {
                rvCLick.deleteClickListener(talaba,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvOquvchilarBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position] , position)
    }

    override fun getItemCount(): Int = list.size
    interface RvCLick{
        fun editClickListener(talaba: Talaba,position: Int)
        fun deleteClickListener(talaba: Talaba,position: Int)
    }
}