package com.kidsworld.api.common.aop

import com.kidsworld.api.common.exception.CommonException
import com.kidsworld.api.common.exception.UnknownException
import com.kidsworld.api.common.logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class ResponseAspect {

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    fun responseAround(pjp: ProceedingJoinPoint): Any? {
        val tic = System.currentTimeMillis()
        var returnValue: Any
        try {
            returnValue = pjp.proceed()
            logger().info(
                "{}, request: {}, response: {}, millis: {}",
                "url", // TODO
                "request", // TODO
                "response", // TODO
                System.currentTimeMillis() - tic
            )
        } catch (commonException: CommonException) {
            throw commonException
        } catch (exception: Exception) {
            logger().error("${exception.javaClass.simpleName} - ${exception.message}")
            throw UnknownException(0, "error")
        }
        return returnValue
    }
}