//
//  TabButton.swift
//  iosApp
//
//  Created by Darren Thiores on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TabButton: View {
    let title: String
    let onClick: () -> Void
    let isSelected: Bool
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        VStack {
            Button(title) {
                withAnimation { onClick() }
            }
            .frame(maxWidth: .infinity)
            .foregroundColor(isSelected ? .primary : .secondary)

            Color(isSelected ? (colorScheme == .dark ? .white : .black) : .clear)
                .frame(height: 4)
                .padding(.horizontal)
        }
    }
}

struct TabButton_Previews: PreviewProvider {
    static var previews: some View {
        TabButton(
            title: "User",
            onClick: {},
            isSelected: true
        )
    }
}
