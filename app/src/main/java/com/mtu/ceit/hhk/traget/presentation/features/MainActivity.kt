package com.mtu.ceit.hhk.traget.presentation.features


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mtu.ceit.hhk.traget.R


import com.mtu.ceit.hhk.traget.databinding.ActivityMainBinding
import com.mtu.ceit.hhk.traget.presentation.features.setting.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    val settingVM: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_Traget)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeIsNight()
        setUpBottomView()
        observeFragmentDestination()


        supportActionBar?.apply {

            setDisplayUseLogoEnabled(false)
            //setDisplayHomeAsUpEnabled(true)
            //setHomeAsUpIndicator(R.drawable.ic_analytics)
           // setLogo(R.drawable.ic_money)
        }

    }

        private fun setUpBottomView() {
            val nh = supportFragmentManager.findFragmentById(R.id.main_Container) as NavHostFragment
            val con = nh.navController

            desListen(con)

            NavigationUI.setupWithNavController(binding.mainBottom, con)
        }

        private fun observeIsNight() {
            settingVM.isNight.observe(this) {
                if (it)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        private fun observeFragmentDestination() {

            settingVM.bottomNavigationVisibility.observe(this) {
                binding.mainBottom.visibility = it
            }

        }


        private fun desListen(controller: NavController) {

            controller.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.addEditClientFragment -> settingVM.hideBottonNavigation()
                    else -> settingVM.showBottomNavigation()
                }
            }

        }

}