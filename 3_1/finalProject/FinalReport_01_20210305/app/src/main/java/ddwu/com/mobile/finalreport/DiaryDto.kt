package ddwu.com.mobile.finalreport

import java.io.Serializable

data class DiaryDto (val id: Int, var photo: Int, var title: String, var date: String, var weather: String, var place: String, var content: String) : java.io.Serializable{
    //var 나중에 값 바꿀 수 있음
    override fun toString() = "$id ($photo) $title $date $weather"

}