package ai.andromeda.nordstarter

import ai.andromeda.nordstarter.database.room.AppDatabase
import ai.andromeda.nordstarter.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var appDatabase: AppDatabase
    private lateinit var mainActivityUi: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityUi = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityUi.root)
    }
}