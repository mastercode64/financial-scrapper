package com.mastercode.financialscrapper.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ExtensionFunctionsTest {
    @Test
    fun `should transform string with comma to bigdecimal`() {
        val result = "1,39".toCustomBigDecimal()
        assertThat(result).isEqualTo(BigDecimal.valueOf(1.39))
    }

    @Test
    fun `should transform string with comma and dot to bigdecimal`() {
        val result = "1.000.000,39".toCustomBigDecimal()
        assertThat(result).isEqualTo(BigDecimal.valueOf(1000000.39))
    }

    @Test
    fun `should transform string with dot to bigdecimal`() {
        val result = "1.000.000".toCustomBigDecimal()
        assertThat(result).isEqualTo(BigDecimal.valueOf(1000000).setScale(2))
    }

    @Test
    fun `should transform string to bigdecimal rounding down`() {
        assertThat("5,401".toCustomBigDecimal()).isEqualTo(BigDecimal.valueOf(5.40).setScale(2))
        assertThat("5,409".toCustomBigDecimal()).isEqualTo(BigDecimal.valueOf(5.40).setScale(2))
    }
}
