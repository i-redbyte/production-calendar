package org.redbyte.domain.usecase

import org.redbyte.domain.formatter.MonthFormatter
import org.redbyte.domain.model.ProductionCalendar
import org.redbyte.domain.model.currentMonth

class CurrentMonthHandler(
    private val formatter: MonthFormatter
) : CalendarRequestHandler<String> {

    override fun handle(calendar: ProductionCalendar): String {
        val month = calendar.currentMonth()
            ?: error("Текущий месяц не найден")
        return formatter.format(month)
    }
}