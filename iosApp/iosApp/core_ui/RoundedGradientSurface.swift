import SwiftUI

struct RoundedGradientSurface: ViewModifier {
    func body(content: Content) -> some View {
        let gradientStart = Color.green100
        let gradientEnd = Color.green40
        
        content
            .background(
                LinearGradient(
                    gradient: Gradient(colors: [gradientStart, gradientEnd]),
                    startPoint: .topLeading,
                    endPoint: .bottomTrailing
                )
                .cornerRadius(
                    16,
                    corners: [.topLeft, .topRight]
                )
                .ignoresSafeArea()
            )
    }
}

extension View {
    func roundedGradientSurface() -> some View {
        modifier(RoundedGradientSurface())
    }
}
