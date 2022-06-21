package com.kidsworld.api.common.exception

import org.springframework.http.HttpStatus

class UnknownException(override val errorCode: Int, override val message: String) : CommonException(message) {
    override val httpStatus: HttpStatus
        get() = HttpStatus.SERVICE_UNAVAILABLE
}