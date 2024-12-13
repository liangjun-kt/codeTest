package com.upsider.invoices.demo.model.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

/**
 * Data Object for user login action
 */
data class LoginDTO(
    @field:Email(message = "電子メールは有効でなければならない")
    @Schema(description = "スタッフのEメールアドレス", example = "johndoe@example.com")
    val email: String,

    @field:NotBlank(message = "パスワード")
    @field:Size(min = 8, max = 50, message = "パスワード長さ制限は８ー５０英文字です")
    @Schema(description = "新規アカウントのパスワード", example = "abcsaad")
    val password: String,
)
