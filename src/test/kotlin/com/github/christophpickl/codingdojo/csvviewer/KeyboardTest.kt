package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.IoUtil
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.Exit
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.FirstPage
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.LastPage
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.NextPage
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.PreviousPage
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.PageChoice
import com.github.christophpickl.codingdojo.toDataProviding
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

@Test(groups = ["csvviewer"], timeOut = 500)
class KeyboardTest {

    fun `When read next Then print at least menu along with prompt`() {
        val printed = IoUtil.readAndWrite("n\n") {
            val choice = Keyboard.readNext()
            assertThat(choice, equalTo(NextPage as UserChoice))
        }
        assertThat(printed, equalTo("N(ext page, P(revious page, F(irst page, L(ast page, eX(it\n>> "))
    }

    @DataProvider(name = "providerUserChoices", parallel = false)
    fun `provide user choices`() = listOf(
        "n" to NextPage,
        "p" to PreviousPage,
        "f" to FirstPage,
        "l" to LastPage,
        "x" to Exit
    ).toDataProviding()

    @Test(dataProvider = "providerUserChoices")
    fun `When read proper key Then return user choice `(keyAndChoice: Pair<String, MenuChoice>) {
        IoUtil.readAndWrite("${keyAndChoice.first}\n") {
            val choice = Keyboard.readNext()
            assertThat(choice, equalTo(keyAndChoice.second as UserChoice))
        }
    }

    fun `When read next and pass integer Then return selected page but 0 base indexed`() {
        IoUtil.readAndWrite("1\n") {
            val choice = Keyboard.readNext()
            assertThat(choice is PageChoice, equalTo(true))
            assertThat((choice as PageChoice).requestedPage, equalTo(0))
        }
    }

}
