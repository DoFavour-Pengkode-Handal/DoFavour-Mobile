//
//  AppModule.swift
//  iosApp
//
//  Created by Darren Thiores on 04/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared


class AppModule {
    init() {
        @Provider var dispatchers: DispatchersProvider = StandardDispatchers()
        @Provider var tokenPrefrences: TokenPreferences = IosTokenPreferences()
        @Provider var client = HttpClientFactory().create(
            tokenPreferences: tokenPrefrences
        )
    }
}
