package com.kidsworld.api.controller

import com.kidsworld.api.service.DummyService
import com.kidsworld.domain.dto.UserDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("dummy")
class DummyController(private val dummyService: DummyService) {

    @GetMapping
    fun meaningless(@RequestParam("name") name: String): ResponseEntity<UserDto> {
        return ResponseEntity.ok(UserDto.of(dummyService.getUser(name)))
    }
}