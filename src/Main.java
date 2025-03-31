import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Vinyl;
import model.VinylList;

import java.io.IOException;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create some vinyls and add them to the VinylList
    createAndAddVinyls();

    // Create the root layout and the scene for the first window (Show window)
    StackPane root = new StackPane();

    // Load the FXML file for the Show window
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "view/show/Show.fxml"));
      root.getChildren().add(loader.load());
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Set up the scene and the primary stage for the Show window
    Scene scene = new Scene(root);
    primaryStage.setTitle("Vinyls Application");
    primaryStage.setScene(scene);
    primaryStage.show();

    // Create and show the second window (Edit window)
    openEditWindow();
  }

  // Helper method to create and add 10 vinyls to the VinylList
  private void createAndAddVinyls() {
    VinylList vinylList = VinylList.getInstance(); // Get the Singleton instance

//    // Create 10 vinyls with sample data and add them to the VinylList
//    for (int i = 1; i <= 10; i++) {
//      Vinyl vinyl = new Vinyl("Title " + i, "Artist " + i, 2000 + i);
//      vinylList.addVinyl(vinyl);
//    }

    vinylList.addVinyl(new Vinyl("Blackbird", "Alter Bridge", 2007));
    vinylList.addVinyl(new Vinyl("The Concrete Confessional", "Hatebreed", 2016));
    vinylList.addVinyl(new Vinyl("Around the Fur", "Deftones", 1997));
    vinylList.addVinyl(new Vinyl("Cease to Begin", "Band of Horses", 2007));
    vinylList.addVinyl(new Vinyl("Continuum", "John Mayer", 2006));
    vinylList.addVinyl(new Vinyl("Origin of Symmetry", "Muse", 2001));
    vinylList.addVinyl(new Vinyl("Follow The Leader", "Korn", 1998));
    vinylList.addVinyl(new Vinyl("Nevermind", "Nirvana", 1991));
    vinylList.addVinyl(new Vinyl("Dookie",  "Green Day", 1994));
    vinylList.addVinyl(new Vinyl("A Black Mile To The Surface", "Manchester Orchestra", 2017));

    System.out.println("10 vinyls added to the list!");
  }

  // Method to open the Edit window
  public void openEditWindow() {
    try {
      // Load the FXML file and set the root layout as BorderPane
      FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "view/edit/Edit.fxml"));
      BorderPane root = loader.load();  // This loads the BorderPane defined in Edit.fxml

      // Create a new Scene using the loaded root (BorderPane)
      Scene editScene = new Scene(root);

      // Create a new Stage (window) for the Edit window
      Stage editStage = new Stage();
      editStage.setTitle("Edit Vinyls");
      editStage.setScene(editScene);

      // Show the Edit window
      editStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    launch(args);  // Launch the JavaFX application
  }
}
