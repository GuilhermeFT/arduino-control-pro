package br.gftapps.arduinocontrolpro.SubView

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import br.gftapps.arduinocontrolpro.ManageSettings
import br.gftapps.arduinocontrolpro.Model.BotaoFuncoesSQL
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
 * [View2Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [View2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class View2Fragment : Fragment(), MyInterface {


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

    private lateinit var inst: MainFragment
    fun Parameters(mf: MainFragment) {
        inst = mf
    }
    private lateinit var buttons: List<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_view2, container, false)
        buttons = listOf(
            v.findViewById(R.id.btn_f1),
            v.findViewById(R.id.btn_f2),
            v.findViewById(R.id.btn_f3),
            v.findViewById(R.id.btn_f4),
            v.findViewById(R.id.btn_f5),
            v.findViewById(R.id.btn_f6),
            v.findViewById(R.id.btn_f7),
            v.findViewById(R.id.btn_f8),
            v.findViewById(R.id.btn_f9),
            v.findViewById(R.id.btn_f10),
            v.findViewById(R.id.btn_f11),
            v.findViewById(R.id.btn_f12)
        )

        var con: List<String>? = null
        if (activity!!.baseContext.getFileStreamPath("addressConf.cfg").exists()) {
            con = ManageSettings().readFile("addressConf.cfg", activity!!.applicationContext).split(";")
        }

        var command = BotaoFuncoesSQL(activity!!.applicationContext)
            .pegarComando().split(";")

        buttons[0].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[0] != "") and (command[0] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[0])
                } else {
                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }
        buttons[1].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[1] != "") and (command[1] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[1])
                } else {
                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }
        buttons[2].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[2] != "") and (command[2] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[2])
                } else {
                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }
        buttons[3].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[3] != "") and (command[3] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[3])
                } else {
                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }
        buttons[4].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[4] != "") and (command[4] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[4])
                } else {
                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }
        buttons[5].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[5] != "") and (command[5] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[5])
                } else {
                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }
        buttons[6].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[6] != "") and (command[6] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[6])
                } else {
                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }
        buttons[7].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[7] != "") and (command[7] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[7])
                } else {
                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }
        buttons[8].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[8] != "") and (command[8] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[8])
                } else {
                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {

                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }
        buttons[9].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[9] != "") and (command[9] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[9])
                } else {

                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }
        buttons[10].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[10] != "") and (command[10] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[10])
                } else {
                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }
        buttons[11].setOnClickListener {
            if (inst.getSatus()) {
                if ((command[11] != "") and (command[11] != "null")) {
                    var sc = SocketConnection()
                    sc.Parameters(this)
                    sc.execute("SendMessage", con!![0], con[1], command[11])
                } else {
                    Toast.makeText(activity!!.applicationContext, getString(R.string.a5), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
            }
        }

        var i = 0
        while (i < 12) {
            buttons[i].text = BotaoFuncoesSQL(
                activity!!.applicationContext
            ).pegarNomes().split(";")[i]
            i += 1
        }

        return v
    }

    override fun VerifySocket(b: Boolean) {
       

        if (!b) {
            inst.atualizeStatus()
            Toast.makeText(context, getString(R.string.a4), Toast.LENGTH_LONG).show()
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
         * @return A new instance of fragment View2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            View2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
