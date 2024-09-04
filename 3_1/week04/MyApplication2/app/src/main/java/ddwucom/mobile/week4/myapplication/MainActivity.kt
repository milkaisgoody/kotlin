package ddwucom.mobile.week4.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myText : TextView = findViewById(R.id.myText) // text를 찾아서 myText라는 변수에 저장
        myText.text = "Mobile!!!"
        myText.setTextColor(Color.GREEN)
    }

    fun onClick(view : View){
        val myEdit : EditText = findViewById(R.id.myEdit)
        val myText : TextView = findViewById(R.id.myText)
        myText.text = myEdit.text

        myEdit.setText("copied")

        Toast.makeText(this, "버튼을 클릭함", Toast.LENGTH_SHORT).show()
    }
}