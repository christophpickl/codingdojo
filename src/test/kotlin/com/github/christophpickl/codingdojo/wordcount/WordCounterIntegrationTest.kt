package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class WordCounterIntegrationTest {

    fun `Given real counter When count word "the" Then return 0`() {
        assertThat(counter().count("the"), equalTo(0))
    }

    private fun counter() = WordCounter(buildStopWordsFilter())

}
