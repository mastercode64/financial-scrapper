package com.mastercode.financialscrapper.service

import com.mastercode.financialscrapper.scrapper.Scrapper
import com.mastercode.financialscrapper.utils.Formatter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class Runner(
    @Value("\${stocks:#{null}}")
    private val stockParam: String? = null,

    private val scrapper: Scrapper,
) : ApplicationRunner {
    companion object {
        private val log: Logger = Logger.getLogger(this::class.qualifiedName)
    }

    override fun run(args: ApplicationArguments) {
        log.info("Starting scrapper")

        requireNotNull(stockParam) { "stock param cannot be null" }

        val stockList = Formatter.commaToList(stockParam)
        log.info("Detected: ${stockList.size} stock(s). $stockList")

        stockList
            .map(scrapper::getStockInfo)
            .map { it.toString() }
            .forEach(log::info)
        log.info("Finished processing")
    }
}
