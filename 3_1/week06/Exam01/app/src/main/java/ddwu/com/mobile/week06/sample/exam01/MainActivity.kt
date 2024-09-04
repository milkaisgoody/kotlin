package ddwu.com.mobile.week06.sample.exam01

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwu.com.mobile.week06.sample.exam01.databinding.ActivityMainBinding
import ddwu.com.mobile.week06.sample.exam01.databinding.DialogInterfaceBinding

class MainActivity : AppCompatActivity() {

    /*viewBinding 관련 부분 작성할 것*/

    val mainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        val txt = mainBinding.etInput.text.toString()
        mainBinding.btnOutput.setOnClickListener{
            val builder = AlertDialog.Builder(this).apply{
                setTitle("메시지 출력")
                setMessage("${txt}를 입력하시겠습니까?")
//                setPositiveButton("확인", null){
//                        dialogInterface: DialogInterface?, i : Int-> mainBinding.tvDisplay.text = mainBinding.etInput.text
//                } //오류남
                setNegativeButton("취소", null)
            }
            builder.show()
        }
    }
}