//
//  CampaignBar.swift
//  iosApp
//
//  Created by Darren Thiores on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CampaignBar: View {
    let value: Float
    
    var body: some View {
        GeometryReader { geo in
            ZStack(alignment: .leading) {
                Rectangle().frame(
                    width: geo.size.width,
                    height: geo.size.height
                )
                .opacity(0.3)
                .foregroundColor(Color.gray)
                        
                Rectangle().frame(
                    width: min(
                        CGFloat(self.value)*geo.size.width,
                        geo.size.width
                    ),
                    height: geo.size.height
                )
                .foregroundColor(Color.primaryColor)
            }
            .cornerRadius(45.0)
        }
        .frame(height: 10)
    }
}

struct CampaignBar_Previews: PreviewProvider {
    static var previews: some View {
        CampaignBar(
            value: 0.4
        )
    }
}
