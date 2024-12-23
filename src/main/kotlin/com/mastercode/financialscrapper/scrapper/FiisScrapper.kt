package com.mastercode.financialscrapper.scrapper

import com.mastercode.financialscrapper.model.Fii
import com.mastercode.financialscrapper.utils.toCustomBigDecimal
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Evaluator
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.logging.Logger

@Component
@ConditionalOnProperty(name = ["SCRAPPER.ORIGIN"], havingValue = "fiis")
class FiisScrapper(
    @Value("\${FIIS.HOST:#{null}}")
    private val host: String? = null,
) : Scrapper {

    companion object {
        private val log: Logger = Logger.getLogger(this::class.qualifiedName)
    }

    override fun getFiiInfo(fiiName: String): Fii {
        requireNotNull(host) { "host param cannot be null" }
        val doc = Jsoup
            .connect(host + fiiName)
            .get()
        log.info("Processing $fiiName")

        return Fii(
            name = fiiName,
            currentValue = getCurrentValue(doc),
            lastDividend = getLastDividend(doc),
            dividendYield = getDividendYield(doc),
            pvp = getPvp(doc),
            segment = getSegment(doc),
        )
    }

    private fun getLastDividend(doc: Document): BigDecimal {
        return doc
            .selectFirst(Evaluator.ContainsOwnText("Último Rendimento"))
            ?.previousElementSibling()
            ?.text()!!
            .toCustomBigDecimal()
    }

    private fun getCurrentValue(doc: Document): BigDecimal {
        return doc
            .selectFirst("div[class=\"item quotation\"]")
            ?.children()
            ?.first()
            ?.children()
            ?.select("span[class=\"value\"]")
            ?.first()
            ?.text()!!
            .toCustomBigDecimal()
    }

    private fun getDividendYield(doc: Document): BigDecimal {
        return doc
            .selectFirst(Evaluator.ContainsOwnText("Dividend Yield"))
            ?.previousElementSibling()
            ?.children()
            ?.first()
            ?.text()!!
            .toCustomBigDecimal()
    }

    private fun getPvp(doc: Document): BigDecimal {
        return doc
            .selectFirst(Evaluator.ContainsOwnText("P/VP"))
            ?.previousElementSibling()
            ?.children()
            ?.first()
            ?.text()!!
            .toCustomBigDecimal()
    }

    private fun getSegment(doc: Document): String {
        return doc
            .selectFirst(Evaluator.ContainsOwnText("Segmento ANBIMA"))
            ?.nextElementSibling()
            ?.text()!!
    }
}
