package org.redbyte.domain.usecase

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.redbyte.data.CalendarRepository
import org.redbyte.domain.model.DisplayMode
import org.redbyte.domain.model.ProductionCalendar

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

    private fun toJson(calendar: ProductionCalendar): String {
        return prettyJson.encodeToString(calendar)
    }

    private fun toPrettyTable(calendar: ProductionCalendar): String {
        val builder = StringBuilder()
        builder.appendLine("📅 Производственный календарь ${calendar.year}")
        builder.appendLine("*".repeat(50))

        calendar.months.forEach { month ->
            builder.appendLine("\n🔹 ${month.monthName}")
//            builder.appendLine("-".repeat(30))

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