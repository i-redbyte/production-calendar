package org.redbyte.domain.usecase

import org.redbyte.domain.formatter.CalendarFormatter
import org.redbyte.domain.model.DisplayMode
import org.redbyte.domain.model.ProductionCalendar

class FullCalendarHandler(
    private val mode: DisplayMode,
    private val jsonFormatter: CalendarFormatter,
    private val prettyFormatter: CalendarFormatter
) : CalendarRequestHandler<String> {

    override fun handle(calendar: ProductionCalendar): String {
        return when (mode) {
            DisplayMode.JSON -> jsonFormatter.format(calendar)
            DisplayMode.PRETTY -> prettyFormatter.format(calendar)
        }
    }
}