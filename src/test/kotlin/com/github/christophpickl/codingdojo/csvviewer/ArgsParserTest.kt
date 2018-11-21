package com.github.christophpickl.codingdojo.csvviewer

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isA
import org.testng.annotations.Test

@Suppress("UNCHECKED_CAST")
@Test
class ArgsParserTest {

    private val defaultPageSize = 10

    fun `Given empty args When parse Then return wrong args`() {
        assertThat(parseArgs(), isA<Args.WrongArgs>())
    }

    fun `Given 3 args When parse Then return wrong args`() {
        assertThat(parseArgs("1", "2", "3"), isA<Args.WrongArgs>())
    }

    fun `Given 2 args When parse Then return right args`() {
        assertThat(parseArgs("1", "2"), isA<Args.RightArgs>())
    }

    fun `Given 1 arg When parse Then csv file is set to that arg`() {
        val args = parseArgs("csv.file")
        assertThat(args, isA<Args.RightArgs>())
        assertThat((args as Args.RightArgs).csvFile, equalTo("csv.file"))
    }

    fun `Given 2 args with valid page size When parse Then page size is set properly`() {
        val args = parseArgs("any", "42")
        assertThat((args as Args.RightArgs).pageSize, equalTo(42))
    }

    fun `Given 2 args with invalid page size When parse Then return wrong args`() {
        assertThat(parseArgs("any", "NaN"), isA<Args.WrongArgs>())
    }

    fun `Given 1 arg When parse Then default page size is set`() {
        val args = parseArgs("any")
        assertThat((args as Args.RightArgs).pageSize, equalTo(defaultPageSize))
    }

    private fun parseArgs(vararg args: String) = ArgsParser.parse(args as Array<String>)

}
