package com.github.christophpickl.codingdojo.csvviewer

object Keyboard {
    fun readNext(): UserChoice {
        var input: String
        var choice: UserChoice?
        println(UserChoice.ordered.joinToString(", ") { it.label })
        do {
            print(">> ")
            input = readLine() ?: ""
            choice = UserChoice.findByKey(input)
        } while (choice == null)
        return choice
    }
}

enum class UserChoice(
    val sortKey: Int,
    val label: String,
    val key: String
) {
    NextPage(1, "N(ext page", "n"),
    PreviousPage(2, "P(revious page", "p"),
    FirstPage(3, "F(irst page", "f"),
    LastPage(4, "L(ast page", "l"),
    Exit(5, "eX(it", "x");

    companion object {
        val ordered by lazy { values().sortedBy { it.sortKey } }
        private val choicesByKey by lazy { values().associateBy { it.key } }
        fun findByKey(key: String): UserChoice? = choicesByKey[key]
    }
}
