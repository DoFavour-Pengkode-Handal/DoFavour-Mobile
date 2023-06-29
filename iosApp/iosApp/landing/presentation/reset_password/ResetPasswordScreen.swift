//
//  ResetPasswordScreen.swift
//  iosApp
//
//  Created by Darren Thiores on 29/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ResetPasswordScreen: View {
    @StateObject private var viewModel = IosResetPasswordViewModel()
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        VStack {
            CommonHeader(
                title: "Reset Password",
                onBackClick: {
                    presentationMode.wrappedValue.dismiss()
                }
            )
            
            Spacer()
            
            Group {
                DefaultTextField(
                    title: "Email",
                    text: Binding(
                        get: {
                            viewModel.state.email
                        },
                        set: {
                            viewModel.onEvent(
                                event: .OnEmailChange(email: $0)
                            )
                        }
                    )
                )
                .background(
                    Color(UIColor.systemBackground)
                        .cornerRadius(8)
                )
                
                if let emailError = viewModel.state.emailError {
                    HStack {
                        Text(emailError.description())
                            .font(.caption)
                            .foregroundColor(.red)
                        
                        Spacer()
                    }
                }
            }
            
            Spacer()
                .frame(height: 16)
            
            Group {
                PasswordTextField(
                    hint: "Confirm New Password",
                    passwordVisible: Binding(
                        get: {
                            viewModel.state.isPasswordVisible
                        },
                        set: { _ in
                            viewModel.onEvent(
                                event: .ToggleShowPassword()
                            )
                        }
                    ),
                    text: Binding(
                        get: {
                            viewModel.state.password
                        },
                        set: {
                            viewModel.onEvent(
                                event: .OnPasswordChange(password: $0)
                            )
                        }
                    )
                )
                .background(
                    Color(UIColor.systemBackground)
                        .cornerRadius(8)
                )
                
                if let passwordError = viewModel.state.passwordError {
                    HStack {
                        Text(passwordError.description())
                            .font(.caption)
                            .foregroundColor(.red)
                        
                        Spacer()
                    }
                }
            }
            
            Spacer()
                .frame(height: 32)
            
            Button {
                viewModel.onEvent(
                    event: .Reset()
                )
            } label: {
                Text("Reset Password")
                    .font(Font.button)
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.borderedProminent)
            .tint(Color.onBackground)
            
            Spacer()
        }
        .padding()
        .gradientSurface()
        .navigationBarBackButtonHidden()
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
        .navigationDestination(
            isPresented: Binding(
                get: {
                    viewModel.state.resetSuccess
                },
                set: {
                    viewModel.onEvent(
                        event: .UpdateResetResult(result: $0)
                    )
                }
            )
        ) {
            VerifyOtpScreen(email: viewModel.state.email)
        }
    }
}

struct ResetPasswordScreen_Previews: PreviewProvider {
    static var previews: some View {
        ResetPasswordScreen()
    }
}
