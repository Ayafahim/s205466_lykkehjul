package com.example.s205466_lykkehjul

import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TestCaseUsedLetters {
    fun myArray(): ArrayList<String> {
        var ar = ArrayList<String>()
        ar.add("a")
        ar.add("b")
        ar.add("c")
        return ar
    }
}

class TestWordGuessingFragment {

    @Test
    fun testWheelListNotEmpty() {
        assertTrue(WordGuessFragment().initializeWheelPoints().size != 0)
    }

    @Test
    fun testWheelPointsInitialized() {
        assertEquals(
            listOf(
                "1200",
                "200",
                "500",
                "1600",
                "100",
                "1000",
                "1500",
                "0",
                "3500",
                "300",
                "50",
                "400",
                "Lost Turn",
                "Bankrupt",
                "Extra Turn"
            ), WordGuessFragment().initializeWheelPoints()
        )
    }

    @Test
    fun testLettersStringFormat() {

        //This line expects correct format
        assertEquals(
            "a, b, c, ",
            WordGuessFragment().showLettersList(TestCaseUsedLetters().myArray())
        )
        // This line expects incorrect format
        assertNotEquals(
            "a,b,c",
            WordGuessFragment().showLettersList(TestCaseUsedLetters().myArray())
        )

    }


}
