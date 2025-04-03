package client.view.show;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.Vinyl;
import server.model.VinylList;
import client.view.VinylStateListener;

public class ShowVM implements VinylStateListener
{
  private ListProperty<Vinyl> vinyls;

  public ShowVM() {
    ObservableList<Vinyl> vinylList = FXCollections.observableArrayList(
        VinylList.getInstance().getVinylList());
    vinyls = new SimpleListProperty<>(vinylList);

    // Add VinylStateListener to each vinyl in the list
    for (Vinyl vinyl : vinyls) {
      vinyl.addListener(this);
    }
  }

  @Override
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
}
