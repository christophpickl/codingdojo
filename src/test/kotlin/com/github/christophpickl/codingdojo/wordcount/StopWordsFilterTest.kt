package com.github.christophpickl.codingdojo.wordcount

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class StopWordsFilterTest {

    fun `Given stop word a When pass a Then mark as a stop word`() {
        assertThat(StopWordsFilter(listOf("a")).isStopWord("a"),
            equalTo(true))
    }

    fun `Given stop word a When pass b Then dont mark as a stop word`() {
        assertThat(StopWordsFilter(listOf("a")).isStopWord("b"),
            equalTo(false))
    }

}
