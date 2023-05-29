import SwiftUI

struct PrimaryGradientSurface: ViewModifier {
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
            )
    }
}

extension View {
    func gradientSurface() -> some View {
        modifier(PrimaryGradientSurface())
    }
}
