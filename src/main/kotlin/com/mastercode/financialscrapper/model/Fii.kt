package com.mastercode.financialscrapper.model

import java.math.BigDecimal

data class Fii(
    val name: String,
    val segment: String? = null,
    val currentValue: BigDecimal? = null,
    val lastDividend: BigDecimal? = null,
    val dividendYield: BigDecimal? = null,
    val pvp: BigDecimal? = null,
)
