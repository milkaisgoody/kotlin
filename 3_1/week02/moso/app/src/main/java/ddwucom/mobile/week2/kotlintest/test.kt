package ddwucom.mobile.week2.kotlintest

fun main(){

    val scores = IntArray(5){readLine()!!.toInt()}
    var total : Int = 0
    for(score in scores){
        total += score
    }

    println("평균: ${total/5} 총합: $total")
}