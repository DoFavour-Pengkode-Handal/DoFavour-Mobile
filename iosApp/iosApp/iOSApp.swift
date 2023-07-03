import SwiftUI

@main
struct iOSApp: App {
    init() {
        LandingUseCasesModule()
        HomeUseCasesModule()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
