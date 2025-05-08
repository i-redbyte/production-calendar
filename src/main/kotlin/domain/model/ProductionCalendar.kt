package org.redbyte.domain.model

import kotlinx.serialization.Serializable
import java.time.LocalDate

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

fun ProductionCalendar.filterByMonth(monthName: String): MonthData? {
    return months.firstOrNull { it.monthName.equals(monthName, ignoreCase = true) }
}

fun ProductionCalendar.currentMonth(): MonthData? {
    val monthIndex = LocalDate.now().monthValue - 1
    return months.getOrNull(monthIndex)
}