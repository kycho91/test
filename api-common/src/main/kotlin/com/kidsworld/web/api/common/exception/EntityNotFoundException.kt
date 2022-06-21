package com.kidsworld.web.api.common.exception

import org.springframework.http.HttpStatus

class EntityNotFoundException(override val errorCode: Int, override val message: String) : CommonException(message) {
    override val httpStatus: HttpStatus
        get() = HttpStatus.NOT_FOUND
}
