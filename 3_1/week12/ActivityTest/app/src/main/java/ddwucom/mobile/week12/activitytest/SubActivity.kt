package ddwucom.mobile.week12.activitytest

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ddwucom.mobile.week12.activitytest.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity(){
    val subBinding by lazy{
        ActivitySubBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(subBinding.root)
    }
}