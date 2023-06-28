//
//  VerifyOtpScreen.swift
//  iosApp
//
//  Created by Darren Thiores on 28/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct VerifyOtpScreen: View {
    let email: String
    
    @StateObject private var viewModel = IosVerifyOtpViewModel()
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        GeometryReader { geo in
            VStack {
                CommonHeader(
                    title: "OTP Verification",
                    onBackClick: {
                        presentationMode.wrappedValue.dismiss()
                    }
                )
                
                Spacer()
                    .frame(height: 16)
                
                Image("otp_icon")
                
                HStack(spacing: 0) {
                    Text("Enter the OTP sent to ")
                        .font(Font.captionInter)
                    
                    Text(email)
                        .font(Font.captionInterBold)
                }
                
                Spacer()
                
                TextField(
                    "OTP",
                    text: Binding(
                        get: {
                            String(viewModel.state.otp.prefix(6))
                        },
                        set: { text in
                            viewModel.onEvent(
                                event: .OnOtpChange(
                                    otp: text
                                )
                            )
                        }
                    )
                )
                .padding()
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .strokeBorder(
                            .black.opacity(0.2),
                            style: StrokeStyle(lineWidth: 2.0)
                        )
                )
                
                HStack {
                    Text("Didn't you receive the otp?")
                        .font(Font.body1)
                    
                    if viewModel.state.otpCountDown == 0 {
                        Button {
                            viewModel.onEvent(
                                event: .ReSendOtp()
                            )
                        } label: {
                            Text("Resend OTP")
                                .font(Font.bodyBold)
                                .foregroundColor(Color.secondaryColor)
                        }
                        .buttonStyle(.plain)
                    } else {
                        Text("\(viewModel.state.otpCountDown) Seconds")
                            .font(Font.bodyBold)
                    }
                }
                
                Spacer()
                    .frame(height: 32)
                
                Button {
                    viewModel.onEvent(
                        event: .OnVerifyOtp()
                    )
                } label: {
                    Text("Verify Now")
                        .font(Font.button)
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(.borderedProminent)
                .tint(Color.onBackground)
                
                Spacer()
            }
            .background {
                Image("bg_prop_1")
                    .offset(y: geo.size.height / 2)
            }
        }
        .padding()
        .navigationBarBackButtonHidden()
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

struct VerifyOtpScreen_Previews: PreviewProvider {
    static var previews: some View {
        VerifyOtpScreen(
            email: "darrenthiores@gmail.com"
        )
    }
}
