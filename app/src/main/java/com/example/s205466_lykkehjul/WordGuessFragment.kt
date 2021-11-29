package com.example.s205466_lykkehjul

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s205466_lykkehjul.adapter.RVAdapter
import com.example.s205466_lykkehjul.data.WheelValues
import com.example.s205466_lykkehjul.data.Words
import kotlin.random.Random


class WordGuessFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    private var underScoreWord = " "
    private var myPoints = 0
    private var wheelPoints = "hej"
    private var wheel = WheelValues.spinWheel
    private var countries = Words.countries
    private val randomIndex = Random.nextInt(countries.size)
    private val randomWord = countries[randomIndex]
    var wordSoFar: String? = null
    var newUnderScoreWord: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_word_guess, container, false)
        wheel.add("0")

        recyclerView = view?.findViewById(R.id.RV)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = context?.let { RVAdapter(it, wheel) }


        val guessedLetter = view.findViewById<EditText>(R.id.letterInput)
        val guessBtn = view.findViewById<Button>(R.id.guessBtn)
        val spinBtn = view.findViewById<Button>(R.id.spinBtn)

//        guessBtn.setOnClickListener {
//            if(TextUtils.isEmpty(guessedLetter.text.toString())){
//                Toast.makeText(activity, "Input letter", Toast.LENGTH_SHORT).show()
//            }
//            else(
//
//            )
//        }

        spinBtn.setOnClickListener {
            spinWheel()
        }
        return view
    }

    private fun  initializeWheelPoints(): ArrayList<String>{
        val values = ArrayList<String>()
        values.add("1200")
        values.add("200")
        values.add("500")
        values.add("1600")
        values.add("100")
        return values
    }

    private fun spinWheel(){
        var list = initializeWheelPoints()
        var randomIndex = Random.nextInt(list.size)
        wheelPoints = list[randomIndex]
        wheel[0]= wheelPoints
        recyclerView?.adapter?.notifyItemChanged(0)


    }

//    private fun revealLetter(letter: Char): ArrayList<String> {
//        val charArray = getWordsList()[0].toCharArray()
//        var indexOfLetter = randomWord.indexOf(letter)
//
//        while (indexOfLetter >= 0){
//            charArray[indexOfLetter] = randomWord[indexOfLetter]
//            indexOfLetter = randomWord.indexOf(letter,indexOfLetter + 1)
//        }
//         newUnderScoreWord = String(charArray)
//
//        val list = ArrayList<String>()
////        list.add(getWordsList()[0].replace(underScoreWord,newUnderScoreWord))
//        list.add(newUnderScoreWord!!)
//        recyclerView?.adapter = context?.let { RVAdapter(it,list ) }
//
//        return list
//    }

//    private fun getWordsList(): ArrayList<String> {
//        val sb = StringBuilder()
//
//        randomWord.forEach {
//            sb.append("_")
//        }
//        underScoreWord = sb.toString()
//
//        val list = ArrayList<String>()
//        list.add(underScoreWord)
//        return list
//    }

}