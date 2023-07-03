//
//  HomeHeader.swift
//  iosApp
//
//  Created by Darren Thiores on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeHeader: View {
    let state: HomeState
    let onEvent: (HomeEvent) -> Void
    
    var body: some View {
        VStack {
            HStack {
                VStack(alignment: .leading) {
                    HStack {
                        Text("Hello, ")
                            .font(Font.h5)
                        
                        Text("Someone")
                            .font(Font.h5)
                            .foregroundColor(Color.background)
                    }
                    
                    HStack {
                        Image(systemName: "mappin")
                        
                        Text("Jakarta Indonesia")
                    }
                }
                
                Spacer()
                
                NavigationLink {
                    Text("Notification")
                } label: {
                    ZStack {
                        Image(systemName:"bell.fill")
                        
//                        if unreadMessageCount > 0 {
//                            Color
//                                .red
//                                .frame(
//                                    width: 15,
//                                    height: 15
//                                )
//                                .clipShape(Circle())
//                                .overlay {
//                                    Text("\()")
//                                        .font(.caption2)
//                                        .foregroundColor(
//                                            Color(UIColor.systemBackground)
//                                        )
//                                }
//                                .offset(x: 8, y: -8)
//                        }
                    }
                }
                .buttonStyle(.plain)
            }
            
            Spacer()
                .frame(height: 16)
            
            TextField(
                "Search...",
                text: Binding(
                    get: {
                        state.searchText
                    },
                    set: {
                        onEvent(
                            .OnSearchTextChange(newText: $0)
                        )
                    }
                )
            )
            .padding()
            .background(
                RoundedRectangle(cornerRadius: 8)
                    .fill(Color.background)
            )
            .overlay(
                RoundedRectangle(cornerRadius: 8)
                    .strokeBorder(
                        .gray.opacity(0.2),
                        style: StrokeStyle(lineWidth: 2.0)
                    )
            )
            
            Spacer()
                .frame(height: 16)
            
            let size = Int(CampaignFilter.values().size)
            
            ScrollView(.horizontal) {
                HStack {
                    ForEach(0..<size, id: \.self) { index in
                        let campaignFilter = CampaignFilter.values().get(
                            index: Int32(index)
                        )
                        
                        if let filter = campaignFilter {
                            CampaignFilterItem(
                                filter: filter,
                                isSelected: state.filter == filter,
                                onClick: {
                                    onEvent(
                                        .SelectFilter(filter: filter)
                                    )
                                }
                            )
                            .padding(4)
                        }
                    }
                }
            }
        }
    }
}

struct HomeHeader_Previews: PreviewProvider {
    static var previews: some View {
        HomeHeader(
            state: HomeState(
                searchText: "",
                filter: .all,
                campaigns: []
            ),
            onEvent: { _ in }
        )
    }
}
