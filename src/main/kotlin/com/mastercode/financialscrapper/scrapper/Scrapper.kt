package com.mastercode.financialscrapper.scrapper

import com.mastercode.financialscrapper.model.Fii

interface Scrapper {
    fun getFiiInfo(fiiName: String): Fii
}
