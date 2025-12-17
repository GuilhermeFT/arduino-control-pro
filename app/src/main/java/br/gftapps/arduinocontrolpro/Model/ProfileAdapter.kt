package br.gftapps.arduinocontrolpro.Model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.gftapps.arduinocontrolpro.Controller.Profile
import br.gftapps.arduinocontrolpro.R
import kotlinx.android.synthetic.main.profile_item.view.*

class ProfileAdapter(private  val c: Context, list: ArrayList<Profile>) : ArrayAdapter<Profile>(c, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View = LayoutInflater.from(c).inflate(R.layout.profile_item, null)
        v.tv_title_profile.text = "teste"
        return super.getView(position, convertView, parent)
    }


}