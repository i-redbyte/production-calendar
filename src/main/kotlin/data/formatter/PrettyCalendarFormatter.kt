package org.redbyte.data.formatter

import org.redbyte.domain.formatter.CalendarFormatter
import org.redbyte.domain.model.ProductionCalendar

class PrettyCalendarFormatter : CalendarFormatter {
    override fun format(calendar: ProductionCalendar): String = buildString {
        appendLine("📅 Производственный календарь ${calendar.year}")
        appendLine("*".repeat(50))
        calendar.months.forEach { month ->
            appendLine("\n🔹 ${month.monthName}")
            if (month.holidays.isNotEmpty()) {
                appendLine("🎉 Выходные: ${month.holidays.joinToString(", ")}")
            }
            if (month.preHolidays.isNotEmpty()) {
                appendLine("⏳ Сокращённые дни: ${month.preHolidays.joinToString(", ")}")
            }
            appendLine("-".repeat(30))
        }
    }
}