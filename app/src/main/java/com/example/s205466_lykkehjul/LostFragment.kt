package com.example.s205466_lykkehjul

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class LostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lost, container, false)
        val playAgainBtn: Button = view.findViewById(R.id.playAgainBtn)
        val backBtn: Button = view.findViewById(R.id.backToStartBtn)

        playAgainBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_lostFragment_to_wordGuessFragment)
        }
        backBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_lostFragment_to_startFragment)
        }
        return  view
    }

}