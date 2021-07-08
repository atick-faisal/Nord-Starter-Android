package ai.andromeda.nordstarter

import ai.andromeda.nordstarter.databinding.ActivityMainBinding
import ai.andromeda.nordstarter.utils.LOG_TAG
import ai.andromeda.nordstarter.utils.Resource
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(root)
            setSupportActionBar(topAppBar)
            navController = (
                    supportFragmentManager
                        .findFragmentById(navHostFragment.id)
                            as NavHostFragment
                    ).navController

            setupWithNavController(navView, navController)
            setupActionBarWithNavController(navController, root)
        }

        viewModel.items.observe(this, { result ->
            when (result) {
                is Resource.Loading -> Log.i(LOG_TAG, "LOADING ... ")
                is Resource.Success -> Log.i(LOG_TAG, "SUCCESS ... " + result.data)
                is Resource.Error -> Log.i(LOG_TAG, "ERROR ... " + result.error)
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(navController, binding.root)
    }
}