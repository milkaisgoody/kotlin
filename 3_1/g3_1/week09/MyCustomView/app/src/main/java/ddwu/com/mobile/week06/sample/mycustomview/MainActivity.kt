package ddwu.com.mobile.week06.sample.mycustomview

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.week06.sample.mycustomview.databinding.ActivityMainBinding
//9주차 실습과제02
class MainActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    var contextSelectd = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        registerForContextMenu(binding.myCustomView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.smallersize -> {
                //Toast.makeText(this, "smaller", Toast.LENGTH_SHORT).show()
                binding.myCustomView.radius -= 10
            }
            R.id.biggersize -> {
                //Toast.makeText(this, "bigger", Toast.LENGTH_SHORT).show()
                binding.myCustomView.radius += 10
            }
        }
        binding.myCustomView.invalidate()

        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        when(v?.id){
            R.id.myCustomView -> {
                menuInflater.inflate(R.menu.context_menu, menu)

                when (contextSelectd){
                    0 -> {
                        menu?.findItem(R.id.redcolor)?.setChecked(true)
                    }
                    1 -> {
                        menu?.findItem(R.id.greencolor)?.setChecked(true)
                    }
                    2 -> {
                        menu?.findItem(R.id.bluecolor)?.setChecked(true)
                    }
                }
            }

        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.redcolor -> {
                //Toast.makeText(this, "smaller", Toast.LENGTH_SHORT).show()
                binding.myCustomView.paintColor = Color.RED
                contextSelectd = 0
            }
            R.id.greencolor -> {
                //Toast.makeText(this, "bigger", Toast.LENGTH_SHORT).show()
                binding.myCustomView.paintColor = Color.GREEN
                contextSelectd = 1
            }
            R.id.bluecolor -> {
                //Toast.makeText(this, "bigger", Toast.LENGTH_SHORT).show()
                binding.myCustomView.paintColor = Color.BLUE
                contextSelectd = 2
            }
        }
        binding.myCustomView.invalidate()

        return true
    }
}