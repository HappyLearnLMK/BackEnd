package com.backend.user.controller

import com.backend.user.domain.User
import com.backend.user.dto.request.UserSaveRequest
import com.backend.user.dto.request.UserUpdateRequest
import com.backend.user.dto.response.UserResponse
import com.backend.user.service.UserService
import jakarta.validation.Valid
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService,
    ) {
    @PostMapping("/user")
    fun saveUser(@RequestBody @Valid request: UserSaveRequest, bindingResult: BindingResult) {
        userService.saveUser(request)
    }
    @GetMapping("/user")
    fun getUsers(): MutableList<User> {
        return userService.getUsers()
    }
    /*@PutMapping("/user")
    fun updateUsername(@RequestBody request: UserUpdateRequest) {
        userService.updateUser(request)
    }*/
    @DeleteMapping("/user")
    fun deleteUser(@RequestParam name: String) {
        userService.deleteUser(name)
    }
}