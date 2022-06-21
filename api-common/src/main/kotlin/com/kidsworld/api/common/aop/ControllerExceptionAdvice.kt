package com.kidsworld.api.common.aop

import com.kidsworld.api.common.exception.CommonException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionAdvice {

    @ExceptionHandler(CommonException::class)
    fun commonExceptionHandler(exception: CommonException): ResponseEntity<Any> {
        // TODO error logging
        return ResponseEntity.status(exception.httpStatus).body(
            mapOf(
                "code" to exception.errorCode,
                "message" to exception.message,
                "exception" to exception.javaClass.simpleName
            ))
    }
}