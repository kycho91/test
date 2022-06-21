package com.kidsworld.web.domain.repository.dev2db

import com.kidsworld.web.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface User2Repository: JpaRepository<UserEntity, Long> {
    fun findTopByName(name: String): UserEntity?
}
