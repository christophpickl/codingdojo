package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.*
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.PageChoice
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.BeforeMethod
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

@Test(groups = ["csvviewer"])
class KeyboardTest {

    private lateinit var keyboard: Keyboard
    private lateinit var io: TestableInputOutput

    @BeforeMethod
    fun `init keyboard`() {
        io = TestableInputOutput()
        keyboard = Keyboard(io = io)
    }

    fun `When read next Then print at least menu along with prompt`() {
        io.addLineToRead("n")
        keyboard.readNext()
        assertThat(io.printedText, equalTo("N(ext page, P(revious page, F(irst page, L(ast page, eX(it\n>> "))
    }

    @DataProvider(name = "providerUserChoices", parallel = false)
    fun `provide user choices`() = arrayOf(
        arrayOf("n", NextPage),
        arrayOf("p", PreviousPage),
        arrayOf("f", FirstPage),
        arrayOf("l", LastPage),
        arrayOf("x", Exit)
    )

    @Test(dataProvider = "providerUserChoices")
    fun `When read proper key Then return user choice `(key: String, givenChoice: UserChoice) {
        io.addLineToRead(key)
        val choice = keyboard.readNext()
        assertThat(choice, equalTo(givenChoice))
    }

    fun `When read next and pass integer Then return selected page but 0 base indexed`() {
        io.addLineToRead("1")

        val choice = keyboard.readNext()

        assertThat(choice is PageChoice, equalTo(true))
        assertThat((choice as PageChoice).requestedPage, equalTo(0))
    }
}
