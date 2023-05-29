package com.example.dofavour.landing.domain.use_cases

import com.example.ajarin.core.utils.erros.ValidationError

class ValidatePassword {
    operator fun invoke(
        password: String
    ): Result<Unit> {
        if (password.length < 8) {
            return Result.failure(
                ValidationError("Password used to has at least 8 characters")
            )
        }

        return Result.success(Unit)
    }
}