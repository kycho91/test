package com.kidsworld.api

import com.kidsworld.domain.repository.dev2db.User2Repository
import com.kidsworld.domain.repository.devdb.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("dev")
class DummyIntegrationTest(val userRepository: UserRepository, val user2Repository: User2Repository) {

    @Test
    fun `JPA 레포지토리 테스트`() {
        Assertions.assertThat(userRepository.count()).isEqualTo(3)
    }

    @Test
    fun `두번째 데이터소스 테스트`() {
        Assertions.assertThat(user2Repository.count()).isEqualTo(3)
    }
}
