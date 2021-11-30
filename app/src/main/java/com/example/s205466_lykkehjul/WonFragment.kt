package com.example.s205466_lykkehjul

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class WonFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_won, container, false)
        val text = view.findViewById<TextView>(R.id.text)
        val args= this.arguments
        val inputData = args?.get("data")
        text.text = inputData.toString()
        return view
    }

}