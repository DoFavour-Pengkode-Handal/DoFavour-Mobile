import SwiftUI

@main
struct iOSApp: App {
    init() {
        AppModule()
        
        LandingNetworkModule()
        LandingRepositoryModule()
        
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
