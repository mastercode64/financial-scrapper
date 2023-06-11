package com.mastercode.financialscrapper.service

import com.mastercode.financialscrapper.model.Stock
import org.jsoup.Jsoup
import org.jsoup.select.Evaluator
import java.util.logging.Logger

class Scrapper(
    private val host: String
) {
    companion object {
        private val log: Logger = Logger.getLogger(this::class.simpleName)
    }

    fun getStockInfo(stockName: String): Stock {
        val doc = Jsoup
            .connect(host + stockName)
            .get()

        val value = doc
            .select("div[title~=\"Valor atual do ativo\"]")
            .get(0)
            .text()
            .toBigDecimal()

        return Stock(
            currentValue = value
        )

    }
}