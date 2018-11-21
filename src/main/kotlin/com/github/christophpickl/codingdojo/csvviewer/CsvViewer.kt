package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.Exit
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.FirstPage
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.LastPage
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.NextPage
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice.PreviousPage
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.PageChoice

class CsvViewer(
    private val table: Table,
    pageSize: Int
) {

    private val paginator = Paginator(pageSize, table.rows)

    fun renderNext() {
        println(Formatter.format(table, paginator.currentPageRequest))
        println(paginator.pageDisplay)

        when (val choice = Keyboard.readNext()) {
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
