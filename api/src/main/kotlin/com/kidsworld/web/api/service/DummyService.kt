package com.kidsworld.web.api.service

import com.kidsworld.web.domain.entity.UserEntity
import com.kidsworld.web.api.common.exception.EntityNotFoundException
import com.kidsworld.web.domain.repository.devdb.UserRepository
import org.springframework.stereotype.Service

@Service
class DummyService(private val userRepository: UserRepository) {

    fun getUser(name: String): UserEntity {
        return userRepository.findTopByName(name) ?: throw EntityNotFoundException(9999, "User[$name]")
    }
}
