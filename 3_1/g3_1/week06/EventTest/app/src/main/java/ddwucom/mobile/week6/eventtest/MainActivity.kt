package ddwucom.mobile.week6.eventtest

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import ddwucom.mobile.week6.eventtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"


    val mainBinding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

//        mainBinding.mainLayout.setOnLongClickListener{
//            Toast.makeText(this@MainActivity, "롱클릭", Toast.LENGTH_SHORT).show()
//            true
//        }
//
//        val myClick = object: View.OnClickListener{
//            override fun onClick(p0: View?) {
//                Toast.makeText(this@MainActivity, "익명 리스너 인터페이스 구현", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//        mainBinding.myButton.setOnClickListener(myClick)


        mainBinding.myButton.setOnClickListener{
            Toast.makeText(
                this@MainActivity,"익명 리스너 인터페이스 구현", Toast.LENGTH_SHORT
            ).show()

            Log.d(TAG, "익명 리스너 로그 출력")
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(mainBinding.root)
//
//        val myClick = MyClick(this)
//
//        mainBinding.myButton.setOnClickListener(myClick)
//    }
//
//    class MyClick(val context: Context): View.OnClickListener{
//        override fun onClick(p0: View?) {
//            Toast.makeText(context, "리스너 인터페이스 구현클래스", Toast.LENGTH_SHORT).show()
//        }
//    }
}