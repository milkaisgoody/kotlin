package ddwucom.mobile.week3.kotlintest02

fun main(){
    var str:String? = null // ?을 뒤에 붙여주는 순간 null을 담을 수 있다.
    println(str?.length)

    str = "test"
    println(str?.length)
}
