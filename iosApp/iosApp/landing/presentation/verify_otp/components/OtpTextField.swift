//
//  OtpTextField.swift
//  iosApp
//
//  Created by Darren Thiores on 29/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct OtpTextField: View {
    let title: String
    @Binding var text: String
    
    var body: some View {
        TextField(
            title,
            text: $text
        )
        .textFieldStyle(OtpTextFieldStyle())
        .keyboardType(.decimalPad)
    }
}

private struct OtpTextFieldStyle: TextFieldStyle {
    func _body(configuration: TextField<_Label>) -> some View {
        configuration
              .textFieldStyle(PlainTextFieldStyle())
              .tracking(20)
              .accentColor(Color.secondaryColor)
              .foregroundColor(.black)
              .font(Font.h1)
              .underline()
              .baselineOffset(20)
              .padding()
    }
}

struct OtpTextField_Previews: PreviewProvider {
    static var previews: some View {
        OtpTextField(
            title: "Mobile No",
            text: .constant("122111")
        )
    }
}
