package com.mastercode.financialscrapper.scrapper

import java.util.*

object StatusInvestUrlFactory {
    fun build(host: String, fii: String): String {
        val fiiCategory = getCategory(fii)
        return "$host/$fiiCategory/${fii.lowercase(Locale.getDefault())}"
    }

    private fun getCategory(fii: String): String {
        val fiiCategory = FiiCategoryMapper.fiiCategory[fii]
        return when(fiiCategory) {
            FiiCategory.FIAGRO -> "fiagros"
            FiiCategory.FIINFRA -> "fiinfras"
            else -> "fundos-imobiliarios"
        }
    }
}