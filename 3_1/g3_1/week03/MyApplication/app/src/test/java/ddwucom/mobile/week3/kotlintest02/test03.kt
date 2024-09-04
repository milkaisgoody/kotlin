package ddwucom.mobile.week3.kotlintest02

fun main(){
    val nameFunc : () -> Unit = {
        println ("SomSom!")
    }

    val subjectFunc : () -> Unit ={
        val subjectName = "Mobile software"
        println(subjectName)
    }

    fun higherOrderFunc (argFunc : () ->Unit): (String)->Unit{
        println("Dept: Computer")
        argFunc()
        return{ grade:String -> println(grade)}
    }

    //higherOrderFunc(nameFunc)
    higherOrderFunc(subjectFunc)
}