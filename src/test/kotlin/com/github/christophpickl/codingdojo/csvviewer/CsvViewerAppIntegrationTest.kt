package com.github.christophpickl.codingdojo.csvviewer

import com.github.christophpickl.codingdojo.IoUtil
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class CsvViewerAppIntegrationTest {

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
        val printed = IoUtil.readFrom {
            CsvViewerApp.main(arrayOf("test_persons.csv"))
        }
        assertThat(printed, equalTo("""
            Name     |Age|City     
            ---------+---+---------
            Peter    |42 |NewYork  
            Paul     |57 |London   
            Mary     |35 |Munich   
            Jaques   |66 |Paris    
            Yuri     |23 |Moscow   
            Stephanie|47 |Stockholm
            Nadia    |29 |Madrid   ${"\n"}
        """.trimIndent()))
    }

    fun `When passing test persons CSV and page size Then print proper CSV table`() {
        val printed = IoUtil.readFrom {
            CsvViewerApp.main(arrayOf("test_persons.csv", "2"))
        }
        assertThat(printed, equalTo("""
            Name     |Age|City
            ---------+---+---------
            Peter    |42 |NewYork
            Paul     |57 |London   ${"\n"}
        """.trimIndent()))
    }

}
