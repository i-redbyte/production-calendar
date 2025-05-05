package org.redbyte

import kotlinx.coroutines.runBlocking
import org.redbyte.data.CalendarRepository
import org.redbyte.domain.model.DisplayMode
import org.redbyte.domain.usecase.GetProductionCalendarUseCase


fun main(args: Array<String>) = runBlocking {
    val repository = CalendarRepository()
    val getCalendar = GetProductionCalendarUseCase(repository)

    val mode = when (args.getOrNull(0)) {
        "--json" -> DisplayMode.JSON
        else -> DisplayMode.PRETTY
    }

    try {
        val output = getCalendar(2022, mode)
        println(output)
    } catch (e: Exception) {
        println("❌ Ошибка: ${e.message}")
    }
}