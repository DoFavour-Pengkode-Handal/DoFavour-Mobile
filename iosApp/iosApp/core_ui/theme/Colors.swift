import Foundation
import SwiftUI
import shared

extension Color {
    init(hex: Int64, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
    
    private static let colors = Colors()
    static let green20 = Color(hex: colors.Green20)
    static let green40 = Color(hex: colors.Green40)
    static let green60 = Color(hex: colors.Green60)
    static let green80 = Color(hex: colors.Green80)
    static let green100 = Color(hex: colors.Green100)
    static let textBlack = Color(hex: colors.TextBlack)
    static let grey = Color(hex: colors.Grey)
    static let darkGrey = Color(hex: colors.DarkGrey)
        
    static let primaryColor = Color(light: .green40, dark: .green40)
    static let background = Color(light: .grey, dark: .darkGrey)
    static let onPrimary = Color(light: .white, dark: .white)
    static let onBackground = Color(light: .textBlack, dark: .white)
    static let surface = Color(light: .white, dark: .darkGrey)
    static let onSurface = Color(light: .textBlack, dark: .white)
}

private extension Color {
    init(light: Self, dark: Self) {
        self.init(
            uiColor: UIColor(
                light: UIColor(light),
                dark: UIColor(dark)
            )
        )
    }
}

private extension UIColor {
    convenience init(light: UIColor, dark: UIColor) {
        self.init { traits in
            switch traits.userInterfaceStyle {
            case .light, .unspecified:
                return light
            case .dark:
                return dark
            @unknown default:
                return light
            }
        }
    }
}

