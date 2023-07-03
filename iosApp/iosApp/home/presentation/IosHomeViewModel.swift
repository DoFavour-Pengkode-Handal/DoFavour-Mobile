//
//  IosHomeViewModel.swift
//  iosApp
//
//  Created by Darren Thiores on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension HomeScreen {
    @MainActor class IosHomeViewModel: ObservableObject {
        private let viewModel: HomeViewModel
        
        @Published var state: HomeState = HomeState(
            searchText: "",
            filter: .all,
            campaigns: []
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var searchCampaigns: SearchCampaigns
            
            self.viewModel = HomeViewModel(
                searchCampaigns: searchCampaigns,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: HomeEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
