package org.redbyte.domain.usecase

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.redbyte.data.CalendarRepository
import org.redbyte.domain.model.*

class GetProductionCalendarUseCase(
    private val repository: CalendarRepository
) {
    private val prettyJson = Json { prettyPrint = true }

    operator fun invoke(year: Int, mode: DisplayMode): String {
        val calendar = repository.getProductionCalendar(year)
        return when (mode) {
            DisplayMode.JSON -> toJson(calendar)
            DisplayMode.PRETTY -> toPrettyTable(calendar)
        }
    }

    operator fun invoke(year: Int, monthName: String): String {
        val calendar = repository.getProductionCalendar(year)
        val month = calendar.filterByMonth(monthName)
            ?: throw RuntimeException("$monthName не найден, проверьте имя месяца на ошибки")
        return toPrettyMonth(month)
    }

    operator fun invoke(year: Int): String {
        val calendar = repository.getProductionCalendar(year)
        val month = calendar.currentMonth()
            ?: throw RuntimeException("Произошла ошибка при получении данных за текущий месяц")
        return toPrettyMonth(month)
    }

    private fun toJson(calendar: ProductionCalendar): String {
        return prettyJson.encodeToString(calendar)
    }

    private fun toPrettyMonth(month: MonthData): String {
        return buildString {
            appendLine("📅 ${month.monthName}")
            appendLine("=".repeat(30))
            appendLine("🎉 Выходные: ${month.holidays.joinToString(", ")}")
            if (month.preHolidays.isNotEmpty()) {
                appendLine("⏳ Сокращённые дни: ${month.preHolidays.joinToString(", ")}")
            }
        }
    }

    private fun toPrettyTable(calendar: ProductionCalendar): String {
        val builder = StringBuilder()
        builder.appendLine("📅 Производственный календарь ${calendar.year}")
        builder.appendLine("*".repeat(50))

        calendar.months.forEach { month ->
            builder.appendLine("\n🔹 ${month.monthName}")
            if (month.holidays.isNotEmpty()) {
                builder.appendLine("🎉 Выходные: ${month.holidays.joinToString(", ")}")
            }

            if (month.preHolidays.isNotEmpty()) {
                builder.appendLine("⏳ Сокращённые дни: ${month.preHolidays.joinToString(", ")}")
            }
            builder.appendLine("-".repeat(30))
        }

        return builder.toString()
    }
}