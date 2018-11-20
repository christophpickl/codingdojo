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
        assertThat(printed, equalTo("Must define filename as application argument!\n"))
    }

    @Test(
        expectedExceptions = [IllegalArgumentException::class],
        expectedExceptionsMessageRegExp = """.*invalid\.csv.*"""
    )
    fun `When passing invalid file path Then throw`() {
        CsvViewerApp.main(arrayOf("invalid.csv"))
    }

    fun `When passing persons CSV Then print proper CSV table`() {
        val printed = IoUtil.readFrom {
            CsvViewerApp.main(arrayOf("persons.csv"))
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

}
