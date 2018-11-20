package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class WordCounterTest {

    fun `When counting empty string Then return 0`() {
        assertThat(WordCounter().count(""), equalTo(0))
    }

}
