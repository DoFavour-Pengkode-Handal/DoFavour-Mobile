//
//  CampaignFilterExtension.swift
//  iosApp
//
//  Created by Darren Thiores on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension CampaignFilter {
    func getImageName() -> String {
        switch self {
        case .all:
            return "rectangle.grid.2x2.fill"
        case .nature:
            return "mountain.2.fill"
        case .health:
            return "heart.fill"
        case .education:
            return "graduationcap.fill"
        default:
            return "rectangle.fill"
        }
    }
}
