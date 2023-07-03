import SwiftUI
import shared

struct ContentView: View {
    @AppStorage("isLogin") private var isLogin: Bool = false
    @State private var selectedTab: TopLevelDestination = .Home
    
    let appearance: UITabBarAppearance = UITabBarAppearance()
    init() {
        UITabBar.appearance().scrollEdgeAppearance = appearance
    }
    
	var body: some View {
        if isLogin {
            TabView(selection: $selectedTab) {
                HomeScreen()
                    .tabItem {
                        Label(
                            "Home",
                            systemImage: "house.fill"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(TopLevelDestination.Home)
                
                Text("Post")
                    .tabItem {
                        Label(
                            "Post",
                            systemImage: "photo"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(TopLevelDestination.Post)
                
                Text("History")
                    .tabItem {
                        Label(
                            "History",
                            systemImage: "clock.arrow.circlepath"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(TopLevelDestination.History)
                
                Text("Profile")
                    .tabItem {
                        Label(
                            "Profile",
                            systemImage: "person.fill"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(TopLevelDestination.Profile)
            }
        } else {
            BoardingScreen()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
