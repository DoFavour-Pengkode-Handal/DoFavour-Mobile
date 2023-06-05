import SwiftUI

struct TitleLessHeader: View {
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
        }
    }
}

struct TitleLessHeader_Previews: PreviewProvider {
    static var previews: some View {
        TitleLessHeader()
    }
}
