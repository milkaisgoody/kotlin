package ddwucom.mobile.week7.dialogpractice

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwucom.mobile.week7.dialogpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val mainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

//        val onOkclick = DialogInterface.OnClickListener{
//            dialogInterface: DialogInterface?, whichButton: Int ->
//                Toast.makeText(this@MainActivity, "확인!", Toast.LENGTH_LONG).show()
//        }
        val onOkClick = object:DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, whichButton: Int) {
                Toast.makeText(this@MainActivity, "확인!", Toast.LENGTH_SHORT).show()
            }
        }

        mainBinding.button.setOnClickListener{
            val builder : AlertDialog.Builder = AlertDialog.Builder(this).apply{
                title = "대화상자 제목"
                setTitle("대화상자 메시지")
                setIcon(R.mipmap.ic_launcher_round)
                setPositiveButton("확인", null)
                setNeutralButton("대기"){p0: DialogInterface?, whichButton: Int -> Toast.makeText(this@MainActivity, "대기!", Toast.LENGTH_SHORT).show()}
                setNegativeButton("취소", null)
                setCancelable(false)
            }
            builder.show()
        }


    }
}