package ai.andromeda.nordstarter

import ai.andromeda.nordstarter.databinding.ActivityMainBinding
import ai.andromeda.nordstarter.extensions.hide
import ai.andromeda.nordstarter.extensions.show
import ai.andromeda.nordstarter.services.background.DefaultFirebaseMessagingService
import ai.andromeda.nordstarter.utils.DEFAULT_FCM_SUBSCRIPTION_TOPIC
import ai.andromeda.nordstarter.utils.RC_SOME_PERMISSIONS
import android.Manifest
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
import kotlinx.coroutines.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),
    EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks {

    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    companion object {
        val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        DefaultFirebaseMessagingService.subscribeToTopic(DEFAULT_FCM_SUBSCRIPTION_TOPIC) {
            Timber.d("successfully subscribed to firebase topic ... ")
        }

        binding?.apply {
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

        askForPermissions()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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
            if (
                destination.id == R.id.authenticationFragment ||
                destination.id == R.id.settingsFragment
            ) {
                binding?.apply {
                    topAppBar.hide()
                    mainDrawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
                }
            } else {
                binding?.apply {
                    topAppBar.show()
                    mainDrawerLayout.setDrawerLockMode(LOCK_MODE_UNLOCKED)
                }
            }
        }
    }

    // ... TODO: Handle permissions here

    private fun hasPermissions(): Boolean {
        return EasyPermissions.hasPermissions(this, *PERMISSIONS)
    }

    private fun askForPermissions() {
        if (hasPermissions()) {
            Timber.d("all permissions have been granted ... ")
        } else {
            EasyPermissions.requestPermissions(
                this@MainActivity,
                getString(R.string.permission_rationale),
                RC_SOME_PERMISSIONS,
                *PERMISSIONS
            )
        }
    }

    @AfterPermissionGranted(RC_SOME_PERMISSIONS)
    private fun someMethodThatRequiresPermission() {
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(
            requestCode, permissions, grantResults, this
        )
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Timber.d("[$requestCode] ${perms.size} permission(s) has been granted ... ")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Timber.d("[$requestCode] ${perms.size} permission(s) has been denied ... ")
    }

    override fun onRationaleAccepted(requestCode: Int) {
        Timber.d("[$requestCode] user agreed to the rationale ... ")
    }

    override fun onRationaleDenied(requestCode: Int) {
        Timber.d("[$requestCode] user denied the rationale ... ")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}