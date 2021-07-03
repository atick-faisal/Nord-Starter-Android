package ai.andromeda.nordstarter

import ai.andromeda.nordstarter.databinding.ActivityMainBinding
import ai.andromeda.nordstarter.utils.LOG_TAG
import ai.andromeda.nordstarter.utils.Resource
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityUi: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityUi = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityUi.root)

        viewModel.items.observe(this, { result ->
            when(result) {
                is Resource.Loading -> Log.i(LOG_TAG, "LOADING ... ")
                is Resource.Success -> Log.i(LOG_TAG, "SUCCESS ... " + result.data)
                is Resource.Error -> Log.i(LOG_TAG, "ERROR ... " + result.error)
            }
        })

    }
}