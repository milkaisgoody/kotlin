package ddwucom.mobile.week2.kotlintest

import java.util.Random
fun main(){
    val lotto = mutableSetOf<Int>()
    val random = Random()
    while(lotto.size != 6){

        val newNum = random.nextInt(45) + 1
        lotto.add(newNum)
    }
    println("로또 번호: $lotto")
}