import SwiftUI

@main
struct iOSApp: App {
    init() {
        LandingUseCasesModule()
        HomeUseCasesModule()
        CampaignDetailUseCasesModule()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
