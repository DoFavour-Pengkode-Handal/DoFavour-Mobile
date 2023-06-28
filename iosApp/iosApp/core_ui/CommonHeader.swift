//
//  CommonHeader.swift
//  iosApp
//
//  Created by Darren Thiores on 28/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CommonHeader: View {
    let title: String
    let onBackClick: () -> Void
    
    var body: some View {
        HStack {
            Button {
                onBackClick()
            } label: {
                Image(systemName: "chevron.left")
            }
            .buttonStyle(.plain)
            
            Spacer()
            
            Text(title)
                .font(Font.h3)
            
            Spacer()
        }
    }
}

struct CommonHeader_Previews: PreviewProvider {
    static var previews: some View {
        CommonHeader(
            title: "Verify Otp",
            onBackClick: {  }
        )
    }
}
