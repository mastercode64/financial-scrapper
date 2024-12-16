package com.mastercode.financialscrapper.service

import com.mastercode.financialscrapper.csv.CsvHelper
import com.mastercode.financialscrapper.model.Stock
import com.mastercode.financialscrapper.scrapper.Scrapper
import com.mastercode.financialscrapper.utils.Formatter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class Runner(
    @Value("\${STOCKS}")
    private val stockParam: String,
    @Value("\${REQUEST_INTERVAL_MS}")
    private val requestIntervalMs: Long,
    private val scrapper: Scrapper,
    private val csvHelper: CsvHelper,
) : ApplicationRunner {
    companion object {
        private val log: Logger = Logger.getLogger(this::class.qualifiedName)
    }

    override fun run(args: ApplicationArguments) {
        log.info("Starting scrapper")
        log.info("Using $requestIntervalMs ms interval between requests")
        val stockList = Formatter.stringToList(stockParam).sorted()
        log.info("Detected: ${stockList.size} stock(s). $stockList")
        val stocks = stockList.map(::processStock)
        csvHelper.createFile(stocks)
    }

    private fun processStock(stock: String): Stock {
        val info = scrapper.getStockInfo(stock)
        log.info("Waiting..")
        Thread.sleep(requestIntervalMs)
        log.info("Finished")
        return info
    }
}
