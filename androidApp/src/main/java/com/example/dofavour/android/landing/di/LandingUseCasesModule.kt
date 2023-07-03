package com.example.dofavour.android.landing.di

import com.example.dofavour.landing.domain.repository.LandingRepository
import com.example.dofavour.landing.domain.use_cases.LandingUseCases
import com.example.dofavour.landing.domain.use_cases.Login
import com.example.dofavour.landing.domain.use_cases.Register
import com.example.dofavour.landing.domain.use_cases.ValidateEmail
import com.example.dofavour.landing.domain.use_cases.ValidateNumber
import com.example.dofavour.landing.domain.use_cases.ValidatePassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LandingUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideValidateNumberUseCase(): ValidateNumber {
        return ValidateNumber()
    }

    @Provides
    @ViewModelScoped
    fun provideValidatePasswordUseCase(): ValidatePassword {
        return ValidatePassword()
    }

    @Provides
    @ViewModelScoped
    fun provideValidateEmailUseCase(): ValidateEmail {
        return ValidateEmail()
    }

    @Provides
    @ViewModelScoped
    fun provideLoginUseCase(
        repository: LandingRepository
    ): Login {
        return Login(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideRegisterUseCase(
        repository: LandingRepository
    ): Register {
        return Register(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideLandingUseCases(
        repository: LandingRepository
    ): LandingUseCases {
        return LandingUseCases(
            validateNumber = ValidateNumber(),
            validatePassword = ValidatePassword(),
            validateEmail = ValidateEmail(),
            login = Login(
                repository = repository
            ),
            register = Register(
                repository = repository
            )
        )
    }
}