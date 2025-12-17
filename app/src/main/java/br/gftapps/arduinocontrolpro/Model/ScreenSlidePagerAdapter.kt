package br.gftapps.arduinocontrolpro.Model

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import br.gftapps.arduinocontrolpro.ManageSettings
import br.gftapps.arduinocontrolpro.SubView.View1Fragment
import br.gftapps.arduinocontrolpro.SubView.View2Fragment
import br.gftapps.arduinocontrolpro.View.MainFragment

class ScreenSlidePagerAdapter(c: Context, fm: FragmentManager, mf: MainFragment) : FragmentStatePagerAdapter(fm) {
    private val context = c
    private val mf = mf

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                var v = View1Fragment()
                v.Parameters(mf)
                return v
            }
            1 -> {
                var v = View2Fragment()
                v.Parameters(mf)
                return v
            }
        }
        return null!!
    }

    override fun getCount(): Int {
        return 2
    }


}