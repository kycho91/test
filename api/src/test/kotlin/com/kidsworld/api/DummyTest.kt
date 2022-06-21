package com.kidsworld.api

import com.kidsworld.domain.util.DummyUtil
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.test.context.TestConstructor

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class DummyTest() {

    @Test
    fun `common모듈 내 정적매소드 호출 테스트`() {
        Assertions.assertThat(DummyUtil.sum(1, 1)).isEqualTo(2)
    }
}
