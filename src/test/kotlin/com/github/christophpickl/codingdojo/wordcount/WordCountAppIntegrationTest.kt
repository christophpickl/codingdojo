package com.github.christophpickl.codingdojo.wordcount

import com.github.christophpickl.codingdojo.IoTestUtil
import com.natpryce.hamkrest.allOf
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import org.testng.annotations.Test

@Test
class WordCountAppIntegrationTest {

    fun `Given app without CLI args When entering two words Then print out the result of 2`() {
        val printed = IoTestUtil.readAndWrite("x y\n") {
            WordCountApp.main(emptyArray())
        }
        assertThat(printed, allOf(
            containsSubstring("Enter text:"),
            containsSubstring("Number of words: 2")
        ))
    }

    fun `Given app with file as single CLI arg Then print out the result of words in that file`() {
        val printed = IoTestUtil.readFrom {
            WordCountApp.main(arrayOf(readFromTextFilePath))
        }
        assertThat(printed, allOf(
            containsSubstring("Enter text:").not(),
            containsSubstring("Number of words: ")
        ))
    }

    fun `Given app with two CLI args Then print error`() {
        val printed = IoTestUtil.readFrom {
            WordCountApp.main(arrayOf("arg1", "arg2"))
        }
        assertThat(printed, allOf(
            containsSubstring("Invalid CLI arguments!")
        ))
    }

}
