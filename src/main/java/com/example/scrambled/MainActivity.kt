package com.example.scrambled

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.scrambled.databinding.ActivityMainBinding
import java.security.acl.Owner

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private  lateinit var navController: NavController
    private lateinit var AppBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController=navHostFragment.navController
        AppBarConfiguration= AppBarConfiguration(setOf(R.id.gameFragment,R.id.settings))
        setupActionBarWithNavController(navController,AppBarConfiguration)
        //binding.toolbar.setupWithNavController(navController)
       binding.bottomnavbar.setupWithNavController(navController)
        /*val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.pop)
            .setExitAnim(R.anim.pop)
            .setPopEnterAnim(R.anim.pop)
            .setPopExitAnim(R.anim.pop)
            .setPopUpTo(R.id.main_nav,true)
            .build()

        val bottomNavigationView=binding.bottomnavbar
        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.settings -> {
                    navController.navigate(R.id.settings,null,options)
                }
                R.id.gameFragment -> {
                    navController.navigate(R.id.gameFragment,null,options)
                }
                R.id.themeFragment -> {
                    navController.navigate(R.id.themeFragment,null,options)
                }
            }
            true
        }
        binding.bottomnavbar.setOnItemReselectedListener { return@setOnItemReselectedListener }*/

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.answers) {

                binding.bottomnavbar.visibility = View.GONE
            }
            else{binding.bottomnavbar.visibility = View.VISIBLE}
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(AppBarConfiguration) || super.onSupportNavigateUp()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return item.onNavDestinationSelected(navController) || return super.onOptionsItemSelected(item)
    }

}