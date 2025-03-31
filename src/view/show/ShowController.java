package view.show;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Vinyl;


public class ShowController
{
    @FXML
    private ListView<Vinyl> vinylListView;
    private ShowVM vm;

    @FXML
    public void initialize() {
      vm = new ShowVM();

      // Bind the ListView to the ObservableList in the ViewModel
      vinylListView.itemsProperty().bind(vm.getVinyls());
    }
}
