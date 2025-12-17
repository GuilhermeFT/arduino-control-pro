package br.gftapps.arduinocontrolpro.View

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import br.gftapps.arduinocontrolpro.ManageSettings
import br.gftapps.arduinocontrolpro.R
import br.gftapps.arduinocontrolpro.SubView.View1Fragment
import br.gftapps.arduinocontrolpro.SubView.View2Fragment

class MaindecActivity : AppCompatActivity(), MainFragment.OnFragmentInteractionListener,
    SettingsFragment.OnFragmentInteractionListener,
    View1Fragment.OnFragmentInteractionListener,
    View2Fragment.OnFragmentInteractionListener {

    lateinit var main: MainFragment
    lateinit var settings: SettingsFragment
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setFragment(MainFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {

                setFragment(settings)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dec)


        main = MainFragment()
        settings = SettingsFragment()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        if (!baseContext.getFileStreamPath("displayConf.cfg").exists()) {
            ManageSettings().saveFile("displayConf.cfg", "0;", applicationContext)
        }
        setFragment(MainFragment())
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    fun setFragment(fragment: Fragment) {
        if (!fragment.isVisible) {
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cfg, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_settings) {

            return true
        }
        return false
    }
    override fun onFragmentInteraction(uri: Uri) {
    }


}
