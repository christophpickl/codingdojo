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
            containsSubstring("Invalid CLI arguments!"),
            containsSubstring("arg1"),
            containsSubstring("arg2")
        ))
    }

    fun `Given app with optional index CLI args Then print index`() {
        val printed = IoTestUtil.readAndWrite("anything") {
            WordCountApp.main(arrayOf("-index"))
        }
        assertThat(printed, containsSubstring("Index:"))
    }

    fun `Given app with file and index flag Then print result along with index`() {
        val printed = IoTestUtil.readFrom {
            WordCountApp.main(arrayOf(readFromTextFilePath, "-index"))
        }
        assertThat(printed, allOf(
            containsSubstring("Number of words: "),
            containsSubstring("Index:")
        ))
    }

    fun `Given app with index flag Then print proper index`() {
        val printed = IoTestUtil.readAndWrite("y x z") {
            WordCountApp.main(arrayOf("-index"))
        }
        assertThat(printed, containsSubstring("Index:\nx\ny\nz"))
    }

}
