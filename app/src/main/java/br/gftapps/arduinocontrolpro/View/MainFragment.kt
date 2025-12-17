package br.gftapps.arduinocontrolpro.View

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import br.gftapps.arduinocontrolpro.Model.ScreenSlidePagerAdapter
import br.gftapps.arduinocontrolpro.ManageSettings
import br.gftapps.arduinocontrolpro.MyInterface
import br.gftapps.arduinocontrolpro.R
import br.gftapps.arduinocontrolpro.SocketConnection
import com.viewpagerindicator.CirclePageIndicator


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */

class MainFragment : Fragment(), MyInterface {

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

    private var im: ImageView? = null
    private var pbs: ProgressBar? = null
    private lateinit var mPager: ViewPager

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_main_dec, container, false)
        im = v.findViewById<ImageView>(R.id.im_status)
        pbs = v.findViewById(R.id.progressBar_status)

        var indicator = v.findViewById<CirclePageIndicator>(R.id.circlePageIndicator)
        mPager = v.findViewById(R.id.pager)
        mPager.adapter =
            ScreenSlidePagerAdapter(
                activity!!.applicationContext,
                childFragmentManager,
                this
            )
        mPager.currentItem =
            ManageSettings().readFile("displayConf.cfg", activity!!.applicationContext).split(";")[0].toInt()
        indicator.setViewPager(mPager)

        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        ManageSettings().saveFile("displayConf.cfg", "0;", activity!!.applicationContext)
                    }
                    1 -> {
                        ManageSettings().saveFile("displayConf.cfg", "1;", activity!!.applicationContext)
                    }
                }
            }
        })
        Initialize()
        return v
    }

    fun Initialize() {
        var data: List<String> = ManageSettings()
            .readFile("addressConf.cfg", activity!!.applicationContext).split(";")

        if (!data[0].equals("null")) {
            pbs!!.visibility = View.VISIBLE
            var socketConnection = SocketConnection()
            socketConnection.Parameters(this)
            socketConnection.execute("TestConnection", data[0], data[1])
        } else {
            pbs!!.visibility = View.INVISIBLE
            mPager.adapter =
                ScreenSlidePagerAdapter(
                    activity!!.applicationContext,
                    childFragmentManager,
                    this
                )
            //mPager.currentItem =
            ManageSettings().readFile("displayConf.cfg", activity!!.applicationContext).split(";")[0].toInt()
        }
    }

    private var v = false
    fun getSatus(): Boolean {
        return v
    }

    fun atualizeStatus() {
        v = false
        im!!.setImageResource(R.drawable.ic_error)
    }

    override fun VerifySocket(b: Boolean) {
        v = b

        if (b) {
            im!!.setImageResource(R.drawable.ic_check)
        } else {
            im!!.setImageResource(R.drawable.ic_error)
        }
        pbs!!.visibility = View.INVISIBLE
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
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
