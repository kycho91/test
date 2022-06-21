package com.kidsworld.client.sample.configuration

import feign.Response
import feign.codec.ErrorDecoder
import java.lang.Exception

class SampleClientErrorDecoder: ErrorDecoder {

    override fun decode(methodKey: String?, response: Response?): Exception {
        TODO("Not yet implemented")
    }
}