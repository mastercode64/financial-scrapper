package com.mastercode.financialscrapper.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FormatterTest {

    @Test
    fun `should convert single item to list`() {
        val input = "A"
        val result = Formatter.stringToList(input)
        assertThat(result).isEqualTo(listOf("A"))
    }

    @Test
    fun `should convert single item with spaces to list`() {
        val input = " A  "
        val result = Formatter.stringToList(input)
        assertThat(result).isEqualTo(listOf("A"))
    }

    @Test
    fun `should convert items with no spaces to list`() {
        val input = "A,B,C"
        val result = Formatter.stringToList(input)
        assertThat(result).isEqualTo(listOf("A", "B", "C"))
    }

    @Test
    fun `should convert items with spaces to list`() {
        val input = "A, B,  C "
        val result = Formatter.stringToList(input)
        assertThat(result).isEqualTo(listOf("A", "B", "C"))
    }

    @Test
    fun `should remove empty strings from list`() {
        val input = ""
        val result = Formatter.stringToList(input)
        assertThat(result).isEqualTo(emptyList<String>())
    }
}
