package com.backend.user.controller

import com.backend.user.domain.User
import com.backend.user.dto.request.UserSaveRequest
import com.backend.user.dto.request.UserUpdateRequest
import com.backend.user.dto.response.UserResponse
import com.backend.user.service.UserService
import com.backend.util.ResponseDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService,
    ) {
    @PostMapping("/user")
    fun saveUser(@RequestBody @Valid request: UserSaveRequest, bindingResult: BindingResult):ResponseEntity<*> {
        userService.saveUser(request)
        return ResponseEntity(ResponseDto(1, "유저 등록 완료", null), HttpStatus.OK)
    }
    @GetMapping("/user")
    fun getUsers(): MutableList<User> {
        return userService.getUsers()
    }
    @PutMapping("/user")
    fun updateUser(@RequestBody request: UserUpdateRequest):ResponseEntity<*> {
        userService.updateUser(request)
        return ResponseEntity(ResponseDto(1, "유저 수정 완료", null), HttpStatus.OK)
    }
    @DeleteMapping("/user")
    fun deleteUser(@RequestParam userCode: String) {
        userService.deleteUser(userCode)
    }
}