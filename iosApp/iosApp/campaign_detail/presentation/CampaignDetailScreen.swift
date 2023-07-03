//
//  CampaignDetailScreen.swift
//  iosApp
//
//  Created by Darren Thiores on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CampaignDetailScreen: View {
    let campaignId: String
    @StateObject private var viewModel = IosCampaignDetailViewModel()
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        let campaign = viewModel.state.campaign
        
        GeometryReader { geo in
            ZStack {
                VStack {
                    CommonHeader(
                        title: "Details",
                        onBackClick: {
                            dismiss()
                        }
                    )
                    .padding()
                    
                    Spacer()
                        .frame(height: 16)
                    
                    List {
                        AsyncImage(
                            url: URL(string: campaign?.imageUrl ?? ""),
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
                        .listRowSeparator(.hidden)
                        
                        Text(campaign?.name ?? "loading...")
                            .font(Font.h4Bold)
                            .listRowSeparator(.hidden)
                        
                        HStack(spacing: 0) {
                            Text("Created by ")
                                .foregroundColor(.gray)
                            
                            Text(campaign?.organization.name ?? "loading...")
                            
                            Spacer()
                                .frame(width: 8)
                            
                            Image(systemName: "checkmark.seal.fill")
                        }
                        .listRowSeparator(.hidden)
                        
                        VStack(alignment: .leading) {
                            let value = Float(
                                Double(campaign?.volunteerCount ?? 0) / Double(campaign?.limit ?? 1)
                            )
                            let available = (campaign?.limit ?? 0) - (campaign?.volunteerCount ?? 0)
                            
                            CampaignBar(value: value)
                            
                            Text("\(available) more people from \(campaign?.limit ?? 0)")
                        }
                        .listRowSeparator(.hidden)
                        
                        Section("Description") {
                            Text(campaign?.description_ ?? "loading...")
                        }
                        .listRowSeparator(.hidden)
                        
                        Section("Schedule") {
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
                                
                                VStack(alignment: .leading) {
                                    Text(campaign?.date ?? "loading...")
                                    
                                    Text(campaign?.time ?? "loading...")
                                }
                            }
                        }
                        .listRowSeparator(.hidden)
                        
                        Section("Location") {
                            HStack {
                                Image(systemName: "mappin")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(
                                        width: 32,
                                        height: 32
                                    )
                                
                                Spacer()
                                    .frame(width: 16)
                                
                                Text(campaign?.location ?? "loading...")
                            }
                        }
                        .listRowSeparator(.hidden)
                        
                        Section("Participants") {
                            let participants = viewModel.state.participants.prefix(5)
                            
                            HStack {
                                ForEach(participants, id: \.id) { participant in
                                    
                                    AsyncImage(
                                        url: URL(string: participant.imageUrl),
                                        content: { image in
                                            image
                                                .resizable()
                                                .aspectRatio(contentMode: .fill)
                                                .frame(
                                                    width: 40,
                                                    height: 40
                                                )
                                        },
                                        placeholder: {
                                            ZStack {
                                                ProgressView()
                                                    .frame(alignment: .center)
                                            }
                                            .background(.ultraThinMaterial)
                                            .frame(
                                                width: 40,
                                                height: 40
                                            )
                                        }
                                    )
                                    .background(.gray)
                                    .clipShape(Circle())
                                }
                            }
                            
                            if viewModel.state.participants.isEmpty {
                                Text("Currently there is no participant")
                            }
                        }
                        .listRowSeparator(.hidden)
                    }
                    .listStyle(.plain)
                }
                
                VStack {
                    Spacer()
                    
                    HStack {
                        Spacer()
                        
                        Button {
                            viewModel.onEvent(
                                event: .Join()
                            )
                        } label: {
                            Text("Join Now")
                                .font(.title2)
                                .foregroundColor(Color.white)
                                .padding()
                        }
                        .buttonStyle(.plain)
                        .background(.primary)
                        .cornerRadius(16)
                        .padding()
                        .shadow(
                            color: Color.black.opacity(0.3),
                            radius: 3,
                            x: 3,
                            y: 3
                        )
                        
                        Spacer()
                    }
                }
            }
        }
        .navigationBarBackButtonHidden()
        .onAppear {
            viewModel.startObserving()
            viewModel.initCampaign(campaignId: campaignId)
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

struct CampaignDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        CampaignDetailScreen(
            campaignId: "C1"
        )
    }
}
