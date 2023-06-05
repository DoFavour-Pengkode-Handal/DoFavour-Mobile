import SwiftUI

struct BoardingScreen: View {
    let radius = 16.0
    
    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                Spacer()
                
                Text("Logo")
                
                Spacer()
                
                VStack(alignment: .leading) {
                    Spacer()
                        .frame(height: 64)
                    
                    Text("Welcome")
                        .font(Font.h1)
                        .foregroundColor(.onPrimary)
                    
                    HStack {
                        Text("Let’s contribute to helping others and saving the earth")
                            .font(Font.subtitle2)
                            .foregroundColor(.onPrimary)
                            .frame(
                                maxWidth: .infinity,
                                alignment: .leading
                            )
                        
                        Spacer()
                            .frame(maxWidth: .infinity)
                    }
                    
                    Spacer()
                        .frame(height: 64)
                    
                    HStack {
                        NavigationLink {
                            LoginScreen()
                        } label: {
                            Text("Login")
                                .font(Font.button)
                                .frame(maxWidth: .infinity)
                        }
                        .buttonStyle(.borderedProminent)
                        .tint(Color.onBackground)
                        
                        NavigationLink {
                            RegisterScreen()
                        } label: {
                            Text("Sign Up")
                                .font(Font.button)
                                .frame(maxWidth: .infinity)
                        }
                        .buttonStyle(.borderedProminent)
                        .tint(Color.background)
                        .foregroundColor(Color.onBackground)
                    }
                }
                .padding()
                .roundedGradientSurface()
            }
        }
    }
}

struct BoardingScreen_Previews: PreviewProvider {
    static var previews: some View {
        BoardingScreen()
    }
}
