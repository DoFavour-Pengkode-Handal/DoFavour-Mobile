package com.example.dofavour.landing.domain.use_cases

import com.example.ajarin.core.utils.erros.ValidationError

class ValidateNumber {
    operator fun invoke(
        number: String
    ): Result<Unit> {
        if (!number.startsWith("+")) {
            return Result.failure(
                ValidationError("Number should starts with \"+\"")
            )
        }

        if (number.length < 10) {
            return Result.failure(
                ValidationError("Number used to has at least 10 digits")
            )
        }

        return Result.success(Unit)
    }
}