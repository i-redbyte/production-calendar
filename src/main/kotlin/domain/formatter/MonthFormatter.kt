package org.redbyte.domain.formatter

import org.redbyte.domain.model.MonthData

interface MonthFormatter {
    fun format(month: MonthData): String
}