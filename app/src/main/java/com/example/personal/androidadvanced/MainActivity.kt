package com.example.personal.androidadvanced

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),BlankFragment.OnFragmentInteractionListener {

    private var isFragmentDisplayed = true
    private val FRAGMENT_STATE = "state_of_fragment"
    private var choice = 2
    private var rValue = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(FRAGMENT_STATE)
            if (isFragmentDisplayed) btnRate.text = "Cerrar"
        }
        btnRate.setOnClickListener {
            if (isFragmentDisplayed) openFragment()
            else closeFragment()
        }
    }

    fun openFragment() {
        val fragment = BlankFragment.newInstance(choice,rValue)
        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        btnRate.text = "Cerrar"
        isFragmentDisplayed = !isFragmentDisplayed
    }

    fun closeFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment != null) supportFragmentManager.beginTransaction()
                .hide(fragment)
                .commit()
        btnRate.text = "Calificar"
        isFragmentDisplayed = !isFragmentDisplayed
    }

    override fun onRadioButtonChoice(choice: Int, rBar: Float) {
        this.choice = choice
        this.rValue = rBar
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putBoolean(FRAGMENT_STATE, isFragmentDisplayed)
        super.onSaveInstanceState(outState)
    }
}
