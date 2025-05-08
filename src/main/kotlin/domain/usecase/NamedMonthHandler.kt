package org.redbyte.domain.usecase

import org.redbyte.domain.formatter.MonthFormatter
import org.redbyte.domain.model.ProductionCalendar
import org.redbyte.domain.model.filterByMonth

class NamedMonthHandler(
    private val monthName: String,
    private val formatter: MonthFormatter
) : CalendarRequestHandler<String> {

    override fun handle(calendar: ProductionCalendar): String {
        val month = calendar.filterByMonth(monthName)
            ?: error("$monthName не найден")
        return formatter.format(month)
    }
}