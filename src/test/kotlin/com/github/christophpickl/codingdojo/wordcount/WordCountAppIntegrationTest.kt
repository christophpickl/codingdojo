package com.github.christophpickl.codingdojo.wordcount

import com.github.christophpickl.codingdojo.IoTestUtil
import com.natpryce.hamkrest.allOf
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import org.testng.annotations.Test

@Test
class WordCountAppIntegrationTest {

    fun `When entering two words Then print out the result of 2`() {
        val printed = IoTestUtil.readAndWrite("x y\n") {
            WordCountApp.main(emptyArray())
        }
        assertThat(printed, allOf(
            containsSubstring("Enter text:"),
            containsSubstring("Number of words: 2")
        ))
    }

}
