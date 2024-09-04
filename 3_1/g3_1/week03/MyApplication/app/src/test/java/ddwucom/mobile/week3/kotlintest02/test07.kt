package ddwucom.mobile.week3.kotlintest02

data class Subject(val title:String, var credit:Int)

fun main(){
    println(Subject("mobile", 3))
}