package com.example.s205466_lykkehjul

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s205466_lykkehjul.adapter.RVAdapter
import com.example.s205466_lykkehjul.data.Words
import kotlin.random.Random


class WordGuessFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var myPoints = 0
    private var myLives = 5
    private var letterOccurance = 0
    private var lifeCounter: TextView? = null
    private var pointsView: TextView? = null
    private var usedLetters: TextView? = null
     var wheelPoints = " "
    private var wheel =  ArrayList<String>()
    private var countries = Words.countries
    private var guessedLetter = " "
    private val randomIndex = Random.nextInt(countries.size)
    private var randomWord = countries[randomIndex]
    private var underScoreWord = " "
    private var gameWordView: TextView? = null
     var isSpinning = false
    private val sb = StringBuilder()
    private var hasWon = false
    private var hasLost = false
    var letters = ArrayList<String>()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_word_guess, container, false)
        wheel.add("0")

        recyclerView = view?.findViewById(R.id.RV)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = context?.let { RVAdapter(wheel) }


        val letterInput = view.findViewById<EditText>(R.id.letterInput)
        val guessBtn = view.findViewById<Button>(R.id.guessBtn)
        val spinBtn = view.findViewById<Button>(R.id.spinBtn)
        lifeCounter = view.findViewById(R.id.lifeCounter)
        pointsView = view.findViewById(R.id.pointsView)
        gameWordView = view.findViewById(R.id.gameWord)
        usedLetters = view.findViewById(R.id.usedLetters)


        newGame()
        guessBtn.setOnClickListener {
            if (!isSpinning) {
                Toast.makeText(activity, "Please spin wheel first.", Toast.LENGTH_SHORT).show()
            } else {
                if (TextUtils.isEmpty(letterInput.text.toString())) {
                    Toast.makeText(activity, "Input letter", Toast.LENGTH_SHORT).show()
                } else {
                    guessedLetter = letterInput.text.toString().lowercase()
                    letterInput.text = null
                    checkLetter()
                    isSpinning = false
                    letters.add(guessedLetter) // adds user input to list of used letters
                    usedLetters!!.text = showLettersList(letters)
                    checkGameState()
                }
            }
        }

        spinBtn.setOnClickListener {
            if (isSpinning) {
                Toast.makeText(
                    activity,
                    "You have already spun the wheel, make a guess",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                spinWheel()
                checkGameState()
            }
        }

        return view
    }

     fun showLettersList(letterList : ArrayList<String>) : String {
        var listString = ""

        for (i in letterList){
            listString += "$i, "
        }
         return listString
    }

    fun initializeWheelPoints(): ArrayList<String> {
//        Spinning wheel
        val values = ArrayList<String>()
        values.add("1200")
        values.add("200")
        values.add("500")
        values.add("1600")
        values.add("100")
        values.add("1000")
        values.add("1500")
        values.add("0")
        values.add("3500")
        values.add("300")
        values.add("50")
        values.add("400")
        values.add("Lost Turn")
        values.add("Bankrupt")
        values.add("Extra Turn")
        return values
    }

     fun spinWheel() {
        var list = initializeWheelPoints()
        var randomIndex = Random.nextInt(list.size)
        wheelPoints = list[randomIndex]
        wheel[0] = wheelPoints
        recyclerView?.adapter?.notifyItemChanged(0)

        if (wheelPoints == "Bankrupt" || wheelPoints == "Extra Turn" || wheelPoints == "Lost Turn") {
            specialFields()
            isSpinning = false
        } else {
            isSpinning = true
        }

    }

    private fun newGame() {

        lifeCounter?.text = myLives.toString()
        pointsView?.text = "$myPoints"
//        makes underscores for each letter of chosen word
        repeat(randomWord.length) {
            sb.append("_")
            underScoreWord = sb.toString()
        }
        gameWordView?.text = underScoreWord
    }

