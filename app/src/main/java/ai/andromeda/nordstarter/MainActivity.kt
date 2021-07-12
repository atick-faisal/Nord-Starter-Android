package ai.andromeda.nordstarter

import ai.andromeda.nordstarter.databinding.ActivityMainBinding
import ai.andromeda.nordstarter.extensions.hide
import ai.andromeda.nordstarter.extensions.show
import ai.andromeda.nordstarter.services.background.DefaultFirebaseMessagingService
import ai.andromeda.nordstarter.utils.DEFAULT_FCM_SUBSCRIPTION_TOPIC
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        DefaultFirebaseMessagingService.subscribeToTopic(DEFAULT_FCM_SUBSCRIPTION_TOPIC) {
            Timber.d("successfully subscribed to firebase topic ... ")
        }

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
            controlActionBarVisibility()
        }

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

    private fun controlActionBarVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.authenticationFragment) {
                binding.apply {
                    topAppBar.hide()
                    mainDrawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
                }
            } else {
                binding.apply {
                    topAppBar.show()
                    mainDrawerLayout.setDrawerLockMode(LOCK_MODE_UNLOCKED)
                }
            }
        }
    }
}