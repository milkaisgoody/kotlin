package ddwucom.mobile.week3.kotlintest02

//data class Subject(val title:String, var credit:Int)

fun main(){
    val subject1 = Subject("mobile",3)
    val subject2 = Subject("mobile", 3)
    println("${subject1.equals(subject2)}")
}