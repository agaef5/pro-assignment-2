package client.view.show;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import server.model.Vinyl;


public class ShowController
{
    @FXML
    private ListView<Vinyl> vinylListView;
    private client.view.show.ShowVM vm;

    @FXML
    public void initialize() {
      vm = new client.view.show.ShowVM();

      // Bind the ListView to the ObservableList in the ViewModel
      vinylListView.itemsProperty().bind(vm.getVinyls());
    }
}
