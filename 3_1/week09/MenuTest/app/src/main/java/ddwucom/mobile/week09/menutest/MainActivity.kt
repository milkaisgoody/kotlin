package ddwucom.mobile.week09.menutest

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ddwucom.mobile.week09.menutest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    var selected : Int = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        registerForContextMenu(binding.tvText)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.menu_main, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, "Context", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.food_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        when(selected){
            3 -> menu?.findItem(R.id.subitem03)?.setChecked(true)
            4 -> menu?.findItem(R.id.subitem04)?.setChecked(true)
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.subitem01 ->
                Toast.makeText(this, "SubItem01", Toast.LENGTH_SHORT).show()
            R.id.subitem02 ->
                Toast.makeText(this, "SubItem02", Toast.LENGTH_SHORT).show()
            R.id.subitem03 -> {
                Toast.makeText(this, "SubItem03", Toast.LENGTH_SHORT).show()
                selected = 3
            }
            R.id.subitem04 -> {
                Toast.makeText(this, "SubItem04", Toast.LENGTH_SHORT).show()
                selected = 4
            }

        }

        return true
    }



//    fun onMenuClick(item: MenuItem){
//
//    }

}