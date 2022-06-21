package com.kidsworld.domain.repository.dev2db

import com.kidsworld.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface User2Repository: JpaRepository<UserEntity, Long> {
    fun findTopByName(name: String): UserEntity?
}
