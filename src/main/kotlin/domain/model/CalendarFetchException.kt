package org.redbyte.domain.model

class CalendarFetchException(msg: String, override val cause: Throwable?) : RuntimeException(msg, cause)