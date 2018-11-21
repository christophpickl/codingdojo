package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.IoUtil
import com.github.christophpickl.codingdojo.toDataProviding
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

@Test
class KeyboardTest {

    fun `foo bar`() {
        val printed = IoUtil.readAndWrite("n\n") {
            val choice = Keyboard.readNext()
            assertThat(choice, equalTo(UserChoice.NextPage))
        }
        assertThat(printed, equalTo("N(ext page, P(revious page, F(irst page, L(ast page, eX(it\n>> "))
    }

    @DataProvider(name = "providerUserChoices", parallel = false)
    fun `provide user choices`() = UserChoice.values().toList().toDataProviding()

    @Test(dataProvider = "providerUserChoices")
    fun `When read proper key Then return user choice `(givenChoice: UserChoice) {
        IoUtil.readAndWrite("${givenChoice.key}\n") {
            val choice = Keyboard.readNext()
            assertThat(choice, equalTo(givenChoice))
        }
    }

}
