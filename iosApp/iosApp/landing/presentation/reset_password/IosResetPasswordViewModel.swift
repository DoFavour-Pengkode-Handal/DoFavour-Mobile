//
//  IosResetPasswordViewModel.swift
//  iosApp
//
//  Created by Darren Thiores on 29/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension ResetPasswordScreen {
    @MainActor class IosResetPasswordViewModel: ObservableObject {
        private let viewModel: ResetPasswordViewModel
        
        @Published var state: ResetPasswordState = ResetPasswordState(
            email: "",
            emailError: nil,
            password: "",
            passwordError: nil,
            isPasswordVisible: false,
            isReset: false,
            resetError: nil,
            resetSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var validateEmail: ValidateEmail
            @Inject var validatePassword: ValidatePassword
            
            self.viewModel = ResetPasswordViewModel(
                validateEmail: validateEmail,
                validatePassword: validatePassword,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: ResetPasswordEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
