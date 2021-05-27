package com.lihan.vocabularycard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.lihan.vocabularycard.R
import com.lihan.vocabularycard.model.SHAREDPREFERENCES_NOWLIST
import com.lihan.vocabularycard.model.SHAREDPREFERENCES_VOCABULARYLIST
import com.lihan.vocabularycard.databinding.ActivityMainBinding
import com.lihan.vocabularycard.model.saveVocabularyListSharedPreferences

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.mainToolbar)
        initData()
        binding.apply {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val navController = findNavController(R.id.nav_host_fragment)



        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.allFragment,
            R.id.settingsFragment,
            R.id.adFragment,
            R.id.aboutFragment
        ),mainDrawerLayout)
//            supportActionBar?.displayOptions = 0
            setupActionBarWithNavController(navController,appBarConfiguration)
            appBarMain.mainToolbar.setNavigationIcon(R.drawable.ic_drawer_menu)
            appBarMain.mainToolbar.setNavigationOnClickListener {
                if (mainDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mainDrawerLayout.close()
                }else{
                    mainDrawerLayout.open()
                }
            }
            mainNavigationView.itemIconTintList = null;
            mainNavigationView.setupWithNavController(navController)


        }



    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    fun initData(){
        saveVocabularyListSharedPreferences(arrayListOf(), SHAREDPREFERENCES_VOCABULARYLIST)
        saveVocabularyListSharedPreferences(arrayListOf(), SHAREDPREFERENCES_NOWLIST)
    }
}