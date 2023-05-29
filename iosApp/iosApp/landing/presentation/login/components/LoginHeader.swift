import SwiftUI

struct LoginHeader: View {
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        HStack {
            Button {
                presentationMode.wrappedValue.dismiss()
            } label: {
                Image(systemName: "chevron.left")
            }
            .buttonStyle(.plain)
            
            Spacer()
            
            NavigationLink {
                Text("Create Account")
            } label: {
                Text("Create Account")
            }
            .buttonStyle(.plain)
        }
    }
}

struct LoginHeader_Previews: PreviewProvider {
    static var previews: some View {
        LoginHeader()
    }
}
