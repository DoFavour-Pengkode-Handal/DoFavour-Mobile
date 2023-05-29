package com.example.dofavour.landing.domain.use_cases

import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

class ValidateEmailUseCaseTest {
    private lateinit var validateEmail: ValidateEmail

    @BeforeTest
    fun setUp() {
        validateEmail = ValidateEmail()
    }

    @Test
    fun `check if email is valid`() {
        val validEmail = "testemail@gmail.com"
        val result = validateEmail(validEmail)

        assertThat(result.isSuccess).isTrue()
        assertThat(result).isEqualTo(Result.success(Unit))
    }

    @Test
    fun `check if email is invalid then return error`() {
        val invalidEmail = "testemail"
        val result = validateEmail(invalidEmail)

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull())
            .isNotNull()
            .hasMessage("Email format is wrong!")

    }
}