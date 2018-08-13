package com.example.personal.androidadvanced

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_blank.*
import com.example.personal.androidadvanced.BlankFragment.OnFragmentInteractionListener


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlankFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var onFragmentInteractionListener: OnFragmentInteractionListener
    private var mRadioButtonChoice = NONE
    private var ratingValue = 0f

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
        val rBar = rootview.findViewById(R.id.ratingBar) as RatingBar
        val tvRating = rootview.findViewById(R.id.tvRating) as TextView

        rBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { p0, p1, p2 ->
            onFragmentInteractionListener.onRadioButtonChoice(YES, p1)
        }

        mRadioButtonChoice = arguments!!.getInt(CHOICE)
        if(mRadioButtonChoice == YES) {
            tvRating.visibility = View.VISIBLE
            rBar.visibility = View.VISIBLE
        }
        ratingValue = arguments!!.getFloat(bValue)
        rBar.rating = ratingValue

        if(mRadioButtonChoice!=NONE) radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).id)
        radioGroup.setOnCheckedChangeListener { p0, checkedId ->
            val rButton = p0!!.findViewById(checkedId) as RadioButton
            val index = p0.indexOfChild(rButton)
            when (index) {
                YES -> {
                    textView3.text = "Este articulo te gusta"
                    tvRating.visibility = View.VISIBLE
                    ratingBar.visibility = View.VISIBLE
                    mRadioButtonChoice = YES
                    onFragmentInteractionListener.onRadioButtonChoice(YES, 0f)
                }
                NO -> {
                    textView3.text = "Gracias"
                    tvRating.visibility = View.GONE
                    ratingBar.visibility = View.GONE
                    mRadioButtonChoice = NO
                    onFragmentInteractionListener.onRadioButtonChoice(NO,0f)
                }
                else -> {
                    mRadioButtonChoice = NONE
                    onFragmentInteractionListener.onRadioButtonChoice(NONE,0f)
                }
            }
        }
        return rootview
    }

    interface OnFragmentInteractionListener {
        fun onRadioButtonChoice(choice: Int,rBar:Float)
    }

    companion object {
        private const val YES = 0
        private const val NO = 1
        private const val NONE = 2
        private const val CHOICE = "choice"
        private const val bValue = "bar_value"
        fun newInstance(choice: Int,barValue:Float): BlankFragment {
            val fragment = BlankFragment()
            val bundle = Bundle()
            bundle.putInt(CHOICE, choice)
            bundle.putFloat(bValue,barValue)
            fragment.arguments = bundle
            return fragment
        }
    }
}
