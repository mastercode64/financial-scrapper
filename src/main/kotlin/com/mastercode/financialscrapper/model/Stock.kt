package com.mastercode.financialscrapper.model

import java.math.BigDecimal

data class Stock(
    val name: String? = null,
    val segment: String? = null,
    val currentValue: BigDecimal? = null,
    val lastDividend: BigDecimal? = null,
    val dividendYield: BigDecimal? = null,
    val pvp: BigDecimal? = null,
)
