package ddwu.com.mobile.finalreport

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.finalreport.databinding.ListItemBinding

class DiaryAdapter (val diarys: ArrayList<DiaryDto>):
    RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>(){

    override fun getItemCount() = diarys.size //원본 데이터의 개수를 반환

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiaryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.itemBinding.ivPhoto.setImageResource(diarys[position].photo)
        holder.itemBinding.tvTitle.text = diarys[position].title
        holder.itemBinding.tvDate.text = diarys[position].date
        holder.itemBinding.tvWeather.text = diarys[position].weather
    }

    inner class DiaryViewHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        val TAG = "DiaryViewHolder"
        init {
            itemBinding.root.setOnClickListener{
//                Log.d(TAG, "${diarys[adapterPosition]}") //아이템 클릭하면 수정 화면으로 넘어가도록 수정하기
                listener.onItemClick(it, adapterPosition)
            }

            itemBinding.root.setOnLongClickListener{
                longListener.onItemLongClick(itemBinding.root, adapterPosition)
                true
            }
        }
    } //미리 찾아둠

    interface OnItemClickListener{
        fun onItemClick(view:View, position:Int) : Unit
    }
    lateinit var listener: OnItemClickListener
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    interface OnItemLongClickListener{
        fun onItemLongClick(view: View, position: Int)
    }

    lateinit var longListener: OnItemLongClickListener
    fun setOnItemLongClickListener(longListener: OnItemLongClickListener){
        this.longListener = longListener
    }


}