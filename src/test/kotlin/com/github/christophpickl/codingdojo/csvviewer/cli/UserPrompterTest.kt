package com.github.christophpickl.codingdojo.csvviewer.cli

import com.github.christophpickl.codingdojo.csvviewer.TestableInputOutput
import com.github.christophpickl.codingdojo.csvviewer.cli.UserChoice.MenuChoice.*
import com.github.christophpickl.codingdojo.csvviewer.cli.UserChoice.PageChoice
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.BeforeMethod
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

@Test(groups = ["csvviewer"])
class UserPrompterTest {

    private lateinit var userPrompter: UserPrompter
    private lateinit var io: TestableInputOutput

    @BeforeMethod
    fun `init state`() {
        io = TestableInputOutput()
        userPrompter = UserPrompter(io = io)
    }

    fun `When read next Then print at least menu along with prompt`() {
        io.addLineToRead("n")
        userPrompter.readNext()
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
        val choice = userPrompter.readNext()
        assertThat(choice, equalTo(givenChoice))
    }

    fun `When read next and pass integer Then return selected page but 0 base indexed`() {
        io.addLineToRead("1")

        val choice = userPrompter.readNext()

        assertThat(choice is PageChoice, equalTo(true))
        assertThat((choice as PageChoice).requestedPage, equalTo(0))
    }
}
