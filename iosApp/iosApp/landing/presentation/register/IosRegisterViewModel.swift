import Foundation
import shared

extension RegisterScreen {
    @MainActor class IosRegisterViewModel: ObservableObject {
        private let viewModel: RegisterViewModel
        
        @Published var state: RegisterState = RegisterState(
            fullName: "",
            fullNameError: nil,
            email: "",
            emailError: nil,
            password: "",
            passwordError: nil,
            isPasswordVisible: false,
            registerButtonEnabled: false,
            registerError: nil,
            registerSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var validateEmail: ValidateEmail
            @Inject var validatePassword: ValidatePassword
            
            self.viewModel = RegisterViewModel(
                validateEmail: validateEmail,
                validatePassword: validatePassword,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: RegisterEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                    
                    if state.registerSuccess {
                        let userDefaults = UserDefaults.standard
                        userDefaults.set(true, forKey: "isLogin")
                    }
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
