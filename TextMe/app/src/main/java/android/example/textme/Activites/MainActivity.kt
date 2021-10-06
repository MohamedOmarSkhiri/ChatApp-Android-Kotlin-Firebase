package android.example.textme.Activites
 
import android.example.textme.Fragment.GetUserNumber
import android.example.textme.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, GetUserNumber())
            .commit()
    }
}