package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.IoUtil
import com.github.christophpickl.codingdojo.csvviewer.UserChoice.MenuChoice
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test(groups = ["csvviewer"], timeOut = 500)
class CsvViewerAppIntegrationTest {

    private val exitCommand = "${MenuChoice.Exit.key}\n"

    fun `When passing empty cli args Then print error message`() {
        val printed = IoUtil.readFrom {
            CsvViewerApp.main(emptyArray())
        }
        assertThat(printed, equalTo("${CsvViewerApp.invalidArgsMessage}\n"))
    }

    fun `When passing three cli args Then print error message`() {
        val printed = IoUtil.readFrom {
            CsvViewerApp.main(arrayOf("1", "2", "3"))
        }
        assertThat(printed, equalTo("${CsvViewerApp.invalidArgsMessage}\n"))
    }

    @Test(
        expectedExceptions = [IllegalArgumentException::class],
        expectedExceptionsMessageRegExp = """.*invalid\.csv.*"""
    )
    fun `When passing invalid file path Then throw`() {
        CsvViewerApp.main(arrayOf("invalid.csv"))
    }

    fun `When passing test persons CSV Then print proper CSV table`() {
        val printed = IoUtil.readAndWrite(exitCommand) {
            CsvViewerApp.main(arrayOf("test_persons.csv"))
        }
        assertThat(printed, containsSubstring("""
            No.|Name     |Age|City     
            ---+---------+---+---------
            1. |Peter    |42 |NewYork  
            2. |Paul     |57 |London   
            3. |Mary     |35 |Munich   
            4. |Jaques   |66 |Paris    
            5. |Yuri     |23 |Moscow   
            6. |Stephanie|47 |Stockholm
            7. |Nadia    |29 |Madrid   
        """.trimIndent()))
    }

    fun `When passing test persons CSV and page size Then print proper CSV table`() {
        val printed = IoUtil.readAndWrite(exitCommand) {
            CsvViewerApp.main(arrayOf("test_persons.csv", "2"))
        }
        assertThat(printed, containsSubstring("""
            No.|Name     |Age|City     
            ---+---------+---+---------
            1. |Peter    |42 |NewYork  
            2. |Paul     |57 |London   
        """.trimIndent()))
    }

}
