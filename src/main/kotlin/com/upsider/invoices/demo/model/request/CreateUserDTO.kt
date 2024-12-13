package com.upsider.invoices.demo.model.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

/**
 * Data Object for creating a new user.
 */
data class CreateUserDTO(

    @field:NotBlank (message = "名前は必須")
    @Schema(
        description = "同じユーザープールで重複したユーザー名を使用しないでください",
        example = "John Doe")
    val name: String,

    @field:Email(message = "電子メールは有効でなければならない")
    @Schema(description = "スタッフのEメールアドレス", example = "johndoe@example.com")
    val email: String,

    @field:NotBlank(message = "パスワード")
    @field:Size(min = 8, max = 50, message = "パスワード長さ制限は８ー５０英文字です")
    @Schema(description = "新規アカウントのパスワード", example = "abcsaad")
    val password: String,

    @field:NotBlank(message = "会社名")
    @field:Size(min = 4, max = 50, message = "会社名長さ制限は８ー５０英文字です")
    @Schema(description = "新規アカウントの会社名", example = "abc株式会社")
    val companyName: String,
)