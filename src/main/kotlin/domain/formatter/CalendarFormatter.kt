package org.redbyte.domain.formatter

import org.redbyte.domain.model.ProductionCalendar

interface CalendarFormatter {
    fun format(calendar: ProductionCalendar): String
}