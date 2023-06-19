package com.mastercode.financialscrapper.utils

object Formatter {
    fun commaToList(param: String): List<String> {
        return param
            .filter { !it.isWhitespace() }
            .split(",")
            .map { it.trim() }
    }
}
