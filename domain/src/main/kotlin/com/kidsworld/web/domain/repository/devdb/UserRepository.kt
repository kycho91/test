package com.kidsworld.web.domain.repository.devdb

import com.kidsworld.web.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findTopByName(name: String): UserEntity?
}
