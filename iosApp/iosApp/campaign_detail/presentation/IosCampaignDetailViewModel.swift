//
//  IosCampaignDetailViewModel.swift
//  iosApp
//
//  Created by Darren Thiores on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension CampaignDetailScreen {
    @MainActor class IosCampaignDetailViewModel: ObservableObject {
        private let viewModel: CampaignDetailViewModel
        
        @Published var state: CampaignDetailState = CampaignDetailState(
            campaign: nil,
            participants: [],
            joinSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getCampaignById: GetCampaignById
            @Inject var getParticipants: GetParticipants
            
            self.viewModel = CampaignDetailViewModel(
                getCampaignById: getCampaignById,
                getParticipants: getParticipants,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: CampaignDetailEvent) {
            viewModel.onEvent(event: event)
        }
        
        func initCampaign(campaignId: String) {
            viewModel.doInit(campaignId: campaignId)
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
