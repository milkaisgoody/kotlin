package ddwu.com.mobile.finalreport

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.finalreport.databinding.ActivityAddBinding

class AddDiaryActivity : AppCompatActivity() {
//    val addBinding by lazy{
//        ActivityAddBinding.inflate(layoutInflater)
//    }
    val TAG = "AddDiaryActivity"
    lateinit var  addBinding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(addBinding.root)

        addBinding.addBtn.setOnClickListener{
            //val photo = 0 //R.mipmap.happy 일단 임의의 값 나중에 수정
            val title = addBinding.addTitle.text.toString()
            val date = addBinding.addDate.text.toString()
            val weather = addBinding.addWeather.text.toString()

            val place = addBinding.addPlace.text.toString()
            val content = addBinding.addContent.text.toString()

            if(title.isNotEmpty() && date.isNotEmpty() && weather.isNotEmpty() && place.isNotEmpty() && content.isNotEmpty()){
                val photo = R.mipmap.happy
                val dto = DiaryDto(0, photo, title, date, weather, place, content)
                if(addDiary(dto) > 0){
                    setResult(Activity.RESULT_OK)
                }
                finish()
//                val resultIntent = Intent()
//                resultIntent.putExtra("data", dto)
//                setResult(Activity.RESULT_OK, resultIntent)
//                finish()
            }else{
                Toast.makeText(this, "입력 값을 모두 채워주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        addBinding.cancelBtn.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

    }
        fun addDiary(newDto : DiaryDto) : Long{
        val helper = DiaryDBHelper(this)
        val db = helper.writableDatabase

        val newRow = ContentValues()
        newRow.put(DiaryDBHelper.COL_TITLE, newDto.title)
        newRow.put(DiaryDBHelper.COL_DATE, newDto.date)
        newRow.put(DiaryDBHelper.COL_WEATHER, newDto.weather)
        newRow.put(DiaryDBHelper.COL_PLACE, newDto.place)
        newRow.put(DiaryDBHelper.COL_CONTENT, newDto.content)

        val result = db.insert(DiaryDBHelper.TABLE_NAME, null, newRow)
        helper.close()
        return result //오류시 일단 0
    }
}