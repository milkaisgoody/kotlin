package ddwu.com.mobile.foodexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.foodexam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var adapter : FoodAdapter
    lateinit var foods : ArrayList<FoodDto>
    val DETAIL_ACTIVITY_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foods = FoodDao().foods

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        adapter = FoodAdapter(foods)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        // btnAdd를 클릭하면 AddFoodActivity 실행
        binding.btnAdd.setOnClickListener{
            val intent = Intent(this, AddFoodActivity::class.java)
            startActivityForResult(intent, DETAIL_ACTIVITY_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            DETAIL_ACTIVITY_CODE -> {
                if(resultCode == RESULT_OK){
                    val data = data?.getSerializableExtra("data") as FoodDto
                    foods.add(data)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}