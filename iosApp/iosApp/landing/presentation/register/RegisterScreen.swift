import SwiftUI

struct RegisterScreen: View {
    @StateObject private var viewModel = IosRegisterViewModel()
    
    var body: some View {
        VStack(alignment: .leading) {
            TitleLessHeader()
                .padding()
            
            Spacer()
                .frame(height: 64)
            
            Text("Sign Up")
                .font(Font.h1)
                .padding()
            
            VStack {
                Spacer()
                    .frame(height: 32)
                
                Group {
                    DefaultTextField(
                        title: "Full Name",
                        text: Binding(
                            get: {
                                viewModel.state.fullName
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnFullNameChange(name: $0)
                                )
                            }
                        )
                    )
                    .background(
                        Color(UIColor.systemBackground)
                            .cornerRadius(8)
                    )
                    
                    if let nameError = viewModel.state.fullNameError {
                        HStack {
                            Text(nameError.description())
                                .font(.caption)
                                .foregroundColor(.red)
                            
                            Spacer()
                        }
                    }
                    
                    Spacer()
                        .frame(height: 16)
                }
                
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
                    
                    Spacer()
                        .frame(height: 16)
                }
                
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
                
                Spacer()
                    .frame(height: 32)
                
                Button {
                    viewModel.onEvent(
                        event: .Register()
                    )
                } label: {
                    Text("Sign Up")
                        .font(Font.button)
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(.borderedProminent)
                .tint(Color.onBackground)
                .disabled(!viewModel.state.registerButtonEnabled)
                
                Spacer()
                    .frame(height: 16)
                
                HStack {
                    Text("Already have an account?")
                        .font(Font.body1)
                    
                    NavigationLink {
                        LoginScreen()
                    } label: {
                        Text("Log In")
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

struct RegisterScreen_Previews: PreviewProvider {
    static var previews: some View {
        RegisterScreen()
    }
}
