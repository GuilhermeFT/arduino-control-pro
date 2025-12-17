package br.gftapps.arduinocontrolpro.SubView

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import br.gftapps.arduinocontrolpro.Model.BotaoPowerSQL
import br.gftapps.arduinocontrolpro.ManageSettings
import br.gftapps.arduinocontrolpro.MyInterface
import br.gftapps.arduinocontrolpro.R
import br.gftapps.arduinocontrolpro.SocketConnection
import br.gftapps.arduinocontrolpro.View.MainFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [View1Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [View1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class View1Fragment : Fragment(), MyInterface {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var bt: ImageButton
    private lateinit var inst: MainFragment
    fun Parameters(mf: MainFragment) {
        inst = mf
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_view1, container, false)
        bt = v.findViewById<ImageButton>(R.id.imb_power)
        var data = BotaoPowerSQL(activity!!.applicationContext)
            .pegarComando().split(";")
        var con: List<String>? = null
        if (activity!!.baseContext.getFileStreamPath("addressConf.cfg").exists()) {
            con = ManageSettings().readFile("addressConf.cfg", activity!!.applicationContext).split(";")
        }
        var i = 0

        bt.setOnClickListener {
            bt.isEnabled = false
            if (inst.getSatus()) {
                if (i == 0) {
                    i = 1
                    bt.setImageResource(R.drawable.ic_power_on)
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], data[0])
                } else {
                    i = 0
                    bt.setImageResource(R.drawable.ic_power)
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], data[1])
                }
            } else {
                bt.isEnabled = true
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }

        }
        return v
    }

    override fun VerifySocket(b: Boolean) {
        if (!b) {
            bt.setImageResource(R.drawable.ic_power)
            inst.atualizeStatus()
            Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            bt.isEnabled = true
        } else {
            bt.isEnabled = true
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment View1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            View1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
