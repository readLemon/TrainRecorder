package com.example.train.view.activity

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.train.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override val contentVieId: Int
        get() = R.layout.activity_main

    private var currentNavController: LiveData<NavController>? = null

    private val bottomListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nav_main_graph -> {
                showToast("nav_main_graph")
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_test_second -> {
                showToast("nav_test_second")
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_test_third -> {
                showToast("nav_test_third")
                return@OnNavigationItemSelectedListener true
            }
        }

        true
    }


    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.nav_train,
            R.navigation.nav_test_second,
            R.navigation.nav_test_third
        )
        val controller = bnv_main_activity.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_main_activity,
            intent = intent,
            listener = bottomListener
        )

//        //因为暂时没有用到bar。所以不做设置
//        controller.observe(this, Observer {
//            //            setupActionBarWithNavController(it)
//        })
        currentNavController = controller
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onBackPressed() {
        if (currentNavController?.value?.popBackStack() != true) {
            super.onBackPressed()
        }
    }


}
