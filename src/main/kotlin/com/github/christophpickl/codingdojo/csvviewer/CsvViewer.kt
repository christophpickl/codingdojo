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

    fun view() {
        println(Formatter.format(table, paginator.currentPageRequest))
        println(paginator.pageDisplay)
        val choice = Keyboard.readNext()

        when (choice) {
            is PageChoice -> {
                if (choice.requestedPage in 0..paginator.maxPage) {
                    paginator.selectPage(choice.requestedPage)
                }
                view()
            }
            is NextPage -> {
                paginator.nextPage()
                view()
            }
            is PreviousPage -> {
                paginator.previousPage()
                view()
            }
            is FirstPage -> {
                paginator.firstPage()
                view()
            }
            is LastPage -> {
                paginator.lastPage()
                view()
            }
            is Exit -> {
                println("Good bye :)")
            }
        }
    }

}
