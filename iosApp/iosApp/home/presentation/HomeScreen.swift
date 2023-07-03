//
//  HomeScreen.swift
//  iosApp
//
//  Created by Darren Thiores on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreen: View {
    @StateObject private var viewModel = IosHomeViewModel()
    
    var body: some View {
        NavigationStack {
            VStack {
                HomeHeader(
                    state: viewModel.state,
                    onEvent: { event in
                        viewModel.onEvent(event: event)
                    }
                )
                
                GeometryReader { geo in
                    ScrollView {
                        ForEach(viewModel.state.campaigns, id: \.id) { campaign in
                            CampaignItem(
                                geo: geo,
                                campaign: campaign
                            )
                        }
                        .listRowSeparator(.hidden)
                        .listRowBackground(Color.clear)
                    }
                    .listStyle(.plain)
                }
            }
            .padding()
            .onAppear {
                viewModel.startObserving()
            }
            .onDisappear {
                viewModel.dispose()
            }
            .gradientSurface()
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
