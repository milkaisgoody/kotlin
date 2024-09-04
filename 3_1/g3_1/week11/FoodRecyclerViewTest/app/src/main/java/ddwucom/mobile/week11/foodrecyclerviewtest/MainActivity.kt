package ddwucom.mobile.week11.foodrecyclerviewtest

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ddwucom.mobile.week11.foodrecyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val foods = FoodDao().foods
        val adapter = FoodAdatpter(foods)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        val listener = object : FoodAdatpter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(this@MainActivity, "${foods[position]}", Toast.LENGTH_SHORT).show()
            }
        }
        adapter.setOnItemClickListener(listener)

//        val listener2 = object : FoodAdatpter.ItemLongClickListner{
//            override fun itemLongClick(view: View, position: Int): Boolean{
//                foods.removeAt(position) //삭제하는 부분
//                adapter.notifyDataSetChanged()//삭제하는 부분
//                return true
//            }
//        }
        val listener2 = object : FoodAdatpter.ItemLongClickListner {
            override fun itemLongClick(view: View, position: Int): Boolean {
                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("삭제 확인 메시지")
                    setMessage("정말 이 항목을 삭제하시겠습니까?")
                    setNegativeButton("취소", null)
                    setPositiveButton("확인") { dialog, which ->
                        foods.removeAt(position)
                        adapter.notifyDataSetChanged()
                    }
                    show()
                }
                return true
            }
        }
        adapter.setItemLongClickListner(listener2)
    }
}