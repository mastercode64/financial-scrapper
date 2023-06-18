package com.mastercode.financialscrapper.scrapper

import com.mastercode.financialscrapper.model.Stock

interface Scrapper {
    fun getStockInfo(stockName: String): Stock
}