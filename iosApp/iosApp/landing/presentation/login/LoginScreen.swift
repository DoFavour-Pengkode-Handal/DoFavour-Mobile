import SwiftUI

struct LoginScreen: View {
    @StateObject private var viewModel = IosLoginViewModel()
    
    var body: some View {
        VStack(alignment: .leading) {
            LoginHeader()
                .padding()
            
            Spacer()
                .frame(height: 64)
            
            Text("Login")
                .font(Font.h1)
                .padding()
            
            VStack {
                Spacer()
                    .frame(height: 32)
                
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
                )
                
                if let passwordError = viewModel.state.passwordError {
                    HStack {
                        Text(passwordError.description())
                            .font(.caption)
                            .foregroundColor(.red)
                        
                        Spacer()
                    }
                }
                
                HStack {
                    Spacer()
                    
                    Button {
                        
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
