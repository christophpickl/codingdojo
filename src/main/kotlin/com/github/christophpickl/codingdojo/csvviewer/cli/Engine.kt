package com.github.christophpickl.codingdojo.csvviewer.cli

import com.github.christophpickl.codingdojo.csvviewer.cli.UserChoice.MenuChoice.*
import com.github.christophpickl.codingdojo.csvviewer.cli.UserChoice.PageChoice
import com.github.christophpickl.codingdojo.csvviewer.logic.Paginator
import com.github.christophpickl.codingdojo.csvviewer.logic.Table
import com.github.christophpickl.codingdojo.csvviewer.logic.format

class Engine(
    private val userPrompter: UserPrompter = UserPrompter(),
    private val table: Table,
    private val paginator: Paginator
) {

    companion object {
        fun build(
            userPrompter: UserPrompter = UserPrompter(),
            table: Table,
            pageSize: Int
        ) = Engine(
            userPrompter = userPrompter,
            table = table,
            paginator = Paginator(
                pageSize = pageSize,
                totalRows = table.rows
            )
        )
    }

    fun startCommandLoop() {
        renderNext()
    }

    private fun renderNext() {
        println(table.format(paginator.currentPageRequest))
        println(paginator.pageDisplay)

        when (val choice = userPrompter.readNext()) {
            is PageChoice -> {
                if (choice.requestedPage in 0..paginator.maxPage) {
                    paginator.selectPage(choice.requestedPage)
                }
                renderNext()
            }
            is NextPage -> {
                paginator.nextPage()
                renderNext()
            }
            is PreviousPage -> {
                paginator.previousPage()
                renderNext()
            }
            is FirstPage -> {
                paginator.firstPage()
                renderNext()
            }
            is LastPage -> {
                paginator.lastPage()
                renderNext()
            }
            is Exit -> {
                println("Good bye :)")
            }
        }
    }

}
