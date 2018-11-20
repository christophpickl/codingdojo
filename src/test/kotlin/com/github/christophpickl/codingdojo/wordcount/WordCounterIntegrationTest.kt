package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class WordCounterIntegrationTest {

    fun `Given counter using real stopwords When count word "the" which IS a stopword Then it is NOT counted`() {
        assertThat(counter().count("the").wordCount, equalTo(0))
    }

    fun `Given counter using real stopwords When count word "THE" which is NOT a stopword Then count it`() {
        assertThat(counter().count("THE").wordCount, equalTo(1))
    }

    fun `Given counter using real stopwords When use sample sentence from requirements 3 Then return proper result`() {
        assertThat(counter().count("Mary had a little lamb").wordCount, equalTo(4))
    }

    fun `Given counter using real stopwords When use sample sentence from requirements 4 Then return proper result`() {
        assertThat(counter().count("Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall."),
            equalTo(CountResult(wordCount = 7, uniqueWordCount = 6)))
    }

    private fun counter() = WordCounter(buildStopWordsFilter())

}
