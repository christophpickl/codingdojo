package com.github.christophpickl.codingdojo.csvviewer.cli

import com.github.christophpickl.codingdojo.IoUtil
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.testng.annotations.Test

@Test
class SystemInputOutputTest {

    fun `When print text Then text is printed to sysout`() {
        val read = IoUtil.readFrom {
            SystemInputOutput.print("x")
        }
        assertThat(read, equalTo("x"))
    }

    fun `When println text Then text with newline is printed to sysout`() {
        val read = IoUtil.readFrom {
            SystemInputOutput.println("x")
        }
        assertThat(read, equalTo("x\n"))
    }

    fun `When read line Then entered text ist returned`() {
        IoUtil.writeTo("x") {
            val read = SystemInputOutput.readLine()
            assertThat(read, equalTo("x"))
        }
    }
    
}
