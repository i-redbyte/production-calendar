package org.redbyte.domain.model

import kotlinx.serialization.Serializable
import java.util.*

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
    val currentMonthNumber = Calendar.getInstance().get(Calendar.MONTH)
    return months[currentMonthNumber]
}