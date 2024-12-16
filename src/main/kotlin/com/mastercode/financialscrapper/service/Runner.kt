package com.mastercode.financialscrapper.service

import com.mastercode.financialscrapper.csv.CsvHelper
import com.mastercode.financialscrapper.scrapper.Scrapper
import com.mastercode.financialscrapper.utils.Formatter
import com.opencsv.CSVWriter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.StringWriter
import java.nio.charset.StandardCharsets
import java.util.logging.Logger

@Component
class Runner(
    @Value("\${STOCKS:#{null}}")
    private val stockParam: String? = null,
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
        requireNotNull(stockParam) { "STOCK parameter cannot be null" }

        val stockList = Formatter.commaToList(stockParam).sorted()
        log.info("Detected: ${stockList.size} stock(s). $stockList")

        val stocks = stockList.map {
            val info = scrapper.getStockInfo(it)
            log.info("Waiting..")
            Thread.sleep(requestIntervalMs)
            log.info("Finished")
            info
        }

        log.info("Creating CSV file..")
        csvHelper.createFile(stocks)
    }
}
