package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.*
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.PageChoice

class CsvViewer(
    private val userPrompter: UserPrompter = UserPrompter(),
    private val table: Table,
    pageSize: Int
) {

    private val paginator = Paginator(pageSize, table.rows)

    fun startCommandLoop() {
        renderNext()
    }

    private fun renderNext() {
        println(Formatter.format(table, paginator.currentPageRequest))
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
