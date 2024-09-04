package ddwu.com.mobile.finalreport

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.finalreport.databinding.ActivityMainBinding
import java.text.FieldPosition

//과제명: 하루 일과를 기록하는 다이어리 앱
//분반: 01분반
//학번: 20210305 성명: 조정원
//제출일: 2024년 6월 25일
class MainActivity : AppCompatActivity() {
    val INTRODUCE_CODE = 100
    val ADD_CODE = 200
    val UPDATE_CODE = 300

    lateinit var mainBinding: ActivityMainBinding
    lateinit var adapter : DiaryAdapter
    lateinit var diarys : ArrayList<DiaryDto>
    lateinit var helper : DiaryDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

//        diarys = DiaryDao().diarys 수정 부분

        diarys = getAllDiarys()
        helper = DiaryDBHelper(this)
        val db = helper.readableDatabase
//        val diarys = DiaryDao().diarys
//        val adapter = DiaryAdapter(diarys)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        adapter = DiaryAdapter(diarys)

        mainBinding.recyclerView.layoutManager = layoutManager
        mainBinding.recyclerView.adapter = adapter

        val listener = object : DiaryAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
//                Toast.makeText(this@MainActivity, "${diarys[position]}", Toast.LENGTH_SHORT).show()
                //짧게 아이템을 클릭하면 수정 화면으로 넘어가도록 수정하기
                val intent = Intent(this@MainActivity, UpdateDiaryActivity::class.java)
                intent.putExtra("dto", diarys.get(position))
                startActivityForResult(intent, UPDATE_CODE)
            }
        }
        adapter.setOnItemClickListener(listener)
        val longListener = object : DiaryAdapter.OnItemLongClickListener{
            override fun onItemLongClick(view: View, position: Int){
                AlertDialog.Builder(this@MainActivity).apply{
                    setTitle("일기 삭제")
                    setMessage("정말 삭제하시겠습니까?")
                    setPositiveButton("확인"){dialog, which ->
                        if(deleteDiary(diarys.get(position)) > 0){
                            diarys.clear()
                            diarys.addAll(getAllDiarys())
                            adapter.notifyDataSetChanged()
                        }
                    }
                    setNegativeButton("취소", null)
                    show()
                }
            }
        }
        adapter.setOnItemLongClickListener(longListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //메뉴 만들기
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //메뉴 항목 하나하나
       when(item.itemId) {
           R.id.add_diary -> {//기록 추가하는 화면으로 넘어가도록 수정
                //Toast.makeText(this, "기록 추가", Toast.LENGTH_SHORT).show()
               val intent = Intent(this, AddDiaryActivity::class.java)
               startActivityForResult(intent, ADD_CODE)
           }
           R.id.introduce -> {//개발자 소개 화면으로 넘어가도록 수정
               //Toast.makeText(this, "개발자 소개", Toast.LENGTH_SHORT).show()
               val intent = Intent(this, IntroduceActivity::class.java)
               startActivityForResult(intent, INTRODUCE_CODE)
           }
           R.id.exit -> {//앱 종료하는 코드로 수정
               Toast.makeText(this, "앱종료", Toast.LENGTH_SHORT).show()
               AlertDialog.Builder(this@MainActivity).apply{
                   setTitle("앱 종료")
                   setMessage("앱을 종료하시겠습니까?")
                   setPositiveButton("종료"){dialog, which ->
                       ActivityCompat.finishAffinity(this@MainActivity)
                   }
                   setNegativeButton("취소", null)
                   show()
               }
           }
       }
        return true
        // return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            ADD_CODE -> {
                if(resultCode == RESULT_OK ){
//                    val data = data?.getSerializableExtra("data") as DiaryDto
//                    diarys.add(data)
//                    adapter.notifyDataSetChanged()
                    diarys.clear()
                    diarys.addAll(getAllDiarys())
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "일기가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            UPDATE_CODE -> {
                if(resultCode == RESULT_OK){
                    diarys.clear()
                    diarys.addAll(getAllDiarys())
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "일기가 수정되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("Range")
    fun getAllDiarys() : ArrayList<DiaryDto>{
        val helper = DiaryDBHelper(this)
        val db = helper.readableDatabase
        val cursor = db.query(DiaryDBHelper.TABLE_NAME, null, null, null, null, null, null)

        val diarys = arrayListOf<DiaryDto>()
        with (cursor){
            while (moveToNext()){
                val id = getInt(getColumnIndex(BaseColumns._ID))
                val photo = R.mipmap.happy // 임의의 값
                val title = getString(getColumnIndex(DiaryDBHelper.COL_TITLE))
                val date = getString(getColumnIndex(DiaryDBHelper.COL_DATE))
                val weather = getString(getColumnIndex(DiaryDBHelper.COL_WEATHER))
                val place = getString(getColumnIndex(DiaryDBHelper.COL_PLACE))
                val content =getString(getColumnIndex(DiaryDBHelper.COL_CONTENT))
                val dto = DiaryDto(id, photo, title, date, weather, place, content)
                diarys.add(dto)
            }
        }
        return diarys
    }

    fun deleteDiary(dto : DiaryDto): Int{
        val helper = DiaryDBHelper(this)
        val db = helper.writableDatabase

        val whereClasure = "${BaseColumns._ID}=?"
        val whereArgs = arrayOf(dto.id.toString())
        val result = db.delete(DiaryDBHelper.TABLE_NAME, whereClasure, whereArgs)

        return result
    }
}