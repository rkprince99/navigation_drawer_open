package com.prince.navigationdrawer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import android.graphics.Color
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.prince.navigationdrawer.databinding.ActivityMainBinding
import com.prince.navigationdrawer.ui.MainViewModel
import com.prince.navigationdrawer.ui.SimpleFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        binding.drawerLayout.setScrimColor(Color.TRANSPARENT)
        drawerToggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener { item ->
            val title = viewModel.selectItem(item.itemId)
            showFragment(title)
            binding.navigationView.setCheckedItem(item.itemId)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        binding.drawerLayout.addDrawerListener(object : androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerOpened(drawerView: View) {
                showBlurOverlay(true)
            }

            override fun onDrawerClosed(drawerView: View) {
                showBlurOverlay(false)
            }
        })

        if (savedInstanceState == null) {
            val title = viewModel.selectItem(R.id.nav_home)
            showFragment(title)
            binding.navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showFragment(title: String) {
        val fragment = SimpleFragment.newInstance(title)
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_container, fragment)
            .commit()
    }

    private fun showBlurOverlay(show: Boolean) {
        val overlay = binding.contentBlurOverlay
        val content = binding.contentContainer
        if (show) {
            overlay.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                content.setRenderEffect(RenderEffect.createBlurEffect(20f, 20f, Shader.TileMode.CLAMP))
            }
        } else {
            overlay.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                content.setRenderEffect(null)
            }
        }
    }
}
