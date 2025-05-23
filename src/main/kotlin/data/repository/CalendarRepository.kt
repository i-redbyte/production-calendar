package org.redbyte.data.repository

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.redbyte.domain.model.CalendarFetchException
import org.redbyte.domain.model.MonthData
import org.redbyte.domain.model.ProductionCalendar
import java.io.IOException
import java.util.Calendar
import java.util.concurrent.ConcurrentHashMap

class CalendarRepository {
    private val calendarCache = ConcurrentHashMap<Int, ProductionCalendar>()
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    fun getProductionCalendar(year: Int = currentYear): ProductionCalendar = calendarCache
        .computeIfAbsent(year) { fetchAndParseCalendar(it) }

    private fun fetchAndParseCalendar(year: Int = currentYear): ProductionCalendar {
        val url = "$CALENDAR_URL$year"
        val doc = try {
            Jsoup.connect(url).get()
        } catch (e: IOException) {
            throw CalendarFetchException("Ошибка получения календаря: ${e.message}", e)
        }
        return parseCalendar(doc, year)
    }

    private fun parseCalendar(doc: Document, year: Int): ProductionCalendar {
        val months = mutableListOf<MonthData>()
        doc.select("table.cal").forEach { monthTable ->
            val monthName = monthTable.select("th.month").text()
            val holidays = monthTable.select("td.weekend, td.holiday")
                .mapNotNull { it.text().toIntOrNull() }
            val preHolidays = monthTable.select("td.preholiday")
                .mapNotNull { it.text().toIntOrNull() }
            months.add(MonthData(monthName, holidays, preHolidays))
        }
        return ProductionCalendar(year, months)
    }

    companion object {
        private const val CALENDAR_URL = "https://www.consultant.ru/law/ref/calendar/proizvodstvennye/"
    }
}