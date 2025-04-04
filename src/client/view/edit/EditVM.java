package client.view.edit;


import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.Vinyl;
import server.model.VinylList;
import client.view.VinylStateListener;
import server.networking.socketHandler.VinylStateListenerServer;

public class EditVM implements VinylStateListener
{
  private ListProperty<Vinyl> vinyls;

  public EditVM() {
    // Initialize the ListProperty, binding it to the vinyl list from the singleton
    ObservableList<Vinyl> vinylList = FXCollections.observableArrayList(
        VinylList.getInstance().getVinylList());
    vinyls = new SimpleListProperty<>(vinylList);
    for (Vinyl vinyl : vinyls) {
      vinyl.addListener((VinylStateListenerServer) this);
    }
  }

  public void update(Vinyl vinyl) {
    // Called when the state of a vinyl changes
    // Refresh the list, since the state changed, this might require updating the ListView
    vinyls.setAll(VinylList.getInstance().getVinylList());
    int index = vinyls.indexOf(vinyl);
    if (index >= 0) {
      vinyls.set(index, vinyl); // Update the vinyl in the list if it's already there
    }
  }

  public ListProperty<Vinyl> getVinyls() {
    return vinyls;
  }

  // This method is called when the "Reserve" button is clicked
  public void reserveVinyl(Vinyl vinyl) {
    vinyl.reserve();  // Calls the reserve method in Vinyl, which handles state change
  }

  // This method is called when the "Rent" button is clicked
  public void rentVinyl(Vinyl vinyl) {
    vinyl.rent();  // Calls the rent method in Vinyl, which handles state change
  }

  // This method is called when the "Return" button is clicked
  public void returnVinyl(Vinyl vinyl) {
    vinyl.returnVinyl();  // Calls the return method in Vinyl, which handles state change
  }
  public void removeVinyl(Vinyl vinyl){
    vinyl.markForRemoval();
  }
}
