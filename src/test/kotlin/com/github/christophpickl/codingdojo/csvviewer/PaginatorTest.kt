package com.github.christophpickl.codingdojo.csvviewer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test(groups = ["csvviewer"])
class PaginatorTest {

    fun `Given 2 rows and page size of 1 When get current page Then return skip 0 and take 1`() {
        val paginator = Paginator(pageSize = 1, totalRows = 2)
        assertThat(paginator.currentPageRequest, equalTo(PageRequest(0, 1)))
    }

    fun `Given 2 rows and page size of 1 When get next page Then return skip 1 and take 1`() {
        val paginator = Paginator(pageSize = 1, totalRows = 2)
        paginator.nextPage()
        assertThat(paginator.currentPageRequest, equalTo(PageRequest(1, 1)))
    }

    fun `Given already at first page When get previous page Then stay at first page`() {
        val paginator = Paginator(pageSize = 1, totalRows = 2)
        paginator.previousPage()
        assertThat(paginator.currentPageRequest, equalTo(PageRequest(0, 1)))
    }

    fun `Given already at last page When get next page Then stay at last page`() {
        val paginator = Paginator(pageSize = 1, totalRows = 1)
        paginator.nextPage()
        assertThat(paginator.currentPageRequest, equalTo(PageRequest(0, 1)))
    }

    fun `Given 3 rows and page size of 1 When get last page Then return skip 2 and take 1`() {
        val paginator = Paginator(pageSize = 1, totalRows = 3)
        paginator.lastPage()
        assertThat(paginator.currentPageRequest, equalTo(PageRequest(2, 1)))
    }

    fun `Given 3 rows and page size of 1 and moved to last page When get first page Then return skip 0 and take 1`() {
        val paginator = Paginator(pageSize = 1, totalRows = 3)
        paginator.lastPage()
        paginator.firstPage()
        assertThat(paginator.currentPageRequest, equalTo(PageRequest(0, 1)))
    }

    fun `Given 3 rows and page size of 1 and moved to last page When get previous page Then return skip 1 and take 1`() {
        val paginator = Paginator(pageSize = 1, totalRows = 3)
        paginator.lastPage()
        paginator.previousPage()
        assertThat(paginator.currentPageRequest, equalTo(PageRequest(1, 1)))
    }

    fun `Given bigger page size than rows Then just take according to page size`() {
        val paginator = Paginator(pageSize = 10, totalRows = 1)
        assertThat(paginator.currentPageRequest, equalTo(PageRequest(0, 10)))
    }

    fun `Given 2 rows and page size of 3 Then next page equals last page`() {
        val paginator = Paginator(pageSize = 3, totalRows = 2)
        paginator.nextPage()
        val next = paginator.currentPageRequest
        paginator.lastPage()
        val last = paginator.currentPageRequest
        assertThat(next, equalTo(last))
    }

    fun `Given 3 rows and page size of 1 When get page display Then show 1 of 3`() {
        val paginator = Paginator(pageSize = 1, totalRows = 3)
        assertThat(paginator.pageDisplay, equalTo("Page 1 of 3"))
    }

}
