//
//  LandingRepositoryModule.swift
//  iosApp
//
//  Created by Darren Thiores on 04/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class LandingRepositoryModule {
    init() {
        @Inject var remoteDataSource: LandingRemoteDataSource
        @Inject var tokenPrefrences: TokenPreferences
        
        @Provider var landingRepository: LandingRepository = LandingRepositoryImpl(
            remoteDataSource: remoteDataSource,
            tokenPreferences: tokenPrefrences
        )
    }
}
