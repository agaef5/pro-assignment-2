package client.view.edit;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import server.model.Vinyl;

public class EditController {
  @FXML
  private ListView<Vinyl> vinylListView;

  private EditVM editVM;

  public EditController() {
    this.editVM = new EditVM(); // Instantiate the ViewModel
  }

  // This method will be called to initialize the ListView and bind it to the ViewModel
  public void initialize() {
    vinylListView.itemsProperty().bind(editVM.getVinyls());  // Bind the ListView to the ListProperty in EditVM
  }

  // This method is triggered when the Reserve button is clicked
  @FXML
  private void reserveVinyl() {
    Vinyl selectedVinyl = vinylListView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      editVM.reserveVinyl(selectedVinyl);  // Update the vinyl state via the ViewModel
    }
  }

  // This method is triggered when the Rent button is clicked
  @FXML
  private void rentVinyl() {
    Vinyl selectedVinyl = vinylListView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      editVM.rentVinyl(selectedVinyl);  // Update the vinyl state via the ViewModel
    }
  }

  // This method is triggered when the Return button is clicked
  @FXML
  private void returnVinyl() {
    Vinyl selectedVinyl = vinylListView.getSelectionModel().getSelectedItem();
    if (selectedVinyl != null) {
      editVM.returnVinyl(selectedVinyl);  // Update the vinyl state via the ViewModel
    }
  }

  @FXML
  private void removeVinyl()
  {
    Vinyl selectedVinyl = vinylListView.getSelectionModel().getSelectedItem();
    if (selectedVinyl!= null)
    {
      editVM.removeVinyl(selectedVinyl);
    }
  }
}
