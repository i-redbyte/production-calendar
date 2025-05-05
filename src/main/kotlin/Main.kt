package org.redbyte

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup

@Serializable
data class ProductionCalendar(
    val year: Int,
    val months: List<MonthData>
)

@Serializable
data class MonthData(
    val monthName: String,
    val holidays: List<Int>,
    val preHolidays: List<Int>
)

fun main() {
    val year = 2025
    val url = "https://www.consultant.ru/law/ref/calendar/proizvodstvennye/$year/"

    try {
        val doc = Jsoup.connect(url).get()
        val calendar = parseCalendar(doc, year)
        val json = Json { prettyPrint = true }.encodeToString(calendar)
        println(json)
    } catch (e: Exception) {
        println("Ошибка: ${e.message}")
    }
}

fun parseCalendar(doc: org.jsoup.nodes.Document, year: Int): ProductionCalendar {
    val months = mutableListOf<MonthData>()

    doc.select("table.cal").forEach { monthTable ->
        val monthName = monthTable.select("th.month").text()
        val holidays = mutableListOf<Int>()
        val preHolidays = mutableListOf<Int>()

        monthTable.select("td.weekend, td.holiday").forEach { day ->
            val dayNumber = day.text().toIntOrNull()
            if (dayNumber != null) {
                holidays.add(dayNumber)
            }
        }

        monthTable.select("td.preholiday").forEach { day ->
            val dayNumber = day.text().toIntOrNull()
            if (dayNumber != null) {
                preHolidays.add(dayNumber)
            }
        }

        months.add(MonthData(monthName, holidays, preHolidays))
    }

    return ProductionCalendar(year, months)
}