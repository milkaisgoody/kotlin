package ddwu.com.mobile.finalreport

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log

class DiaryDBHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
    val TAG = "DiaryDBHelper"

    companion object{
        const val DB_NAME = "diary_db"
        const val TABLE_NAME = "diary_table"
        const val COL_TITLE = "title"
        const val COL_DATE = "date"
        const val COL_WEATHER = "weather"
        const val COL_PLACE = "place"
        const val COL_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE ${TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "${COL_TITLE} TEXT, ${COL_DATE} TEXT, ${COL_WEATHER} TEXT, ${COL_PLACE} TEXT, ${COL_CONTENT} TEXT)"
        Log.d(TAG, CREATE_TABLE)
        db?.execSQL(CREATE_TABLE)

        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, '오늘은 종강한 날', '2024/06/18', '맑음', '서울특별시 성북구', '오늘은 종강을 해서 기분이 좋았다.')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, '동생과 싸운 날', '2024/06/19', '흐림', '김포시 감정동', '오늘은 동생에게 화를 냈다.')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, '성적이 나온 날', '2024/06/20', '맑음', '김포시 감정동', '오늘은 성적이 나왔는데 평균점수보다 낮아서 슬펐다.')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, ' 방에서 벌레를 발견했다.', '2024/06/21', '흐림', '김포시 감정동', '오늘은 집에 벌레가 나와서 매우 놀랐다.')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, '미술관에 다녀온 날', '2024/06/22', '비', '서울특별시 종로구', '오늘은 미술관에 다녀왔다. 다양한 작품들을 탐구했다.')")
    } //테이블 만듦

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS ${TABLE_NAME}"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }
}