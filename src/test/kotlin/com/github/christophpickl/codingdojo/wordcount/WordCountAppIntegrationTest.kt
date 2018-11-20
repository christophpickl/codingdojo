package com.github.christophpickl.codingdojo.wordcount

import com.github.christophpickl.codingdojo.IoUtil
import com.natpryce.hamkrest.allOf
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class WordCountAppIntegrationTest {

    fun `Given app without CLI args When entering two words Then print out the result of 2`() {
        val printed = IoUtil.readAndWrite("x y\n") {
            WordCountApp.main(emptyArray())
        }
        assertThat(printed, allOf(
            containsSubstring("Enter text:"),
            containsSubstring("Number of words: 2")
        ))
    }

    fun `Given app with file as single CLI arg Then print out the result of words in that file`() {
        val printed = IoUtil.readFrom {
            WordCountApp.main(arrayOf(readFromTextFilePath))
        }
        assertThat(printed, allOf(
            containsSubstring("Enter text:").not(),
            containsSubstring("Number of words: ")
        ))
    }

    fun `Given app without CLI args When entering two words of different length Then print average length with 2 decimals`() {
        val printed = IoUtil.readAndWrite("x yy zz\n") {
            WordCountApp.main(emptyArray())
        }
        assertThat(printed, containsSubstring("average word length: 1.67 characters"))
    }

    fun `Given app without CLI args When entering one word Then print average length with 1 decimal`() {
        val printed = IoUtil.readAndWrite("x\n") {
            WordCountApp.main(emptyArray())
        }
        assertThat(printed, containsSubstring("average word length: 1.0 characters"))
    }

    fun `Given app with two CLI args Then print error`() {
        val printed = IoUtil.readFrom {
            WordCountApp.main(arrayOf("arg1", "arg2"))
        }
        assertThat(printed, allOf(
            containsSubstring("Invalid CLI arguments!"),
            containsSubstring("arg1"),
            containsSubstring("arg2")
        ))
    }

    fun `Given app with optional index CLI args Then print index`() {
        val printed = IoUtil.readAndWrite("anything") {
            WordCountApp.main(arrayOf("-index"))
        }
        assertThat(printed, containsSubstring("Index:"))
    }

    fun `Given app with file and index flag Then print result along with index`() {
        val printed = IoUtil.readFrom {
            WordCountApp.main(arrayOf(readFromTextFilePath, "-index"))
        }
        assertThat(printed, allOf(
            containsSubstring("Number of words: "),
            containsSubstring("Index:")
        ))
    }

    fun `Given app with index flag Then print proper index`() {
        val printed = IoUtil.readAndWrite("y x z") {
            WordCountApp.main(arrayOf("-index"))
        }
        assertThat(printed, containsSubstring("Index:\nx\ny\nz"))
    }

    fun `Given app with index and dictionary flag Then print proper index with dictionary data`() {
        val printed = IoUtil.readAndWrite("x big y") {
            WordCountApp.main(arrayOf("-index", "-dictionary=dict.txt"))
        }
        assertThat(printed, containsSubstring("Index (unknown: 2):\nbig\nx*\ny*"))
    }

    fun `Given app with dictionary but without index flag Then dont print index at all`() {
        val printed = IoUtil.readAndWrite("some text") {
            WordCountApp.main(arrayOf("-dictionary=dict.txt"))
        }
        assertThat(printed, allOf(
            containsSubstring("Number of words:"),
            containsSubstring("Index").not()
        ))
    }

    fun `Given text entered When hit enter without text Then application quits`() {
        val printed = IoUtil.readAndWrite("some text\n\n") {
            WordCountApp.main(emptyArray())
        }
        assertThat(printed, equalTo("Enter text: Number of words: 2, unique: 2; average word length: 4.0 characters\nEnter text: "))
    }

}
