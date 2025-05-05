package org.redbyte.domain.model
import kotlinx.serialization.Serializable

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