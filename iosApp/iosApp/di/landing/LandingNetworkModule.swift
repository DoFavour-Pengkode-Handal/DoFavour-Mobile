//
//  LandingNetworkModule.swift
//  iosApp
//
//  Created by Darren Thiores on 04/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class LandingNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var landingApi: LandingApiService = KtorLandingApiService(client: client)
        @Provider var landingRemoteDataSource: LandingRemoteDataSource = LandingRemoteDataSource(
            apiService: landingApi,
            dispatchers: dispatchers
        )
    }
}
