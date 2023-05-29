package com.example.dofavour.landing.domain.use_cases

import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

class ValidatePasswordUseCaseTest {
    private lateinit var validatePassword: ValidatePassword

    @BeforeTest
    fun setUp() {
        validatePassword = ValidatePassword()
    }

    @Test
    fun `check if return success when password is valid`() {
        val validPassword = "abc123456"
        val result = validatePassword(validPassword)

        assertThat(result.isSuccess).isTrue()
        assertThat(result).isEqualTo(Result.success(Unit))
    }

    @Test
    fun `check if return error when password length less than 8`() {
        val invalidPassword = "abc"
        val result = validatePassword(invalidPassword)

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull())
            .isNotNull()
            .hasMessage("Password used to has at least 8 characters")
    }
}