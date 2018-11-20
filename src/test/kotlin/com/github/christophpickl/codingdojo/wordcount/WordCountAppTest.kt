package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.allOf
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import org.testng.annotations.Test

@Test
class WordCountAppTest {

    fun `When entering two words Then print out the result of 2`() {
        val printed = IoTestUtil.readAndWrite("a b\n") {
            WordCountApp.main(emptyArray())
        }
        assertThat(printed, allOf(
            containsSubstring("Enter text:"),
            containsSubstring("Number of words: 2")
        ))
    }

}

