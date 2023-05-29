package com.example.dofavour.landing.domain.use_cases

import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

class ValidateNumberUseCaseTest {
    private lateinit var validateNumber: ValidateNumber

    @BeforeTest
    fun setUp() {
        validateNumber = ValidateNumber()
    }

    @Test
    fun `check if return success when number is valid`() {
        val validNumber = "+6282282512065"
        val result = validateNumber(validNumber)

        assertThat(result.isSuccess).isTrue()
        assertThat(result).isEqualTo(Result.success(Unit))
    }

    @Test
    fun `check if return error when number don't start with +`() {
        val invalidNumber = "6282282512065"
        val result = validateNumber(invalidNumber)

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull())
            .isNotNull()
            .hasMessage("Number should starts with \"+\"")
    }

    @Test
    fun `check if return error when number length less than 10`() {
        val invalidNumber = "+6282282"
        val result = validateNumber(invalidNumber)

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull())
            .isNotNull()
            .hasMessage("Number used to has at least 10 digits")
    }
}