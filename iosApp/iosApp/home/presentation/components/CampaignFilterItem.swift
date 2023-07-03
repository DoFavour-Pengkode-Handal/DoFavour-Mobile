//
//  CampaignFilterItem.swift
//  iosApp
//
//  Created by Darren Thiores on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CampaignFilterItem: View {
    let filter: CampaignFilter
    let isSelected: Bool
    let onClick: () -> Void
    
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Button {
            onClick()
        } label: {
            VStack {
                ZStack(alignment: .center) {
                    Image(systemName: filter.getImageName())
                        .foregroundColor(
                            isSelected ? Color.primary : Color.onBackground
                        )
                }
                .frame(width: 70, height: 70)
                .background(
                    Rectangle()
                        .fill(
                            colorScheme == .dark ? Color(UIColor.secondarySystemFill) : Color(UIColor.systemBackground)
                        )
                        .cornerRadius(4)
                        .shadow(radius: 2)
                )
                .overlay(
                    RoundedRectangle(
                        cornerRadius: 4
                    )
                    .stroke(
                        Color.primary,
                        lineWidth: isSelected ? 2 : 0
                    )
                )
                
                Text(filter.name)
                    .foregroundColor(
                        isSelected ? Color.primary : Color.onBackground
                    )
            }
        }
        .buttonStyle(.plain)
    }
}

struct CampaignFilterItem_Previews: PreviewProvider {
    static var previews: some View {
        CampaignFilterItem(
            filter: .all,
            isSelected: false,
            onClick: {  }
        )
    }
}
