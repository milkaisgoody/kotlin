package ddwucom.mobile.week3.kotlintest02
//3주차 실습01
data class RegDate(val year:Int, val month:Int, val day:Int){
    override fun equals(other:Any?):Boolean{
        return this.year == (other as RegDate).year //년도만 같으면 같게 취급 as어쩌고 다운캐스팅..?하는거
    }
    override fun toString():String{
        return "${year}년 ${month}월 ${day}일"
    }
}

fun main(){
    val reg01 = RegDate(2023, 2 ,1)
    val reg02 = RegDate(2023, 3 ,1)

    println(reg01.equals(reg02))
}
