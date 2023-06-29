import SwiftUI

struct LoginScreen: View {
    @StateObject private var viewModel = IosLoginViewModel()
    
    var body: some View {
        VStack(alignment: .leading) {
            TitleLessHeader()
                .padding()
            
            Spacer()
                .frame(height: 64)
            
            Text("Login")
                .font(Font.h1)
                .padding()
            
            VStack {
                Spacer()
                    .frame(height: 32)
                
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
                        hint: "Password",
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
                
                HStack {
                    Spacer()
                    
                    NavigationLink {
                        ResetPasswordScreen()
                    } label: {
                        Text("Forgot Password")
                            .font(Font.caption)
                    }
                    .buttonStyle(.plain)
                }
                
                Spacer()
                    .frame(height: 32)
                
                Button {
                    viewModel.onEvent(
                        event: .Login()
                    )
                } label: {
                    Text("Login")
                        .font(Font.button)
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(.borderedProminent)
                .tint(Color.onBackground)
                .disabled(!viewModel.state.loginButtonEnabled)
                
                Spacer()
                    .frame(height: 16)
                
                HStack {
                    Text("Don’t have account yet?")
                        .font(Font.body1)
                    
                    NavigationLink {
                        RegisterScreen()
                    } label: {
                        Text("Sign Up")
                            .font(Font.bodyBold)
                    }
                    .buttonStyle(.plain)
                }
                
                Spacer()
            }
            .padding()
            .roundedGradientSurface()
        }
        .navigationBarBackButtonHidden()
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
