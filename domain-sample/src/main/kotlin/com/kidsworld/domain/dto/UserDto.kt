package com.kidsworld.domain.dto

import com.kidsworld.domain.entity.UserEntity

data class UserDto(
    var id: Int = 0,
    var name: String = "",
    var email: String = "",
    var tel: String = ""
    ) {

    companion object {
        fun of(userEntity: UserEntity): UserDto {
            return UserDto(userEntity.id, userEntity.name, userEntity.email, userEntity.tel)
        }
    }
}