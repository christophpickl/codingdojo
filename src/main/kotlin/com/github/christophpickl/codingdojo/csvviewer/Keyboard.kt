package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.PageChoice
import kotlin.reflect.KClass

object Keyboard {
    @JvmStatic
    fun main(args: Array<String>) {
        readNext()
    }
    fun readNext(): UserChoice {
        var input: String
        var choice: UserChoice?
        println(MenuChoice.allChoices.joinToString(", ") { it.label })
        do {
            print(">> ")
            input = readLine() ?: ""
            choice = MenuChoice.findByKey(input)
            if (choice == null && input.toIntOrNull() != null) {
                choice = PageChoice(input.toInt() - 1)
            }
        } while (choice == null)
        return choice
    }
}

sealed class UserChoice {

    companion object {
        val allChoices: List<KClass<out UserChoice>> by lazy {
            ArrayList<KClass<out UserChoice>>().apply {
                this += PageChoice::class
                this += MenuChoice.allChoices.map { it::class }
            }
        }
    }

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

}
