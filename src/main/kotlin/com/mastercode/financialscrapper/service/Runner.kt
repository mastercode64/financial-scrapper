package com.mastercode.financialscrapper.service

import com.mastercode.financialscrapper.utils.Formatter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.util.logging.Logger


@Component
class Runner(

) : ApplicationRunner {

    @Value("\${STOCKS:#{null}}")
    private val stockParam: String? = null

    @Value("\${HOST:#{null}}")
    private val host: String? = null

    companion object {
        private val log: Logger = Logger.getLogger(this::class.qualifiedName)
    }

    override fun run(args: ApplicationArguments) {
        log.info("Starting scrapper")

        requireNotNull(stockParam) { "stock param cannot be null" }
        requireNotNull(host) { "host param cannot be null" }

        val stockList = Formatter.commaToList(stockParam)
        log.info("Host: $host")
        log.info("Detected: ${stockList.size} stock(s). $stockList")

        val scrapper = Scrapper(host)
        val stocks = stockList.map(scrapper::getStockInfo)
        log.info(stocks.toString())
        log.info("Finished processing")
    }
}