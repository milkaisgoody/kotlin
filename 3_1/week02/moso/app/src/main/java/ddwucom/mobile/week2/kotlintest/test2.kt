package ddwucom.mobile.week2.kotlintest

fun main(){
    println("키를 입력하시오. (cm 단위): " )
    val height = readLine()!!.toInt()
    println("몸무게를 입력하시오: (kg 단위): ")
    val weight = readLine()!!.toInt()

    val heightwithM = height/100.0
    var bmi = weight / (heightwithM * heightwithM)

    val result = when (bmi){
        in 0.0 .. 18.5 -> "저체중"
        in 18.5 .. 23.0 -> "정상"
        in 23.0 ..  25.0 -> "과체중"
        else -> "비만"
    }
    println("키 $height cm, 몸무게 $weight kg의 BMI 지수는 $bmi 이며 $result 입니다.")
}