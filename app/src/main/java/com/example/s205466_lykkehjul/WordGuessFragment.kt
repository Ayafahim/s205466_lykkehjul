package com.example.s205466_lykkehjul

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s205466_lykkehjul.adapter.RVAdapter
import com.example.s205466_lykkehjul.data.Words
import java.lang.StringBuilder
import kotlin.random.Random


class WordGuessFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    var underScoreWord = " "
    var myPoints = 0
    var wheelPoints = 0
    var countries = Words.countries
    val randomIndex = Random.nextInt(countries.size)
    private val randomWord = countries[randomIndex]
    var wordSoFar: String? = null
    var newUnderScoreWord: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_word_guess, container, false)

        recyclerView = view?.findViewById(R.id.RV)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = context?.let { RVAdapter(it, getWordsList()) }

        val guessedLetter = view.findViewById<EditText>(R.id.letterInput)
        val guessBtn = view.findViewById<Button>(R.id.guessBtn)



        guessBtn.setOnClickListener {
            if (TextUtils.isEmpty(guessedLetter.text.toString())) {
                Toast.makeText(activity, "Input a letter", Toast.LENGTH_SHORT).show()
            } else {
                if(randomWord.contains(guessedLetter.text.toString()))
                revealLetter(guessedLetter.text.toString()[0])
                guessedLetter.setText("")

//                    recyclerView?.adapter = context?.let { RVAdapter(it,revealLetter(guessedLetter.text.toString()[0]) ) }


            }
        }

        return view
    }


    private fun revealLetter(letter: Char): ArrayList<String> {
        val charArray = getWordsList()[0].toCharArray()
        var indexOfLetter = randomWord.indexOf(letter)

        while (indexOfLetter >= 0){
            charArray[indexOfLetter] = randomWord[indexOfLetter]
            indexOfLetter = randomWord.indexOf(letter,indexOfLetter + 1)
        }
         newUnderScoreWord = String(charArray)

        val list = ArrayList<String>()
//        list.add(getWordsList()[0].replace(underScoreWord,newUnderScoreWord))
        list.add(newUnderScoreWord!!)
        recyclerView?.adapter = context?.let { RVAdapter(it,list ) }

        return list
    }

    private fun getWordsList(): ArrayList<String> {
        val sb = StringBuilder()

        randomWord.forEach {
            sb.append("_")
        }
        underScoreWord = sb.toString()

        val list = ArrayList<String>()
        list.add(underScoreWord)
        return list
    }

    fun saveWordSoFar(word: String): String{

         wordSoFar = word

        return wordSoFar as String

    }
}