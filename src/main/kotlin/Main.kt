package org.redbyte

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.redbyte.data.CalendarRepository
import org.redbyte.domain.usecase.GetProductionCalendarUseCase

fun main() = runBlocking {
    val repository = CalendarRepository()
    val getCalendar = GetProductionCalendarUseCase(repository)

    try {
        val calendar = getCalendar(2025)
        val json = Json { prettyPrint = true }.encodeToString(calendar)
        println(json)
    } catch (e: Exception) {
        println("Ошибка: ${e.message}")
    }
}