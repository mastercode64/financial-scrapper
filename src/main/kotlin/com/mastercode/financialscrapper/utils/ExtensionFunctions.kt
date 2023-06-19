package com.mastercode.financialscrapper.utils

import java.math.BigDecimal
import java.math.RoundingMode

fun String.toCustomBigDecimal(): BigDecimal {
    return this
        .replace(".", "")
        .replace(",", ".")
        .toBigDecimal()
        .setScale(2, RoundingMode.DOWN)
}
