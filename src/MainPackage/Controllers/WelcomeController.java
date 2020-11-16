package MainPackage.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    public void goToMenu(MouseEvent mouseEvent) {

        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        GameOptionsController controller = new GameOptionsController();
        controller.showStage();

    }
}