// checks if letter has already been typed or whether it correct or not
    private fun checkLetter() {

        if (randomWord.contains(guessedLetter)) {
            if (guessedLetter in letters) {
                Toast.makeText(activity, "you have already typed this  letter", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(activity, "You guessed right", Toast.LENGTH_SHORT).show()
                revealLetter()
                calculatePoints()
            }
        } else {
            if (guessedLetter in letters) {
                Toast.makeText(activity, "you have already typed this  letter", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(activity, "You guessed wrong, you lose a life", Toast.LENGTH_SHORT)
                    .show()
                myLives--
                lifeCounter?.text = myLives.toString()
            }
        }
    }

    private fun calculatePoints() {

        if (wheelPoints == "Bankrupt" || wheelPoints == "Extra Turn" || wheelPoints == "Lost Turn") {
            specialFields()
        } else {
//            checks the occurance of a letter, only up to 3 time occurance
            when (letterOccurance) {
                1 -> {
                    myPoints += wheelPoints.toInt()
                    pointsView!!.text = "$myPoints"
                }
                2 -> {

                    myPoints += (wheelPoints.toInt() * 2)
                    pointsView!!.text = "$myPoints"
                }
                3 -> {
                    myPoints += (wheelPoints.toInt() * 3)
                    pointsView!!.text = "$myPoints"
                }

            }
        }
    }

    private fun specialFields() {
        when (wheelPoints) {
            "Extra Turn" -> {
                myLives++
                lifeCounter!!.text = myLives.toString()
                Toast.makeText(activity, "You get an extra life, spin again", Toast.LENGTH_SHORT).show()
            }
            "Lost Turn" -> {
                myLives--
                lifeCounter!!.text = myLives.toString()
                Toast.makeText(activity, "You lose a life, spin again", Toast.LENGTH_SHORT).show()
            }
            "Bankrupt" -> {
                myPoints = 0
                pointsView!!.text = "$myPoints"
                Toast.makeText(activity, "You lost all your points, spin again", Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun revealLetter() {
        val index = randomWord.indexesOf(guessedLetter, true)

        when (index.size) {
            1 -> {
                letterOccurance = 1
                underScoreWord = underScoreWord.replaceRange(
                    index[0],
                    index[0] + 1,
                    guessedLetter
                )
                gameWordView?.text = underScoreWord
            }

            2 -> {
                letterOccurance = 2
                underScoreWord = underScoreWord.replaceRange(
                    index[0],
                    index[0] + 1,
                    guessedLetter
                )
                underScoreWord = underScoreWord.replaceRange(
                    index[1],
                    index[1] + 1,
                    guessedLetter
                )
                gameWordView?.text = underScoreWord
            }
            3 -> {
                letterOccurance = 3
                underScoreWord = underScoreWord.replaceRange(
                    index[0],
                    index[0] + 1,
                    guessedLetter
                )
                underScoreWord = underScoreWord.replaceRange(
                    index[1],
                    index[1] + 1,
                    guessedLetter
                )
                underScoreWord = underScoreWord.replaceRange(
                    index[2],
                    index[2] + 1,
                    guessedLetter
                )
                gameWordView?.text = underScoreWord

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun checkGameState() {
        var word = randomWord.uppercase()
        matchWord(gameWordView!!.text.toString())
        if (myLives == 0) {
            hasLost = true
            Toast.makeText(activity, "You lost, the word was $word", Toast.LENGTH_SHORT).show()
        } else if (myLives >= 1 && hasWon) {
            Toast.makeText(activity, "You Won, the word was $word", Toast.LENGTH_SHORT).show()
        }
        navToFragment()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun matchWord(word: String) {
        hasWon = word.chars().allMatch(Character::isLetter)
    }

    private fun navToFragment() {
        if (hasWon) {
            Navigation.findNavController(requireView()).navigate(R.id.toWinFragment)
        }
        if (hasLost) {
            Navigation.findNavController(requireView()).navigate(R.id.toLostFragment)
        }
    }




    /**
     * Funktionen kan finde indexes af substring i et string.
     * taget fra stackoverflow
     * link: https://stackoverflow.com/questions/62189457/get-indexes-of-substrings-contained-in-a-string-in-kotlin-way
     */
    private fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {
        return this?.let {
            val regex = if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr)
            regex.findAll(this).map { it.range.start }.toList()
        } ?: emptyList()
    }

}