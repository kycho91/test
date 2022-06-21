package com.kidsworld.domain.entity

import javax.persistence.*

@Entity
@Table(name = "user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,
    var email: String = "",
    var name: String = "",
    var tel: String = ""
)
