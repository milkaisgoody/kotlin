package ddwu.com.mobile.finalreport

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.finalreport.databinding.ActivityUpdateBinding

class UpdateDiaryActivity : AppCompatActivity() {
    val updateBinding by lazy{
        ActivityUpdateBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(updateBinding.root)

        val dto = intent.getSerializableExtra("dto") as DiaryDto

        updateBinding.updateTitle.setText(dto.title)
        updateBinding.updateDate.setText(dto.date)
        updateBinding.updateWeather.setText(dto.weather)
        updateBinding.updatePlace.setText(dto.place)
        updateBinding.updateContent.setText(dto.content)

        updateBinding.updateBtn.setOnClickListener{
            dto.title = updateBinding.updateTitle.text.toString()
            dto.date = updateBinding.updateDate.text.toString()
            dto.weather = updateBinding.updateWeather.text.toString()
            dto.place = updateBinding.updatePlace.text.toString()
            dto.content = updateBinding.updateContent.text.toString()

            if(dto.title.isNotEmpty() && dto.date.isNotEmpty() && dto.weather.isNotEmpty() && dto.place.isNotEmpty() && dto.content.isNotEmpty()){
                if (updateDiary(dto) > 0) {
                    setResult(RESULT_OK)
                }
                finish()
            }else{
                Toast.makeText(this, "입력 값을 모두 채워주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        updateBinding.cancelBtn.setOnClickListener{
            setResult(RESULT_CANCELED)
            finish()
        }

    }

    fun updateDiary(dto: DiaryDto): Int {
        val helper = DiaryDBHelper(this)
        val db = helper.writableDatabase

        val updateValue = ContentValues()
        updateValue.put(DiaryDBHelper.COL_TITLE, dto.title)
        updateValue.put(DiaryDBHelper.COL_DATE, dto.date)
        updateValue.put(DiaryDBHelper.COL_WEATHER, dto.weather)
        updateValue.put(DiaryDBHelper.COL_PLACE, dto.place)
        updateValue.put(DiaryDBHelper.COL_CONTENT, dto.content)

        val whereClause = "${BaseColumns._ID}=?"
        val whereArgs = arrayOf(dto.id.toString())

        val resultCount =  db.update(DiaryDBHelper.TABLE_NAME,
            updateValue, whereClause, whereArgs)

        helper.close()
        return resultCount
    }

}