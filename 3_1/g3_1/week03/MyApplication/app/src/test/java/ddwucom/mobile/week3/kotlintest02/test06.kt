package ddwucom.mobile.week3.kotlintest02

class MyClass constructor(var dept:String){
    var name : String = "test"

    init{
        this.dept = dept
    }

    fun getDept(){
        println(dept)
    }
}

fun main(){
//    val MyObject : MyClass = MyClass("computer")
//    myObject.getDept()
}