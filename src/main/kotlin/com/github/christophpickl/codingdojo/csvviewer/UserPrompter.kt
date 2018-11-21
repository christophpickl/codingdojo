package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.PageChoice
import com.github.christophpickl.codingdojo.doUntilNotNull

class UserPrompter(
    private val io: InputOutput = SystemInputOutput
) {

    private val prompt = ">> "
    
    fun readNext(): UserChoice {
        printMenu()
        return doUntilNotNull(::tryReadNext)
    }

    private fun printMenu() {
        io.println(MenuChoice.allChoices.joinToString(", ") { it.label })
    }
    
    private fun tryReadNext(): UserChoice? {
        io.print(prompt)
        val input = io.readLine()
        var choice: UserChoice? = MenuChoice.findByKey(input)
        if (choice == null && input.toIntOrNull() != null) {
            choice = PageChoice(input.toInt() - 1)
        }
        return choice
    }
}

sealed class UserChoice {

    class PageChoice(
        val requestedPage: Int
    ) : UserChoice()

    sealed class MenuChoice(
        val label: String,
        val key: String,
        val order: Int
    ) : UserChoice() {

        companion object {
            val allChoices: List<MenuChoice> by lazy {
                listOf(NextPage, PreviousPage, FirstPage, LastPage, Exit).sortedBy { it.order }
            }

            fun findByKey(key: String): MenuChoice? = allChoices.find { it.key == key }
        }

        object NextPage : MenuChoice(order = 1, key = "n", label = "N(ext page")
        object PreviousPage : MenuChoice(order = 2, key = "p", label = "P(revious page")
        object FirstPage : MenuChoice(order = 3, key = "f", label = "F(irst page")
        object LastPage : MenuChoice(order = 4, key = "l", label = "L(ast page")
        object Exit : MenuChoice(order = 5, key = "x", label = "eX(it")

    }

    override fun toString(): String = javaClass.simpleName

}
