package org.redbyte

import org.redbyte.data.formatter.JsonCalendarFormatter
import org.redbyte.data.formatter.PrettyCalendarFormatter
import org.redbyte.data.formatter.PrettyMonthFormatter
import org.redbyte.data.repository.CalendarRepository
import org.redbyte.domain.model.DisplayMode
import org.redbyte.domain.usecase.CurrentMonthHandler
import org.redbyte.domain.usecase.FullCalendarHandler
import org.redbyte.domain.usecase.GetProductionCalendarUseCase
import org.redbyte.domain.usecase.NamedMonthHandler

fun main(args: Array<String>) {
    val repository = CalendarRepository()
    val jsonFormatter = JsonCalendarFormatter()
    val prettyFormatter = PrettyCalendarFormatter()
    val monthFormatter = PrettyMonthFormatter()
    val getCalendar = GetProductionCalendarUseCase(repository = repository)

    val mode = when (args.getOrNull(0)) {
        "--json" -> DisplayMode.JSON
        else -> DisplayMode.PRETTY
    }

    try {
        val august2020 = getCalendar(NamedMonthHandler("май", monthFormatter), 2020)
        println(august2020)
        val allYear = getCalendar(FullCalendarHandler(mode, jsonFormatter, prettyFormatter))
        println(allYear)
        val currentMonth = getCalendar(CurrentMonthHandler(monthFormatter))
        println(currentMonth)

    } catch (e: Exception) {
        println("❌ Ошибка: ${e.message}")
    }
}