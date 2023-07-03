//
//  HomeUseCasesModule.swift
//  iosApp
//
//  Created by Darren Thiores on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class HomeUseCasesModule {
    init() {
        @Provider var searchCampaigns: SearchCampaigns = SearchCampaigns()
    }
}
