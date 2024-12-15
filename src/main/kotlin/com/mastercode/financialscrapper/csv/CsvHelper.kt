package com.mastercode.financialscrapper.csv

import com.mastercode.financialscrapper.model.Stock
import com.opencsv.CSVWriter
import org.springframework.stereotype.Component
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.text.NumberFormat
import java.util.*
import kotlin.text.Charsets.UTF_8

private const val CSV_SEPARATOR = '|'

private val HEADER_COLUMNS = arrayOf(
    "NAME",
    "VALUE",
    "LAST DIVIDEND"
)

private val numberFormat = NumberFormat.getInstance(Locale("pt", "BR"))

@Component
class CsvHelper {
    fun createFile(stocks: List<Stock>) {
        val path = "investimentos.csv"
        val fos = FileOutputStream(path)
        val osw = OutputStreamWriter(fos, UTF_8)

        val csvWriter = CSVWriter(
            osw,
            CSV_SEPARATOR,
            CSVWriter.NO_QUOTE_CHARACTER,
            CSVWriter.NO_ESCAPE_CHARACTER,
            CSVWriter.DEFAULT_LINE_END
        )

        csvWriter.writeNext(HEADER_COLUMNS)
        stocks.forEach {
            val line = arrayOf(
                it.name,
                numberFormat.format(it.currentValue),
                numberFormat.format(it.lastDividend)
            )
            csvWriter.writeNext(line)
        }
        csvWriter.close()
    }
}