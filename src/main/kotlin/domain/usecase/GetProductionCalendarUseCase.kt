package org.redbyte.domain.usecase

import org.redbyte.data.repository.CalendarRepository
import java.util.*

class GetProductionCalendarUseCase(
    private val repository: CalendarRepository
) {
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    operator fun invoke(handler: CalendarRequestHandler<String>, year: Int = currentYear): String {
        val calendar = repository.getProductionCalendar(year)
        return handler.handle(calendar)
    }
}
