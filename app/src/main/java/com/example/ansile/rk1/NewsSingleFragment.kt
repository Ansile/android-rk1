package com.example.ansile.rk1

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.mail.park.articlelistlib.Article
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [NewsSingleFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [NewsSingleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsSingleFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private lateinit var title: String
    private lateinit var content: String
    private lateinit var dateString: String

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val article = arguments.getSerializable("article") as Article
            title = article.title
            content = article.content
            val date = article.date
            dateString = date.toString()
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.i("finishedDisplay", Integer.toString(resources.configuration.orientation))
        return inflater!!.inflate(R.layout.fragment_news_single, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        Log.i("View in fragment", view.toString())
        super.onViewCreated(view, savedInstanceState)
        view?.findViewById<TextView>(R.id.titleView)?.text = title
        view?.findViewById<TextView>(R.id.contentView)?.text = content
        view?.findViewById<TextView>(R.id.dateView)?.text = dateString
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsSingleFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): NewsSingleFragment {
            val fragment = NewsSingleFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
