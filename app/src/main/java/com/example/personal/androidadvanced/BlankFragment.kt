package com.example.personal.androidadvanced

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_blank.*
import com.example.personal.androidadvanced.BlankFragment.OnFragmentInteractionListener


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlankFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var onFragmentInteractionListener: OnFragmentInteractionListener
    private var mRadioButtonChoice = NONE

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            onFragmentInteractionListener = context
        } else {
            throw ClassCastException(context!!.toString() + "Exception")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootview = inflater.inflate(R.layout.fragment_blank, container, false)
        val radioGroup = rootview.findViewById(R.id.radioGroup) as RadioGroup
        mRadioButtonChoice = arguments!!.getInt(CHOICE)
        if(mRadioButtonChoice!=NONE) radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).id)
        radioGroup.setOnCheckedChangeListener { p0, checkedId ->
            val rButton = p0!!.findViewById(checkedId) as RadioButton
            val index = p0.indexOfChild(rButton)
            when (index) {
                YES -> {
                    textView3.text = "Este articulo te gusta"
                    textView4.visibility = View.VISIBLE
                    ratingBar.visibility = View.VISIBLE
                    mRadioButtonChoice = YES
                    onFragmentInteractionListener.onRadioButtonChoice(YES)
                }
                NO -> {
                    textView3.text = "Gracias"
                    textView4.visibility = View.GONE
                    ratingBar.visibility = View.GONE
                    mRadioButtonChoice = NO
                    onFragmentInteractionListener.onRadioButtonChoice(NO)
                }
                else -> {
                    mRadioButtonChoice = NONE
                    onFragmentInteractionListener.onRadioButtonChoice(NONE)
                }
            }
        }

        val rBar = rootview.findViewById(R.id.ratingBar) as RatingBar
        rBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { p0, p1, p2 ->
            Toast.makeText(context, p1.toString(), Toast.LENGTH_SHORT).show()
        }
        return rootview
    }

    interface OnFragmentInteractionListener {
        fun onRadioButtonChoice(choice: Int)
    }

    companion object {
        private const val YES = 0
        private const val NO = 1
        private const val NONE = 2
        private const val CHOICE = "choice"
        fun newInstance(choice: Int): BlankFragment {
            val fragment = BlankFragment()
            val bundle = Bundle()
            bundle.putInt(CHOICE, choice)
            fragment.arguments = bundle
            return fragment
        }
    }
}
