package ai.andromeda.nordstarter

import ai.andromeda.nordstarter.databinding.ActivityMainBinding
import ai.andromeda.nordstarter.utils.LOG_TAG
import ai.andromeda.nordstarter.utils.Resource
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

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
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.homeFragment
                ),
                root
            )
            setupWithNavController(navView, navController)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }

        viewModel.items.observe(this, { result ->
            when (result) {
                is Resource.Loading -> Log.i(LOG_TAG, "LOADING ... ")
                is Resource.Success -> Log.i(LOG_TAG, "SUCCESS ... " + result.data)
                is Resource.Error -> Log.i(LOG_TAG, "ERROR ... " + result.error)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.default_toobar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(navController, appBarConfiguration) ||
                super.onSupportNavigateUp()
    }
}