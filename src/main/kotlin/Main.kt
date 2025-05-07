package org.redbyte

import org.redbyte.data.CalendarRepository
import org.redbyte.domain.model.DisplayMode
import org.redbyte.domain.usecase.GetProductionCalendarUseCase

private const val CURRENT_YEAR = 2025

fun main(args: Array<String>)  {
    val repository = CalendarRepository()
    val getCalendar = GetProductionCalendarUseCase(repository)

    val mode = when (args.getOrNull(0)) {
        "--json" -> DisplayMode.JSON
        else -> DisplayMode.PRETTY
    }

    try {
        val august2020 = getCalendar(2020, "август")
        println(august2020)
        val allYear = getCalendar(CURRENT_YEAR, mode)
        println(allYear)
        val currentMonth = getCalendar(2025)
        println(currentMonth)

    } catch (e: Exception) {
        println("❌ Ошибка: ${e.message}")
    }
}