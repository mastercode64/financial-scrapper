package com.mastercode.financialscrapper.utils

object Formatter {
    fun stringToList(param: String): List<String> {
        return param
            .filter { !it.isWhitespace() }
            .split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
    }
}
