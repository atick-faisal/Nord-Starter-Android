package ai.andromeda.nordstarter

import ai.andromeda.nordstarter.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityUi: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityUi = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityUi.root)

//        val intent = Intent(this, MyService::class.java)
//        startService(intent)
    }
}