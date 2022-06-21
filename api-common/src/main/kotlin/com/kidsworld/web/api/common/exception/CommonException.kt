package com.kidsworld.web.api.common.exception

import org.springframework.http.HttpStatus

abstract class CommonException(message: String) : Exception(message) {
    abstract val errorCode: Int
    abstract val httpStatus: HttpStatus
}
