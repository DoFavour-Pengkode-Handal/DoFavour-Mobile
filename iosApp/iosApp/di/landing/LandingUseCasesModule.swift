import Foundation
import shared

class LandingUseCasesModule {
    init() {
        @Inject var repository: LandingRepository
        
        @Provider var validateNumber: ValidateNumber = ValidateNumber()
        @Provider var validatePassword: ValidatePassword = ValidatePassword()
        @Provider var validateEmail: ValidateEmail = ValidateEmail()
        @Provider var login: Login = Login(repository: repository)
        @Provider var register: Register = Register(repository: repository)
        @Provider var landingUseCases: LandingUseCases = LandingUseCases(
            validateNumber: validateNumber,
            validatePassword: validatePassword,
            validateEmail: validateEmail,
            login: login,
            register: register
        )
    }
}

