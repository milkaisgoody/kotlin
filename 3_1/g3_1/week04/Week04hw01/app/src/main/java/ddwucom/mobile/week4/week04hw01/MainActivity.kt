package ddwucom.mobile.week4.week04hw01

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
    }

    fun onClick(view : View){
        val etName : EditText = findViewById(R.id.etName)
        val etPhone : EditText = findViewById(R.id.etPhone)

        val nameEdit : String = etName.text.toString()
        val phoneEdit : String = etPhone.text.toString()
        val printText : String = "안녕하세요, 저는 " + nameEdit + "입니다.\n" + "전화번호는 " + phoneEdit + "입니다."

        Toast.makeText(this, printText, Toast.LENGTH_SHORT).show()
    }

    fun onClick2(view : View){
        finish()
    }
}