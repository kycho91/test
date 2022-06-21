package com.kidsworld.domain.repository.devdb

import com.kidsworld.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findTopByName(name: String): UserEntity?
}
