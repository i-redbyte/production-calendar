package org.redbyte.data.formatter

import org.redbyte.domain.formatter.MonthFormatter
import org.redbyte.domain.model.MonthData

class PrettyMonthFormatter : MonthFormatter {
    override fun format(month: MonthData): String = buildString {
        appendLine("📅 ${month.monthName}")
        appendLine("=".repeat(30))
        appendLine("🎉 Выходные: ${month.holidays.joinToString(", ")}")
        if (month.preHolidays.isNotEmpty()) {
            appendLine("⏳ Сокращённые дни: ${month.preHolidays.joinToString(", ")}")
        }
    }
}