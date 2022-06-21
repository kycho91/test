package com.kidsworld.web.api.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CoreBase {
}

// 코틀린 클래스에서 slf4j Logger 객체 사용가능
inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}
