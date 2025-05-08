package org.redbyte.domain.usecase

import org.redbyte.domain.model.ProductionCalendar

interface CalendarRequestHandler<T> {
    fun handle(calendar: ProductionCalendar): T
}
