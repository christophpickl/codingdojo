package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.csvviewer.UserChoice.Exit
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.FirstPage
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.LastPage
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.NextPage
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.PreviousPage

class CsvViewer(
    private val table: Table,
    pageSize: Int
) {

    private val paginator = Paginator(pageSize, table.rows)
    private val choiceCommands = UserChoice.values().associate {
        it to when (it) {
            NextPage -> fun() {
                paginator.nextPage()
                view()
            }
            PreviousPage -> fun() {
                paginator.previousPage()
                view()
            }
            FirstPage -> fun() {
                paginator.firstPage()
                view()
            }
            LastPage -> fun() {
                paginator.lastPage()
                view()
            }
            Exit -> fun() {
                println("Good bye :)")
            }
        }
    }

    fun view() {
        println(Formatter.format(table, paginator.currentPageRequest))
        println(paginator.pageDisplay)
        val choice = Keyboard.readNext()
        choiceCommands[choice]!!()
    }

}
