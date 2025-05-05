package org.redbyte

import kotlinx.coroutines.runBlocking
import org.redbyte.data.CalendarRepository
import org.redbyte.domain.model.DisplayMode
import org.redbyte.domain.usecase.GetProductionCalendarUseCase


fun main() = runBlocking {
    val repository = CalendarRepository()
    val getCalendar = GetProductionCalendarUseCase(repository)

    val mode = DisplayMode.PRETTY

    try {
        val output = getCalendar(2025, mode)
        println(output)
    } catch (e: Exception) {
        println("❌ Ошибка: ${e.message}")
    }
}