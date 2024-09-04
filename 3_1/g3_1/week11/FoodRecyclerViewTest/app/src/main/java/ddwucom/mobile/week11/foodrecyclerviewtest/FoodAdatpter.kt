package ddwucom.mobile.week11.foodrecyclerviewtest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ddwucom.mobile.week11.foodrecyclerviewtest.databinding.ListItemBinding

class FoodAdatpter (val foods:ArrayList<FoodDto>):
    RecyclerView.Adapter<FoodAdatpter.FoodViewHolder>(){
    //클래스 이름 Adatpter로 오타남
    override fun getItemCount(): Int = foods.size

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
//       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//        return FoodViewHolder(itemView)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.itemBinding.ivPhoto.setImageResource(foods[position].photo)
        holder.itemBinding.tvFood.text = foods[position].food
        holder.itemBinding.tvCount.text = foods[position].count.toString()
    }

    inner class FoodViewHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        val TAG = "FoodViewHolder"
        init{
            itemBinding.root.setOnClickListener{
                //Log.d(TAG, "${foods[adapterPosition]}")
                listener.onItemClick(it, adapterPosition) //전달받은 리스너의 기능을 수행
            }//그냥 클릭했을때 삭제하는 부분 구현
            itemBinding.root.setOnLongClickListener{
                //롱클릭 리스너 멤버 함수 호출
                listener2.itemLongClick(it, adapterPosition) //전달받은 리스너의 기능을 수행
            }
        }
    }


    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int) : Unit
    }
    lateinit var listener: OnItemClickListener

    fun setOnItemClickListener (listener: OnItemClickListener){
        this.listener = listener
    }//그냥 클릭



    interface ItemLongClickListner{
        fun itemLongClick(view: View, position: Int) : Boolean
    }
    lateinit var listener2: ItemLongClickListner
    fun setItemLongClickListner (listener2: ItemLongClickListner){
        this.listener2 = listener2
    }//롱클릭
}