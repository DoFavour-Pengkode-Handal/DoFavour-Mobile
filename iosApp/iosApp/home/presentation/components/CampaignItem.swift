//
//  CampaignItem.swift
//  iosApp
//
//  Created by Darren Thiores on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CampaignItem: View {
    let geo: GeometryProxy
    let campaign: Campaign
    
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        VStack(alignment: .leading) {
            ZStack {
                AsyncImage(
                    url: URL(string: campaign.imageUrl),
                    content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(
                                width: geo.size.width - 32,
                                height: 220
                            )
                    },
                    placeholder: {
                        ZStack {
                            ProgressView()
                                .frame(alignment: .center)
                        }
                        .background(.ultraThinMaterial)
                        .frame(
                            width: geo.size.width - 32,
                            height: 220
                        )
                    }
                )
                .background(.gray)
                .cornerRadius(4)
                
                ZStack(alignment: .topTrailing) {
                    Color.clear
                    
                    NavigationLink {
                        CampaignDetailScreen(
                            campaignId: campaign.id
                        )
                    } label: {
                        Text("View")
                            .font(Font.button)
                    }
                    .buttonStyle(.borderedProminent)
                    .tint(Color.onBackground)
                    .padding()
                }
            }
            .frame(
                width: geo.size.width - 32,
                height: 220
            )
            
            Text(campaign.name)
                .font(Font.h5Bold)
            
            HStack {
                AsyncImage(
                    url: URL(string: campaign.imageUrl),
                    content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(
                                width: 32,
                                height: 32
                            )
                    },
                    placeholder: {
                        ZStack {
                            ProgressView()
                                .frame(alignment: .center)
                        }
                        .background(.ultraThinMaterial)
                        .frame(
                            width: 32,
                            height: 32
                        )
                    }
                )
                .cornerRadius(4)
                
                Spacer()
                    .frame(width: 16)
                
                VStack(alignment: .leading) {
                    HStack(spacing: 0) {
                        Text("Created by ")
                            .foregroundColor(.gray)
                        Text(campaign.organization.name)
                    }
                    
                    Text("Verified since \(campaign.organization.dateCreated)")
                        .font(Font.captionInter)
                }
            }
            
            HStack {
                Image(systemName: "calendar")
                    .resizable()
                    .scaledToFit()
                    .frame(
                        width: 32,
                        height: 32
                    )
                
                Spacer()
                    .frame(width: 16)
                
                HStack(spacing: 0) {
                    Text(campaign.date)
                    
                    Text(" • ")
                        .foregroundColor(Color.primaryColor)
                    
                    Text(campaign.time)
                }
            }
            
            HStack {
                Image(systemName: "person.3.fill")
                    .resizable()
                    .scaledToFit()
                    .frame(
                        width: 32,
                        height: 32
                    )
                
                Spacer()
                    .frame(width: 16)
                
                let value = Float(
                    Double(campaign.volunteerCount) / Double(campaign.limit)
                )
                let available = campaign.limit - campaign.volunteerCount
                
                VStack(alignment: .leading) {
                    CampaignBar(value: value)
                    
                    Text("\(available) more people from \(campaign.limit)")
                }
            }
        }
        .padding()
        .background(
            Rectangle()
                .fill(
                    colorScheme == .dark ? Color(UIColor.secondarySystemFill) : Color(UIColor.systemBackground)
                )
                .cornerRadius(4)
                .shadow(radius: 2)
        )
    }
}

struct CampaignItem_Previews: PreviewProvider {
    static var previews: some View {
        GeometryReader { geo in
            CampaignItem(
                geo: geo,
                campaign: HomeDummy().campaigns[0]
            )
        }
    }
}
