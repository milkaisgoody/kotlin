package ddwu.com.mobile.fooddbexam02

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.fooddbexam02.data.FoodDBHelper
import ddwu.com.mobile.fooddbexam02.data.FoodDto
import ddwu.com.mobile.fooddbexam02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    val REQ_ADD = 100
    val REQ_UPDATE = 200

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var foods : ArrayList<FoodDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*RecyclerView 의 layoutManager 지정*/
        binding.rvFoods.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        foods = getAllFoods()               // DB 에서 모든 food를 가져옴
        val adapter = FoodAdapter(foods)        // adapter 에 데이터 설정
        binding.rvFoods.adapter = adapter   // RecylcerView 에 adapter 설정

        binding.btnAdd.setOnClickListener{//추가 버튼 누르면 AddActivity 뜸
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, REQ_ADD)
        }

        val listener = object : FoodAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, UpdateActivity::class.java)
                intent.putExtra("dto", foods.get(position) )   // 클릭한 RecyclerView 항목 위치의 dto 전달
                startActivityForResult(intent, REQ_UPDATE)      // 수정결과를 받아오도록 Activity 호출
            }
        }

        adapter.setOnItemClickListener(listener)
        adapter.setOnItemLongClickListener(object : FoodAdapter.OnItemLongClickListener{
            override fun onItemLongClick(view: View, position: Int){
                Toast.makeText(this@MainActivity, "롱클릭! ${foods.get(position)}", Toast.LENGTH_SHORT)
                if(deleteFoods(foods.get(position)) > 0){
                    foods.clear()                       // 기존 항목 제거
                    foods.addAll(getAllFoods())         // 항목 추가
                    binding.rvFoods.adapter?.notifyDataSetChanged()      // RecyclerView 갱신 필수
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        binding.rvFoods.adapter?.notifyDataSetChanged()   // 액티비티가 보일 때마다 RecyclerView 를 갱신하고자 할 경우
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_UPDATE -> {
                if (resultCode == RESULT_OK) {
                    foods.clear()                       // 기존 항목 제거
                    foods.addAll(getAllFoods())         // 항목 추가
//                    binding.rvFoods.adapter?.notifyDataSetChanged()      // RecyclerView 갱신
                } else {
                    Toast.makeText(this@MainActivity, "취소됨", Toast.LENGTH_SHORT).show()
                }
            }
            REQ_ADD -> {
                if (resultCode == RESULT_OK) {
                    foods.clear()                       // 기존 항목 제거
                    foods.addAll(getAllFoods())         // 항목 추가
//                    binding.rvFoods.adapter?.notifyDataSetChanged()      // RecyclerView 갱신
                } else {
                    Toast.makeText(this@MainActivity, "취소됨", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //잘라내서 FoodDao에 넣기
    @SuppressLint("Range")
    fun getAllFoods() : ArrayList<FoodDto> {
        val helper = FoodDBHelper(this)
        val db = helper.readableDatabase
//        val cursor = db.rawQuery("SELECT * FROM ${FoodDBHelper.TABLE_NAME}", null)
        val cursor = db.query(FoodDBHelper.TABLE_NAME, null, null, null, null, null, null)

        val foods = arrayListOf<FoodDto>()
        with (cursor) {
            while (moveToNext()) {
                val id = getInt( getColumnIndex(BaseColumns._ID) )
                val food = getString ( getColumnIndex(FoodDBHelper.COL_FOOD) )
                val country = getString ( getColumnIndex(FoodDBHelper.COL_COUNTRY) )
                val dto = FoodDto(id, food, country)
                foods.add(dto)
            }
        }
        return foods
    }
    //까지

    fun deleteFoods(dto: FoodDto): Int{
        val helper = FoodDBHelper(this)
        val db = helper.writableDatabase

        val whereClasure = "${BaseColumns._ID}=?"
        val whereArgs = arrayOf(dto.id.toString())
        val result = db.delete(FoodDBHelper.TABLE_NAME, whereClasure,whereArgs)

        return result
    }
}