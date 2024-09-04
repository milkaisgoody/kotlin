package ddwucom.mobile.week5.layoutest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import ddwucom.mobile.week5.layoutest.databinding.LinearLayoutBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.linear_layout) //새로운 레이아웃을 추가하고 싶을때 layout. 뒤 부분을 바꿔주면됨
    }
    val linearBinding : LinearLayoutBinding
        = LinearLayoutBinding.inflate(layoutInflater)
    setContentView(linearBinding.root)


//    val mainBinding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
//    setContentView(mainBinding.root)

//    val button : Button = findViewById(R.id.button2)
//    button.setOnClickListener{
//        val layout : LinearLayout = findViewById(R.id.linear_layout)
//        layout.orientation = LinearLayout.HORIZONTAL
//    }

//    fun onClick(view: View){
    //val layout : LinearLayout = findViewById(R.id.linear_layout)
    //layout.orientation = LinearLayout.HORIZONTAL
//    }
}