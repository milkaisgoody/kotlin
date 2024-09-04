package ddwucom.mobile.week3.kotlintest02

fun funName(num:Int):String{
    println(num)
    return "return"
}

fun main(){
//    funName(num1)
    val lambda = {num : Int ->
        println(num)
        "return"
    }
    println(lambda(10))
}


