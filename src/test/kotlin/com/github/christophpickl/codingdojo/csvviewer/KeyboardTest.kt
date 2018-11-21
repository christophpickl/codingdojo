package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.IoUtil
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.NextPage
import com.github.christophpickl.codingdojo.toDataProviding
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

@Test(groups = ["csvviewer"])
class KeyboardTest {

    fun `When read next Then print at least menu along with prompt`() {
        val printed = IoUtil.readAndWrite("n\n") {
            val choice = Keyboard.readNext()
            assertThat(choice, equalTo(NextPage as UserChoice))
        }
        assertThat(printed, equalTo("N(ext page, P(revious page, F(irst page, L(ast page, eX(it\n>> "))
    }

    @DataProvider(name = "providerUserChoices", parallel = false)
    fun `provide user choices`() = MenuChoice.allChoices.toDataProviding()

    @Test(dataProvider = "providerUserChoices")
    fun `When read proper key Then return user choice `(givenChoice: MenuChoice) {
        IoUtil.readAndWrite("${givenChoice.key}\n") {
            val choice = Keyboard.readNext()
            assertThat(choice, equalTo(givenChoice as UserChoice))
        }
    }

}
