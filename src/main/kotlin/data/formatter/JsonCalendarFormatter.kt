package org.redbyte.data.formatter

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.redbyte.domain.formatter.CalendarFormatter
import org.redbyte.domain.model.ProductionCalendar

class JsonCalendarFormatter : CalendarFormatter {
    private val prettyJson = Json { prettyPrint = true }
    override fun format(calendar: ProductionCalendar): String {
        return prettyJson.encodeToString(calendar)
    }
}