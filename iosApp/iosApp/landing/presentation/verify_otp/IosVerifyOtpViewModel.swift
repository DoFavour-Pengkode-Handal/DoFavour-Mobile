//
//  IosVerifyOtpViewModel.swift
//  iosApp
//
//  Created by Darren Thiores on 28/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension VerifyOtpScreen {
    @MainActor class IosVerifyOtpViewModel: ObservableObject {
        private let viewModel: VerifyOtpViewModel
        
        @Published var state: VerifyOtpState = VerifyOtpState(
            otp: "",
            otpError: nil,
            otpSendCount: 0,
            otpCountDown: 0,
            sendOtpError: nil,
            sendOtpLoading: false,
            verifyOtpSuccess: false,
            verifyOtpError: nil,
            verifyOtpLoading: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            self.viewModel = VerifyOtpViewModel(
                coroutineScope: nil
            )
        }
        
        func onEvent(event: VerifyOtpEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                    
                    if state.verifyOtpSuccess {
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
