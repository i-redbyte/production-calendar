package org.redbyte.domain.usecase

import org.redbyte.data.CalendarRepository
import org.redbyte.domain.model.ProductionCalendar

class GetProductionCalendarUseCase(
    private val repository: CalendarRepository
) {
    operator fun invoke(year: Int): ProductionCalendar {
        return repository.getProductionCalendar(year)
    }
}