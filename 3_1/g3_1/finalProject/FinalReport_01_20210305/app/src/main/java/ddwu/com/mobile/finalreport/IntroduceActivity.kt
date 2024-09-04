package ddwu.com.mobile.finalreport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.finalreport.databinding.ActivityIntroduceBinding

class IntroduceActivity : AppCompatActivity() {
    val TAG = "IntroduceActivity"
    lateinit var binding : ActivityIntroduceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroduceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}