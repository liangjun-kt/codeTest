package com.upsider.invoices.demo.web

import com.upsider.invoices.demo.model.request.CreateUserDTO
import com.upsider.invoices.demo.model.request.LoginDTO
import com.upsider.invoices.demo.service.AuthService
import jakarta.annotation.PostConstruct
import jakarta.validation.Valid

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/auth")
class AuthApi (val authService: AuthService){

    /**
     * Create a new user API
     *
     * @param createUserDTO data object of the new user
     * @return null
     */
    @PostMapping(value = ["/user"], consumes = ["application/json"])
    fun createUser(@Valid @RequestBody createUserDTO: CreateUserDTO): ResponseEntity<Any> {
        authService.createUser(createUserDTO)
        return ResponseEntity.ok("")
    }


    /**
     * Login API
     *
     * @param loginDTO login json data.
     * @return access token.
     */
    @PostMapping(value = ["/login"], consumes = ["application/json"])
    fun login(@Valid @RequestBody loginDTO: LoginDTO):ResponseEntity<String>{
        val accessToken = authService.loginUser(loginDTO)
        return ResponseEntity.ok(accessToken)
    }
}
